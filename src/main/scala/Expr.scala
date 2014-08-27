/**
 * Created by bamboo on 27.08.14.
 */

import spiral.Element
import spiral.Element._

sealed abstract class Expr

case class Var(name: String) extends Expr

case class Number(x: Double) extends Expr

case class UnOp(operator: String, arg: Expr) extends Expr

case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

class ExprFormatter {
  // Contains operators in groups of increasing precedence
  private val opGroups =
    Array(
      Set("|", "||"),
      Set("&", "&&"),
      Set("ˆ"),
      Set("==", "!="),
      Set("<", "<=", ">", ">="),
      Set("+", "-"),
      Set("*", " % ")
    )
  // A mapping from operators to their precedence
  private val precedence = {
    val assocs =
      for {
        i <- 0 until opGroups.length
        op <- opGroups(i)
      } yield op -> i
    assocs.toMap
  }

  private val unaryPrecedence = opGroups.length
  private val fractionPrecedence = -1

  private def format(e: Expr, enclPrec: Int): Element = e match {
    case Var(s) =>
      elem(s)
    case Number(x) =>

      def stripEnd(s: String): String =
      // function removes all zeros after [.] (if any) when it ends only with zeros
        if (s matches "[0-9]*.[0]*") {
          val str = s.substring(0, s.indexOf('.'))
          if (str.length > 0) str
          else "0"
        }
        else s
      elem(stripEnd(x.toString))
    case UnOp(op, e) =>
      elem(op) beside format(e, unaryPrecedence)

    case BinOp("/", left, right) =>
      val top = format(left, fractionPrecedence)
      val bottom = format(right, fractionPrecedence)
      val line = elem('-', top.width max bottom.width, 1)
      val frac = top above line above bottom
      if (enclPrec != fractionPrecedence) frac
      else elem(" ") beside frac beside elem(" ")
    case BinOp(op, left, right) =>
      val opPrec = precedence(op)
      val l = format(left, opPrec)
      val r = format(right, opPrec)
      val oper = l beside elem(s" ${op} ") beside r
      if (enclPrec <= opPrec) oper
      else elem("(") beside oper beside elem(")")
  }

  def format(e: Expr): Element = format(e, 0)
}

object Express extends Application {
  val f = new ExprFormatter
  val e1 = BinOp("*", UnOp("+", BinOp("/", Number(1), Number(2))),
    BinOp("+", Var("x"), Number(1)))
  val e2 = BinOp("+", BinOp("/", Var("x"), Number(2)),
    BinOp("/", Number(1.5), Var("x")))
  val e3 = BinOp("/", e1, e2)

  val e4 = BinOp("<=", BinOp("/", BinOp("*", BinOp("+", Number(2.0), Var("a")), Number(6.0)),
    BinOp(" % ", BinOp("/", Number(17), Var("x")), BinOp("+", Var("x"), Var("y")))),
    BinOp("+", Number(2.0), BinOp("|", Var("a"), Var("b"))))

  def show(e: Expr) = println(f.format(e) + "\n\n")

  for (e <- Array(e1, e2, e3, e4)) show(e)
}