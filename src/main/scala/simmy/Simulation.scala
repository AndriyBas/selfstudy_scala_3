package simmy

/**
 * Created by bamboo on 21.08.14.
 */


trait Simulation {

  type Action = () => Unit

  case class Event(time: Int, action: Action)

  private type Agenda = List[Event]

  private var agenda: Agenda = List()

  private var curTime: Int = 0

  def currentTime: Int = curTime

  private def insert(ag: Agenda, item: Event): Agenda = ag match {
    // TODO : have a look at the use of pattern match + if expression in one line below
    case head :: tail if head.time <= item.time => head :: insert(tail, item)
    case _ => item :: ag
  }

  def afterDelay(delay: Int)(block: => Unit): Unit = {
    val item = Event(currentTime + delay, () => block)
    agenda = insert(agenda, item)
  }

  private def loop(): Unit = agenda match {
    case head :: tail =>
      agenda = tail
      curTime = head.time
      head.action()
      loop()
    case Nil => ()
  }

  def run(): Unit = {
    afterDelay(0) {
      println("*** simulation started, time = " + currentTime + " ***")
    }
    loop()
  }


}



