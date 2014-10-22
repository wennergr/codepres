package org.wennergr.fp

import scala.annotation.tailrec

/**
 * Solutions to the 99 Scala Problems.
 *
 * http://aperiodic.net/phil/scala/s-99/
 */
object NintyNine {
  def slice[T](start: Int, end: Int, lst: List[T]): List[T] = (start, end, lst) match {
    case (_, _, Nil)        => Nil // End of original list
    case (_, 0, _)          => Nil // Reached end of cutting
    case (0, end, x :: xs)  => x :: slice(0, end-1, xs)  // Build list until end = 0
    case (_, _, x :: xs)    => slice(start-1, end-1, xs) // Wait until start = 0
  }

  def sliceF[T](start: Int, end: Int, lst: List[T]): List[T] =
    lst drop start take(end - (start max 0))

  def split[A](i: Int, lst: List[A]) : (List[A], List[A]) = {

    @tailrec
    def _split(n: Int, left: List[A], right: List[A]): (List[A], List[A]) = (n, right) match {
      case (_, Nil) => (reverse(left), right)
      case (0, x :: xs) => (reverse(left), right)
      case (_, x :: xs) => _split(n-1, x :: left, xs)
    }

    _split(i, Nil, lst)
  }

  def drop[A](n: Int, lst: List[A]) : List[A] = {
    def _drop(i: Int, lst: List[A]) : List[A] = (i, lst) match {
      case (_, Nil)      => Nil
      case (1, x :: xs ) => _drop(n, xs)
      case (_, x :: xs ) => x :: _drop(i-1, xs)
    }

    _drop(n, lst)
  }

  def duplicateN[A](n: Int, lst: List[A]) : List[A] = lst match {
    case Nil => Nil
    case x :: xs => repeat(n, x) ::: duplicateN(n, xs)
  }

  def duplicate[A](lst: List[A]) : List[A] = duplicateN(2, lst)

  def decode[A](lst: List[(Int, A)]) : List[A] = {
    foldRight(lst)(List[A]())( (x, xs) => repeat(x._1, x._2) ::: xs)
  }

  def decode2[A](lst: List[(Int, A)]) : List[A] = lst match {
    case Nil => Nil
    case (i, v) :: xs => repeat(i, v) ::: decode2(xs)
  }

  def repeat[A](i: Int, a: A) : List[A] = {
    if (i == 0) Nil else a :: repeat(i-1, a)
  }

  def encodeModified[A](lst: List[A]): List[_] = encodeMap(lst)({
    (i: Int, v: A) => if (i == 1) v else (i,v)
  })

  def encode[A](lst: List[A]): List[(Int, A)] = encodeMap(lst)((_,_))

  def encodeMap[A,B](lst: List[A])(f: (Int, A) => B): List[B] = {
    if (lst.isEmpty) return Nil

    dropWhileWithIndex(lst)(_ == lst.head) match {
      case (i, xs) => f(i, lst.head) :: encodeMap(xs)(f)
    }
  }

  def span[A](lst: List[A])(f: A => Boolean) : (List[A], List[A]) = {

    def _span(lst: List[A], left: List[A]) : (List[A], List[A]) = lst match {
      case Nil              => (reverse(left), Nil)
      case x :: xs if f(x)  => _span(xs, x :: left)
      case xs               => (reverse(left), xs)
    }

    _span(lst, Nil)
  }

  def pack[A](lst: List[A]) : List[List[A]] = lst match {
    case Nil  => Nil
    case xs   => span(xs)(_ ==  xs.head) match {
      case (y,ys) => y :: pack(ys)
    }
  }

  def dropWhileWithIndex[A](lst: List[A])(f: A => Boolean) : (Int, List[A]) = {

    @tailrec
    def _dropWhileWithIndex(lst: List[A], i: Int) : (Int, List[A]) = lst match {
      case Nil              => (i, Nil)
      case x :: xs if f(x)  => _dropWhileWithIndex(xs, i+1)
      case xs               => (i, xs)
    }

    _dropWhileWithIndex(lst, 0)
  }

  def dropWhile[A](lst: List[A])(f: A => Boolean) : List[A] = lst match {
    case Nil              => Nil
    case x :: xs if f(x)  => dropWhile(xs)(f)
    case xs               => xs
  }

  def compress[A](lst: List[A]): List[A] = lst match {
    case Nil      => Nil
    case x :: xs  => x :: compress(dropWhile(xs)(_ == x))
  }

  def flatten(lst: List[_]) : List[_] = lst match {
    case Nil                => Nil
    case (x: List[_]) :: xs => flatten(x) ::: flatten(xs)
    case x :: xs            => x :: flatten(xs)
  }

  def isPalindrome[A](lst: List[A]) = lst == lst.reverse

  def foldLeft[A, B](lst: List[A])(zero: B)(f: (B, A) => B) : B = lst match {
    case Nil => zero
    case x :: xs => foldLeft(xs)(f(zero, x))(f)
  }

  def foldRight[A, B](lst: List[A])(zero: B)(f: (A, B) => B) : B = lst match {
    case Nil => zero
    case x :: xs => f(x, foldRight(xs)(zero)(f))
  }

  def reverse[A](lst: List[A]) : List[A] = {
    foldLeft(lst)(List[A]())( (xs, x) => x :: xs )
  }

  def length[A](lst: List[A]) : Int = lst match {
    case Nil      => 0
    case x :: xs  => 1 + length(xs)
  }

  def nth[A](i: Int, lst: List[A]) : A = lst match {
    case Nil              => throw new NoSuchElementException
    case x :: _ if i == 0 => x
    case x :: xs          => nth(i-1, xs)
  }

  def penultimate[A](lst: List[A]): A = lst match {
    case x :: _ :: Nil  => x
    case x :: xs        => penultimate(xs)
    case _              => throw new NoSuchElementException
  }

  def last[A](lst: List[A]): A = lst match {
    case Nil      => throw new NoSuchElementException
    case x :: Nil => x
    case _ :: xs  => last(xs)
  }
}
