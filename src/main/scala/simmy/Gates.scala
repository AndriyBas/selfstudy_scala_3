package simmy

/**
 * Created by bamboo on 21.08.14.
 */


abstract class Gates extends Simulation {

  def InvertDelay: Int

  def AndGateDelay: Int

  def OrGateDelay: Int

  class Wire {
    type Action = () => Unit

    private var sigVal = false
    private var actions: List[Action] = List()

    def getSignal: Boolean = sigVal

    def setSignal(s: Boolean): Unit =
      if (s != sigVal) {
        sigVal = s
        actions foreach (_())
      }

    def addAction(a: Action) = {
      actions = a :: actions
      a()
    }
  }

  def inverter(in: Wire, out: Wire): Unit = {
    def invertAction(): Unit = {
      val inSig = in.getSignal
      afterDelay(InvertDelay) {
        out setSignal !inSig
      }
    }
    in addAction invertAction
  }

  def andGate(in1: Wire, in2: Wire, out: Wire): Unit = {
    def andAction(): Unit = {
      val in1Sig = in1.getSignal
      val in2Sig = in2.getSignal
      afterDelay(AndGateDelay) {
        out setSignal (in1Sig && in2Sig)
      }
    }
    in1 addAction andAction
    in2 addAction andAction
  }

  def orGate(in1: Wire, in2: Wire, out: Wire): Unit = {
    def orAction(): Unit = {
      val in1Sig = in1.getSignal
      val in2Sig = in2.getSignal
      afterDelay(OrGateDelay) {
        out setSignal (in1Sig || in2Sig)
      }
    }
    in1 addAction orAction
    in2 addAction orAction
  }

  def probe(name: String, wire: Wire): Unit = {
    def probeAction(): Unit = {
      println(s"$name $currentTime value = ${wire.getSignal}")
    }
    wire addAction probeAction
  }

}