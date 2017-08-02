
trait Printable[A] {
  def format(value: A): String
}
object PrintableInstances {
  implicit val stringWriter = new Printable[String] {
    def format(value: String): String = value
  }
  implicit val IntWriter = new Printable[Int] {
    def format(value: Int): String = value.toString
  }
  implicit val CatWriter = new Printable[Cat] {
    def format(value: Cat): String = s"${value.name} is a ${value.age} year old ${value.color} Cat"
  }

}
object Printable {
  def format[A](input: A)(implicit p: Printable[A]): String =
    p.format(input)
  def print[A](input: A)(implicit p: Printable[A]): Unit =
    println(format(input))
}


object PrintableSyntax{
  implicit class PrintOps[A](value: A) {
    def format(implicit p: Printable[A]): String = p.format(value)
    def printInsanity(implicit p: Printable[A]): Unit = println("I am the PrintOps " + p.format(value))
  }
}
final case class Cat(
                      name: String,
                      age: Int,
                      color: String
                    )
import java.util.Date

import PrintableInstances._
import PrintableSyntax.PrintOps
object test extends App{
  Cat("Colm" , 2 ,"Red").printInsanity
  "Hello".printInsanity
  1.printInsanity
  print(Cat("Colm" , 2 ,"Red"))
  PrintableSyntax.PrintOps(print(Cat("Luke",35,"white")))
  print(new Date())
  print(1)
}