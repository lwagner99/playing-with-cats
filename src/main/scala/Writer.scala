object Writer {

  sealed trait Json

  final case class JsObject(get: Map[String, Json]) extends Json

  final case class JsString(get: String) extends Json

  // type class
  trait JsonWriter[A] {
    def write(value: A): Json
  }

  // type class instance (implicit value)
  object JsonWriterInstances {
    implicit val stringWriter = new JsonWriter[String] {
      override def write(value: String): Json =
        JsString(value)
    }
  }

  // interface object
  object JsonObject {
    def toJson[A](value: A)(implicit writer: JsonWriter[A]) = {
      writer.write(value)
    }
  }

  object JsonSyntax {
    implicit class JsonWriterOps[A](value: A) {
      def toJson(implicit writer: JsonWriter[A]) = {
        writer.write(value)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    import JsonSyntax._
    import JsonWriterInstances._
    import JsonObject._

    val tmp = "testJson".toJson
    val tmp2 = toJson("testJson")

    // to get implicit value (type class instance)
    val testImplicit = implicitly[JsonWriter[String]]
    println("hello")
  }

}