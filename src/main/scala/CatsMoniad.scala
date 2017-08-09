import cats.Semigroup
import cats.instances.int._
import cats.instances.option._
import cats.instances.string._
import cats.syntax.semigroup._

case class Order(totalCost: Double, quantity: Double)
 object OrderMoniad  {

   implicit val orderMonaid = new Monoid[Order] {
     override def empty: Order = Order(0,0)

     override def combine(x: Order, y: Order): Order = Order( (x.quantity * x.totalCost) + (y.quantity * y.totalCost) ,x.quantity + y.quantity)
   }
 }
 import OrderMoniad.orderMonaid
object CatsMoniad extends App{
  def addOption(items: List[Option[Int]]): Option[Int] = {
    items.foldLeft(cats.Monoid[Option[Int]].empty)(_ |+| _)}

  def addNormal(items: List[Int]): Int = {
    items.foldLeft(cats.Monoid[Int].empty)(_ |+| _)}

  def addGeneric[A](items : List[A])(implicit monoid: cats.Monoid[A]): A = {
    items.foldLeft(monoid.empty)(_ |+| _)
  }

  def addTypeBounds[A: cats.Monoid](items: List[A]): A = {
    items.foldLeft(cats.Monoid[A].empty)(_ |+| _)
  }

 // def add[A](items: List[A])(implicit monoid: Monoid[A]): A = items.foldLeft(catmonoid.empty)(_ |+| _)
  println(Semigroup[String].combine("Hisdfsdfsdf ", "there"))
  val a = Some(1)
  val b = Some(2)
  val lista = List(Some(1),Some(2),Some(3),Some(4),Some(5),None)
  val listb= List(1,2,3)
  println(Semigroup[Int].combine(1, 1))
  println(Semigroup[Option[Int]].combine(a, b))
  println(addNormal(listb))
  println(addOption(lista))

  println(addGeneric(listb))
  println(addGeneric(lista))
  val order = Order(15,20)
  val listOrder = List(order,order)
  println(addGeneric(order)(OrderMoniad.orderMonaid))
 // println(addTypeBounds(listb))
 // println(addTypeBounds(lista))
}
