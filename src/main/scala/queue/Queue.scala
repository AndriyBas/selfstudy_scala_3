package queue

/**
 * Created by bamboo on 29.08.14.
 */
class Queue[+T](
                 protected[queue] val leading: List[T],
                 protected[queue] val trailing: List[T]) {

  protected[queue] def mirror = leading match {
    case Nil => new Queue(trailing.reverse, Nil)
    case _ => this
  }

  def head = mirror.leading.head

  def tail = {
    val m = mirror
    new Queue(m.leading.tail, m.trailing)
  }

  def enqueue[U >: T](x: U) = {
    println("in root")
    new Queue[U](leading, x :: trailing)
  }

  override def toString = (leading ::: trailing.reverse) mkString("[", ", ", "]")
}

object Queue {
  def apply[T](xs: List[T]) = {
    new Queue(xs, Nil)
  }
}

//class IntQueue extends Queue[Int](List.empty[Int], List.empty[Int]) {
//
//  def enqueue(x: Int) = {
//    new Queue(mirror.leading.tail, trailing)
//  }
//}

class StringQueue(override val leading: List[String],
                  override val trailing: List[String]) extends Queue[String](leading, trailing) {

  override def enqueue[U >: String](x: U) = {
    println("int str " + x)

    new Queue[U](leading, x :: trailing)
  }
}

class Fruit

class Orange extends Fruit

class Apple extends Fruit

class OutCh[-T] {
  def write(c: T): Unit = {
    println(c)
  }
}

object MainQ {
  def main(args: Array[String]): Unit = {
    var a = Queue(List(1, 2, 3, 4))
    a = a.enqueue(5)
    println(a)
    println(a.head)
    println(a.tail)

    var qq = new Queue[AnyVal](Nil, Nil)
    qq.enqueue(1)
    qq.enqueue("sd")


    val x = new Queue[Apple](List(new Apple), Nil)
    val y: Queue[Fruit] = x.enqueue(new Orange)


    val c1 = new OutCh[AnyRef]
    val c2: OutCh[String] = c1
    c1.write("sdf")
    c2.write("sd")
  }
}