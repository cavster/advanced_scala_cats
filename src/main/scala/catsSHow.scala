import cats.Show
import cats.instances.int._
import cats.instances.string._
import cats.kernel.Eq
import cats.syntax.show._

object catsShow  extends App{
  implicit val catShow = Show.show[Cat] { cat =>
    val name = cat.name.show
    val age = cat.age.show
    val color = cat.color.show
    s"$name is a $age year-old $color cat."
  }
println(Cat("COlm",123,"Black").show)
}

import java.util.Date
import cats.instances.long._
import cats.syntax.option._
import cats.instances.int._
import cats.instances.option._
import cats.syntax.eq._
object test1 {
  implicit val dateEqual = Eq.instance[Date] { (date1, date2) =>
    date1.getTime === date2.getTime
  }
  implicit val catEqual = Eq.instance[Cat] { (cat1, cat2) =>
    (cat1.name === cat2.name) && (cat1.age === cat2.age) && (cat1.color === cat2.color)
  }
}
import test1._
object catEqulity extends App{
  val cat1 = Cat("Garfield",
    35, "orange and black")
  val cat2 = Cat("Heathcliff", 30, "orange and black")
  val x = new Date() // now
  Thread.sleep(5000)
  val y = new Date() // a bit later than now
 /* println(x === x)
  // res11: Boolean = true
  println(x === y)
  // res12: Boolean = false
  */
  val optionCat1 = Option(cat1)
  val optionCat2 = Option.empty[Cat]
  println(cat1 === cat2)
  println(optionCat1 =!= optionCat2)
  println(optionCat1 === optionCat2)
}
