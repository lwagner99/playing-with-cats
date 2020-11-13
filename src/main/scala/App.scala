

object App {

  trait Printable[A] {
    def format(value: A): String
  }

  object PrintableInstances {
    implicit val PrintableString = new Printable[String] {
      override def format(value: String): String = value
    }

    implicit val PrintableInt = new Printable[Int] {
      override def format(value: Int): String = value.toString
    }

    implicit val PrintableCat = new Printable[Cat] {
      override def format(value: Cat): String = s"${value.name} is a ${value.age} year-old ${value.color} cat."
    }
  }

  object Printable {
    def format[A](value: A)(implicit printer: Printable[A]) = {
      printer.format(value)
    }
    def print[A](value: A)(implicit printable: Printable[A]) = {
      println(printable.format(value))
    }
  }

  object PrintableSyntax {
    implicit class PrintableOps[A](value: A) {
      def format(implicit printer: Printable[A]) = {
        printer.format(value)
      }
      def print(implicit printer: Printable[A]) = {
        println(printer.format(value))
      }
    }
  }

  case class Cat(name: String, age:Int, color: String)

  def main(args: Array[String]): Unit = {
    import Printable._
    import PrintableInstances._
    import PrintableSyntax._
    val myCat = Cat("Lucy", 12, "black")
    print(myCat)
    myCat.print


  }

}
