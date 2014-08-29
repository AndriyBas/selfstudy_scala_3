val pattern: String = "[0-9]*.[0]*"

"121.0" matches (pattern)

"12" matches (pattern)

"1424.1000" matches pattern

".0000" matches pattern

".0002" matches pattern

"2.0300" matches pattern

def stripEnd(s: String): String =
  if (s matches "[0-9]*.[0]*") {
    val str = s.substring(0, s.indexOf('.'))
    if (str.length > 0) str
    else "0"
  }
  else s

stripEnd("1230.000")
stripEnd("0.000")
stripEnd(".000")
stripEnd("1230.0020")

def fac1(n: Int): Int =
  if (n == 0) 1 else n * fac1(n - 1)

fac1(4)

def fac2(n: Int): Int = {
  def loop(c: Int, acc: Int): Int =
    if (c == 1) acc
    else loop(c - 1, acc * c)
  loop(n, 1)
}

fac2(4)

::(1, Nil)



