import spiral.Element._

//elem("hello") beside elem("world!")
//"\n" + (elem("hello") above elem(Array("world!", "lsdkfrgn")))
//"\n" + (elem(Array("hello", "wsd")) beside elem(Array(" worl", "  lsdkfrgn")))
//elem('_', 1, 3) above elem('*', 3, 3)
//elem("wow") above elem("d")
"\n" + (spiral.Spiral.go(11))

val arr = Array("a", "b", "c")
val z = arr zip (0 to arr.length)

val m = z toMap