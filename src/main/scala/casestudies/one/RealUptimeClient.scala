package casestudies.one
import cats.instances.future._
import cats.instances.list._
import cats.syntax.traverse._

import scala.concurrent.Future



class RealUptimeClient extends UptimeClient[Future] {
  override def getUptime(hostname: String): Future[Int] = ???
}
