package week1

/**
 * Created by bamboo on 02.08.14.
 */
object martin1 {

  def main(args: Array[String]) = {

    def crystal(n: Int) = {
      val half = n / 2
      val first = (for {
        i <- half to 0 by -1
      } yield {
        ("*" * i) + ("D" * (n - i - i)) + ("*" * i)
      }) mkString ("\n")
      val sec = (for {
        i <- 1 to half
      } yield {
        ("*" * i) + ("D" * (n - i - i)) + ("*" * i)
      }) mkString ("\n")
      first + "\n" + sec
    }

    val n = Console.readInt
    Console.print(crystal(n))
  }

}
