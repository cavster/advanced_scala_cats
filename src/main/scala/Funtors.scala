import scala.language.higherKinds
trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}
import cats.instances.function._
import cats.syntax.functor._

object catsTest extends App{

  import cats.Functor
  import cats.instances.list._
  import cats.instances.option._
  val list1 = List(1, 2, 3)
  // list1: List[Int] = List(1, 2, 3)
  val list2 = Functor[List].map(list1)(_ * 2)
  // list2: List[Int] = List(2, 4, 6)
  val option1 = Option(123)
  println(option1.map(_.toString))
  val option2 = Functor[Option].map(option1)(_.toString)
  // option2: Option[String] = Some(123)
  val func = (x: Int) => x + 1
  // func: Int => Int = <function1>
  println(func)
  val lifted = Functor[Option].lift(func)
   println(lifted)
  println(lifted(option1))
  val func1 = (a: Int) => a + 1
  // func1: Int => Int = <function1>
  val func2 = (a: Int) => a * 2
  // func2: Int => Int = <function1>
  val func3 = func1.map(func2)
  println("ssadssa " + func3(1))
}