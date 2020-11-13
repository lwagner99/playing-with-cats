package casestudies.two

import cats.kernel.Monoid
import cats.syntax.semigroup._
//import cats.instances.string._
import scala.concurrent.ExecutionContext.Implicits.global // default: starts one thread each cpu
import scala.concurrent.Future

object App {
  def main(args: Array[String]): Unit = {
    val vector = Vector(1, 2, 3)
    val tmp = foldMap(vector)(x => x + 1)
    val ll: String = foldMap(Vector(1, 2, 3))(_.toString + "! ")
    println("das")

    val future1 = Future {
      (1 to 100).toList.foldLeft(0)(_ + _)
    }
    Thread.sleep(100)
    val lat = 3
    val la = 3
  }

  def foldMap[A, B: Monoid](vector: Vector[A])(f: A => B): B = {
    vector.map(f).foldLeft(Monoid[B].empty)(_ |+| _)
  }

  def parallelFoldMap[A, B: Monoid](vector: Vector[A])(f: A => B): Future[B] = {
    val cpus = Runtime.getRuntime.availableProcessors()
    val groupedVector: Iterator[Vector[A]] =
      vector.grouped(vector.length / cpus)
    val futureIterator: Iterator[Future[B]] =
      groupedVector.map { vector =>
      Future {
        foldMap(vector)(f)
      }
    }
    val result: Future[Iterator[B]] = Future.sequence(futureIterator)
    result.map(iterable => iterable.foldLeft(Monoid[B].empty)(_ |+| _))
  }
}
