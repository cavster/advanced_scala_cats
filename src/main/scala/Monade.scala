
object testPure  extends App{

  import cats.Id
  def pure[A](value: A): Id[A] = value

  def map[A, B](initial: Id[A])(func: A => B): Id[B] = {
    func(initial)
  }
  def flatMap[A, B](initial: Id[A])(func: A => Id[B]): Id[B] =
    func(initial)


  println(pure(5))
  println(map(5)(_ * 2))
  println(flatMap(5)(_ * 5))
}

