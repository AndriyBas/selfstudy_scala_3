val f: PartialFunction[String, String] = {
  case "ping" => "pong"
}

f("ping")
f.isDefinedAt("pong")

abstract class JSON

case class JSeq(elems: List[JSON]) extends JSON

case class JObj(bindings: Map[String, JSON]) extends JSON

case class JNum(num: Double) extends JSON

case class JStr(str: String) extends JSON

case class JBool(bool: Boolean) extends JSON

object JNull extends JSON

val obj = JObj(Map(
  "first_name" -> JStr("Andriy"),
  "last_name" -> JStr("Bas"),
  "address" -> JObj(Map(
    "street" -> JStr("Yandelya"),
    "building" -> JSeq(List(JNum(3), JNum(15))),
    "is_there" -> JBool(false)
  ))))

def show(obj: JSON): String = obj match {
  case JNull => "null"
  case JNum(num) => num.toString
  case JBool(bool) => bool.toString
  case JStr(str) => str
  case JSeq(list) => "[" + (list map show mkString ",") + "]"
  case JObj(bindings) => {
    val inner = bindings map { case (key, json) => key + ":" + show(json)}
    "{" + (inner mkString (",")) + "}"
  }
}

val res = show(obj)
require(res.equals("{first_name:Andriy,last_name:Bas,address:{street:Yandelya,building:[3.0,15.0],is_there:false}}"))