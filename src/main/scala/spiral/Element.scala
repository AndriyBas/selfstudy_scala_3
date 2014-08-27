package spiral

/**
 * Created by bamboo on 05.08.14.
 */

import spiral.Element.elem

abstract class Element() {
  def contents: Array[String]

  def width: Int = if (height == 0) 0 else contents(0).length

  def height: Int = contents.length

  def above(el: Element): Element = {
    val thisNew = this widen el.width
    val elNew = el widen this.width
    elem(thisNew.contents ++ elNew.contents)
  }

  def beside(el: Element): Element = {
    val thisNew = this heighten el.height
    val elNew = el heighten this.height
    val lines = for {
      (line1, line2) <- thisNew.contents zip elNew.contents
    } yield line1 + line2
    elem(lines)
  }

  def widen(w: Int): Element =
    if (w <= this.width) this
    else {
      val left = elem(' ', (w - this.width) / 2, this.height)
      val right = elem(' ', w - this.width - left.width, this.height)
      left beside this beside right
    }


  def heighten(h: Int): Element =
    if (h <= this.height) this
    else {
      val top = elem(' ', this.width, (h - this.height) / 2)
      val bottom = elem(' ', this.width, h - this.height - top.height)
      top above this above bottom
    }


  override def toString = contents mkString "\n"
}


object Element {

  class ArrayElement(val contents: Array[String])
    extends Element

  class UniformElement(ch: Char,
                       override val width: Int,
                       override val height: Int)
    extends Element {
    private val line = ch.toString * width

    def contents = Array.fill(height)(line)
  }

  class LineElement(line: String) extends Element {
    def contents = Array(line)

    override def width = line.length

    override def height = 1
  }

  def elem(ch: Char, width: Int, height: Int) =
    new UniformElement(ch, width, height)

  def elem(contents: Array[String]) =
    new ArrayElement(contents)

  def elem(line: String) =
    new LineElement(line)
}

object Spiral {
  private val space = elem(" ")
  private val corner = elem("+")

  private def spiral(edges: Int, direction: Int): Element = {
    if (edges == 1) corner
    else {
      val sp = spiral(edges - 1, (direction + 3) % 4)
      def verticalBar = elem('|', 1, sp.height)
      def horizontalBar = elem('-', sp.width, 1)

      direction match {
        case 0 => (corner beside horizontalBar) above (sp)
        case 1 => (sp) beside (corner above verticalBar)
        case 2 => (space beside sp) above (horizontalBar beside corner)
        case _ => (verticalBar above corner) beside (space above sp)
      }
    }
  }

  def go(nSides: Int) = spiral(nSides, 0)
}