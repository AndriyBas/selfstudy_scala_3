trait Generator[+T] {
  self =>
  def generate: T
  def map[S](f: T => S): Generator[S] = new Generator[S] {
    def generate = f(self.generate)
  }
  def flatMap[S](f: T => Generator[S]): Generator[S] = new Generator[S] {
    def generate = f(self.generate).generate
  }
}
val ints = new Generator[Int] {
  def generate = scala.util.Random.nextInt
}
val booleans = new Generator[Boolean] {
  def generate = ints.generate > 0
}
//val pairs = new Generator[(Int, Int)] {
//  def generate = (ints.generate, ints.generate)
//}
val bools = for (x <- ints) yield x > 0
def pairs[T, U](t: Generator[T], u: Generator[U]) =
  t flatMap (x => u map (y => (x, y)))
pairs(ints, bools).generate
def single[T](x: T): Generator[T] = new Generator[T] {
  def generate = x
}
def emptyList = single(Nil)

def nonEmptyList = for {
  head <- ints
  tail <- lists
} yield head :: tail

def lists: Generator[List[Int]] = for {
  isEmpty <- bools
  list <- if (isEmpty) emptyList else nonEmptyList
} yield list

trait Tree

case class Inner(left: Tree, right: Tree) extends Tree

case class Leaf(x: Int) extends Tree

def leafs = for {
  i <- ints
} yield Leaf(i)

def inners = for {
  left <- trees
  right <- trees
} yield Inner(left, right)

def trees: Generator[Tree] = for {
  isLeaf <- bools
  t <- if (isLeaf) leafs else inners
} yield t

trees.generate

