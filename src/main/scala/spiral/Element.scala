package spiral

/**
 * Created by bamboo on 05.08.14.
 */

//import spiral.Element.elem

//abstract class Element() {
//  def contents: Array[String]
//
//  def width: Int = if (height == 0) 0 else contents(0).length
//
//  def height: Int = contents.length
//
//  def above(that: Element) = {
//    elem(this.contents ++ that.contents)
//  }
//
//  def beside(el: Element) = {
//    this.contents.zip()
//  }
//
//  def widen(w: Int) = {
//
//  }
//
//  def heighten(h: Int) = {
//
//  }
//}
//
//
//object Element {
//
//  private class ArrayElement(val contents: Array[String])
//    extends Element
//
//  private class UniformElement(ch: Char,
//                               override val width: Int,
//                               override val height: Int)
//    extends Element {
//    private val line = ch.toString * width
//
//    def contents = Array.fill(height)(line)
//  }
//
//  def elem(ch: Char, width: Int, height: Int) =
//    new UniformElement(ch, width, height)
//
//  def elem(contents: Array[String]) =
//    new ArrayElement(contents)
//}