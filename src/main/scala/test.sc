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
    if(str.length > 0) str
    else "0"
  }
  else s

stripEnd("1230.000")
stripEnd("0.000")
stripEnd(".000")
stripEnd("1230.0020")



