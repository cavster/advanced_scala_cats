/**
  * Created by colm on 22/08/17.
  */

trait Printable2[A] {
  def format(value: A): String
  def contramap[B](func: B => A): Printable2[B] =
    ???
}
object test1234 {
  def format[A](value: A)(implicit p: Printable2[A]): String =
    p.format(value)
}
final case class Box[A](value: A)
import test1234.format
object Funtor2 extends App{
  implicit val stringPrintable =
    new Printable2[String] {
      def format(value: String): String =
        "\"" + value + "\""
    }
  implicit val booleanPrintable =
    new Printable2[Boolean] {
      def format(value: Boolean): String =
        if(value) "yes" else "no"
    }
  println(format("hello"))
  // res4: String = "hello"
  println(format(true))
  val testBOx = Box("Hello")
  println(format(testBOx))

}
