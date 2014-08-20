package quickcheck

import org.scalacheck.Arbitrary._
import org.scalacheck.Prop._
import org.scalacheck._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  //  val genIntList = Gen.nonEmptyContainerOf[List, Int](Gen.oneOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 10, 11, 12, 13, 14, 15, 16, 17, 18))

  //  def randList = Gen.nonEmptyContainerOf[List, Int](Gen.oneOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 10, 11, 12, 13, 14, 15, 16, 17, 18)).sample.getOrElse(List(0))

  def randList = Gen.nonEmptyContainerOf[List, Int](Gen.oneOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 10, 11, 12, 13, 14, 15, 16, 17, 18)).sample.getOrElse(List(0))

  def listToHeap(xs: List[Int]) = xs.foldLeft(empty)({ (h, i) => insert(i, h)})

  property("min1") = forAll { a: Int =>
    val h = insert(a, empty)
    findMin(h) == a
  }

  property("min2") = forAll { a: Int =>
    var h = insert(a, empty)
    forAll { b: Int =>
      h = insert(b, h)
      findMin(h) == Integer.min(a, b)
    }
  }

  property("min3") = forAll { (a: Int, b: Int, c: Int) =>
    val h = insert(a, insert(b, insert(c, empty)))
    val x = List(a, b, c)
    findMin(h) == x.min
  }

  property("min3 updated") = forAll { (a: Int, b: Int, c: Int) =>
    val x = List(a, b, c)
    val h = x.foldLeft(empty)({ (h, i) => insert(i, h)})
    findMin(h) == x.min
  }

  property("min list") = forAll { (a: Int) =>
    val list = randList
    val h = list.foldLeft(empty)({ (h, i) => insert(i, h)})
    findMin(h) == list.min
  }

  property("heap empty 1") = forAll { a: Int =>
    val h = insert(a, empty)
    isEmpty(deleteMin(h))
  }

  property("heap empty 2") = forAll { (a: Int, b: Int) =>
    val h = insert(a, insert(b, empty))
    isEmpty(deleteMin(deleteMin(h)))
  }

  property("heap empty list") = forAll { (a: Int) =>
    val list = randList
    // list to heap
    val h = list.foldLeft(empty)({ (h, i) => insert(i, h)})
    // traverse list again and delete min each time
    val h2 = list.foldLeft(h)({ (h, i) => deleteMin(h)})
    // check if is empty
    isEmpty(h2)
  }

  property("min of meld list") = forAll { a: Int =>
    val a1 = randList
    val a2 = randList

    val h1 = listToHeap(a1)
    val h2 = listToHeap(a2)

    findMin(meld(h1, h2)) == Integer.min(a1.min, a2.min)
  }


  lazy val genHeap: Gen[H] = ???

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

}
