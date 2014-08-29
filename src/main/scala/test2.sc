

def mergeSort[T](less: (T, T) => Boolean)(xs: List[T]): List[T] = {
  def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
    case (Nil, _) => ys
    case (_, Nil) => xs
    case (x :: xs1, y :: ys1) =>
      if (less(x, y)) x :: merge(xs1, ys)
      else y :: merge(xs, ys1)
  }
  val mid = xs.length / 2
  if (mid == 0) xs
  else {
    val (l, r) = xs splitAt (mid)
    merge(mergeSort(less)(l), mergeSort(less)(r))
  }
}

def reverseLeft[T](xs: List[T]) =
  xs.foldLeft(List[T]()) { (ys, y) => y :: ys}

reverseLeft(List(1, 2, 3, 4, 5))
val l = List(1, 34, 4, 1, 4, 2, 8, 3)
def intSort = mergeSort((a: Int, b: Int) => a < b) _
intSort(l)
List.fill(1, 2, 3, 5)("ololo")
List.tabulate(3, 4)(_ * _)
(List(10, 20), List(3, 4, 5)).zipped.map(_ * _)
val a = Array(10)
a mkString ", "
import scala.collection.mutable.ListBuffer
val x = new ListBuffer[Int]
x += 1
x += 3
5 +=: x
x mkString ", "
val str = "See, Spot run. Run, Spot! Run!!"
val sp = str.split("[ !.?,]+")
val m = scala.collection.mutable.Map.empty[String, Int]
for (word <- sp) {
  val lowerWord = word.toLowerCase
  val old = m.getOrElse(lowerWord, 0)
  m += (lowerWord -> (old + 1))
}

m mkString ", "
sp mkString ", "
"_____________"
sp.groupBy(_.toLowerCase).map({ case (y, ys) => (y -> ys.length)})
sp.foldLeft(Set[String]()) { (set, x) => set + x.toLowerCase}

