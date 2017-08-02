/**
  * Created by colm on 01/08/17.
  */
trait Semigroup[A] {
  def combine(x: A, y: A): A
}
trait Monoid[A] extends Semigroup[A] {
  def empty: A
}

 //  def empty: Boolean = false
 object Monoid {
   def apply[A](implicit monoid: Monoid[A]) =
     monoid
 }
object monoidBooleanInstances {
  implicit val monoidAndBoolean = new Monoid[Boolean] {
    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean = x && y
  }

  implicit val monoidOrBoolean =  new Monoid[Boolean] {
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = x || y
  }
}

import monoidBooleanInstances.monoidAndBoolean
object moniad extends App{
  def associativeLaw[A](x: A, y: A, z: A)
                       (implicit m: Monoid[A]): Boolean =
    m.combine(x, m.combine(y, z)) == m.combine(m.combine(x, y), z)
  def identityLaw[A](x: A)
                    (implicit m: Monoid[A]): Boolean = {
    println("method 1 " + (m.combine(x, m.empty) == x))
    println("method 2" + (m.combine(m.empty, x) == x))
    (m.combine(x, m.empty) == x) && (m.combine(m.empty, x) == x)
  }
  println(associativeLaw(false,false,true))
  println(identityLaw(true))
}
