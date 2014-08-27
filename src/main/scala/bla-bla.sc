def ff(x: Any) = x match {
  case m: Map[Int, Int] => "wow"
  case List(r@List(12)) => r
  case _ => "ololo"
}
ff(Map(1 -> "234"))
ff(Map(1 -> 2))
ff(List(List(12)))

sealed abstract class Expr

case class Var(s: String) extends Expr

case class Num(n: Int) extends Expr

case class Op(op: String, a: Expr, b: Expr) extends Expr

def f01(ex: Expr) = (ex: @unchecked) match {
  case Var(s) => "1"
}

val fpar: PartialFunction[List[Int], Int] = {
  case x :: y :: _ => y
}

fpar.isDefinedAt(List(2))