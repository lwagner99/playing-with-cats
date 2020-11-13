package casestudies.one

import cats.Id

import scala.concurrent.Future


class TestUptimeClient(hosts: Map[String, Int]) extends UptimeClient[Id] {
  override def getUptime(hostname: String): Id[Int] = {
    val tmp: Int = hosts.getOrElse(hostname, 0)
    tmp
  }
}

object TestUptimeClient {

  def main(args: Array[String]): Unit = {
    println(Runtime.getRuntime.availableProcessors())
    testTotalUptime()
  }


  def testTotalUptime() = {
    val hosts = Map("host1" -> 10, "host2" -> 6)
    val client = new TestUptimeClient(hosts)
    val service = new UptimeService(client)
    val actual = service.getTotalUptime(hosts.keys.toList)
    val expected = hosts.values.sum
    assert(actual == expected)
  }
}
