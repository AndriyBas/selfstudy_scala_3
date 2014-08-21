package simmy

/**
 * Created by bamboo on 21.08.14.
 */

abstract class Circuits extends Gates {

  def halfAdder(a: Wire, b: Wire, s: Wire, c: Wire): Unit = {
    val d, e = new Wire
    orGate(a, b, d)
    andGate(a, b, c)
    inverter(c, e)
    andGate(d, e, s)
  }

  def fullAdder(a: Wire, b: Wire, cin: Wire, s: Wire, cout: Wire): Unit = {

    val s1, c1, c2 = new Wire
    halfAdder(a, cin, s1, c1)
    halfAdder(b, s1, s, c2)
    orGate(c1, c2, cout)
  }
}