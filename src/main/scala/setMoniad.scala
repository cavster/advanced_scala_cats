object setMonade {
  implicit def unionMonade[A] = new Monoid[Set[A]] {
    override def empty:  Set[A]  = Set.empty[A]

    override def combine(x: Set[A], y: Set[A]): Set[A] = x union(y)
  }
}

import setMonade.unionMonade
object moniadSET extends App{
  def associativeLaw[A](x: A, y: A, z: A)
                       (implicit m: Monoid[A]):Boolean =
    m.combine(x, m.combine(y, z)) == m.combine(m.combine(x, y), z)
  def identityLaw[A](x: A)
                    (implicit m: Monoid[A]) : Boolean = {
    (m.combine(x, m.empty) == x) && (m.combine(m.empty, x) == x)
  }

  val testSet = Set(1)
  println(associativeLaw(testSet,testSet,testSet))
  println(identityLaw(testSet))
}
