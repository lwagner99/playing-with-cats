package casestudies.one

import cats.Applicative
import cats.instances.list._
import cats.syntax.functor._
import cats.syntax.traverse._ // for map


class UptimeService[F[_]](client: UptimeClient[F])(implicit a: Applicative[F]) {
  def getTotalUptime(hostnames: List[String]): F[Int] = hostnames.traverse(client.getUptime).map(_.sum)

}
