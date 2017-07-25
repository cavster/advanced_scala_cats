import cats.Show
import cats.instances.int._
import cats.instances.string._
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
