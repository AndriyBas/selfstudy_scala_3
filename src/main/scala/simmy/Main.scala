package simmy

import simmy.MySim._

/**
 * Created by bamboo on 21.08.14.
 */
object Main {

  def main(args: Array[String]) = {

    val in1, in2, sum, carry = new Wire
    halfAdder(in1, in2, sum, carry)
    probe("sum", sum)
    probe("carry", carry)

  }
}
