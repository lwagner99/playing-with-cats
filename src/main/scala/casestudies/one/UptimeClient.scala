package casestudies.one

import cats.instances.future._
import cats.instances.list._
import cats.syntax.traverse._

trait UptimeClient[F[_]] {
  def getUptime(hostname: String): F[Int]

}
