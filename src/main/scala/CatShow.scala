//import cats.Show
//import cats.instances.int._

import cats.{Eq, Show}
import cats.syntax.show._
import cats.syntax.eq._


object CatShow {


  case class Cat(name: String, age: Int, color: String)

  object CatShowInstances {
    implicit val CatShowImplicit = new Show[Cat] {
      def show(value: Cat): String = s"${value.name} is a ${value.age} year-old ${value.color} cat."
    }
  }

  implicit val CatShowQuick: Show[Cat] = Show.show(cat => "quickTest")

  object CatComparerInstances {
    implicit val CatComparer: Eq[Cat] = new Eq[Cat] {
      def eqv(x: Cat, y: Cat): Boolean = {
        x == y
      }
    }

  }


  def main(args: Array[String]): Unit = {
    // import CatShowInstances._
    import CatComparerInstances._
    val myCat = Cat("Lucy", 12, "black")
    val myCat2 = Cat("Lucy", 2, "black")
    println(myCat.show)

    //val IntShow: Show[Int] = Show.apply[Int]
    //val tmp = IntShow.show(12)
    val tmp2 = 123.show

    myCat === myCat2
    println(myCat === myCat2)

  }
}
