
def WHILE(condition: => Boolean)(command: => Unit): Unit =
  if (condition) {
    command
    WHILE(condition)(command)
  } else ()

def REPEAT(command: => Unit)(condition: => Boolean): Unit =
  if (condition) ()
  else {
    command
    REPEAT(command)(condition)
  }

var i = 0;
WHILE(i < 5) {
  println(i)
  i = i + 1
}
REPEAT {
  println(i)
  i = i - 1
}(i <= 0)

class R2OLOLO(command: => Unit) {
  def UNTIL(condition: => Boolean): Unit =
    if (condition) ()
    else {
      command
      UNTIL(condition)
    }
}

def REPEATU(command: => Unit): R2OLOLO = new R2OLOLO(command)

REPEATU {
  println(i)
  i = i - 1
} UNTIL (i <= -5)