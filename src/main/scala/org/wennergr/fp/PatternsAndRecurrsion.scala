package org.wennnergr.fp

object PatternsAndRecurrsion {

  def length[T](lst: List[T]): Int = lst match {
    case Nil      => 0
    case x :: xs  => 1 + length(xs)
  }

  def take[T](lst: List[T], n: Int): List[T] = lst match {
    case x :: xs if n > 0  => x :: take(xs, n - 1)
    case _ => List()
  }

// takeWhile(List(1,2,3,4,5,6)( x => x < 4)
  def takeWhile[T](lst: List[T])(f: T => Boolean): List[T] = {
    lst match {
      case x :: xs if f(x) => x :: takeWhile(xs)(f)
      case _ => List()
    }
  }

  def takeWhileNoCurry[T](lst: List[T],f: T => Boolean): List[T] = {
    lst match {
      case x :: xs if f(x) => x :: takeWhileNoCurry(xs, f)
      case _ => List()
    }
  }

  def flatten(lst: List[_]): List[_] = lst match {
    case (x: List[_]) :: xs => flatten(x) ::: flatten(xs)
    case x :: xs => x :: flatten(xs)
    case _ => Nil
  }
}
