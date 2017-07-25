/**
  * Created by colm on 25/07/17.
  */
import JsonWriterInstances._
import Json.toJson
import JsonWriterInstances._
object Scala extends App{
  stringJsonWriter
Json.toJson("hello")(stringJsonWriter)
println(Json.toJson("hello")(stringJsonWriter))
}
sealed trait Json
final case class JsObject(get: Map[String, Json]) extends Json
final case class JsString(get: String) extends Json
final case class JsNumber(get: Double) extends Json
// The "serialize to JSON" behavior is encoded in this trait
trait JsonWriter[A] {
  def write(value: A): Json
}

object JsonWriterInstances {
  implicit val stringJsonWriter = new JsonWriter[String] {
    def write(value: String): Json =
      JsString(value)
  }
}
object Json {
  import JsonWriterInstances.stringJsonWriter
  def toJson[A](value: A)(implicit w: JsonWriter[A]): Json =
    w.write(value)
}