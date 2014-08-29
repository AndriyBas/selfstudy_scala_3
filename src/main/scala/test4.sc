

import queue._



var a = Queue(List(1, 2, 3, 4))
a = a.enqueue(5)
(a.head)
(a.tail)

val x = Queue(List("a", "b"))
val y: Queue[Any] = x
val z: Queue[Any] = x.enqueue(1)
(z.head)
