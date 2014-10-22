package org.wennergr.fp

import org.scalatest._

class NintyNineTest extends FlatSpec with Matchers {

  "The last function" should "return the last element in a list" in {
    NintyNine.last(List(1,2,3,4,5)) should be (5)
  }

  it should "throw NoSuchElementException in case the last is empty" in {
    a [NoSuchElementException] should be thrownBy {
      NintyNine.last(List())
    }
  }

  "The penultimate function" should "find the last but one element of a list" in {
    NintyNine.penultimate(List(12,3,4,5,6,8)) should be (6)
  }

  it should "throw NoSuchElementException in case of en empty list" in {
    a [NoSuchElementException] should be thrownBy {
      NintyNine.penultimate(List())
    }
  }

  it should "throw a NoSuchElementException in case of a list with one element" in {
    a [NoSuchElementException] should be thrownBy {
      NintyNine.penultimate(List(1))
    }
  }

  "The nth function" should "return the nth element of a list" in {
    NintyNine.nth(1, List(1,2,3)) should be(2)
    NintyNine.nth(0, List(1,2,3,5)) should be(1)
    NintyNine.nth(2, List(7,6,5,4,4)) should be(5)
  }

  it should "throw a NoSuchElementException in case the index position is longer then the list" in {
    a [NoSuchElementException] should be thrownBy {
      NintyNine.nth(5, List(1,2,3))
    }

    a [NoSuchElementException] should be thrownBy {
      NintyNine.nth(0, List())
    }
  }

  "The length function" should "return the amount of elements in a list" in {
    NintyNine.length(List(1,2,3,4)) should be(4)

    NintyNine.length(List()) should be(0)
  }

  "The reverse function" should "reverse a list" in {
    NintyNine.reverse(List(1,2,3)) should be(List(3,2,1))

    NintyNine.reverse(List()) should be(List())
  }

  "A list that is a palindrome" should "be the same if you reverse is" in {
    val palindrom = List(1,2,3,2,1)
    val notPalindrom = List(1,2,3,4)

    NintyNine.isPalindrome(palindrom) should be(true)
    NintyNine.isPalindrome(notPalindrom) should be(false)
  }

  it should "be considered a palindrome if the list is empty" in {
    NintyNine.isPalindrome(List()) should be(true)
  }

  "A flattern function" should "flattern nested structures" in {
    val input = List(List(1, 1), 2, List(3, List(5, 8)))
    val expected = List(1, 1, 2, 3, 5, 8)

    NintyNine.flatten(input) should be(expected)
  }

  "A dropWhile function" should "Remove elements from a list as long as an expression is true" in {
    val input = List(1,2,3,4,5,6,7,8)
    val expected = List(5,6,7,8)

    NintyNine.dropWhile(input)(_ < 5) should be(expected)
  }

  it should "return an empty list if expression is always" in {
    NintyNine.dropWhile(List(1,2,3,4))(_ < 10) should be(Nil)
  }

  it should "return an empty list if the input list is empty" in {
    NintyNine.dropWhile(Nil)(_ != null) should be(Nil)
  }

  "A compress function" should "consecutive duplicates elements" in {
    val input = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
    val expected = List('a, 'b, 'c, 'a, 'd, 'e)

    NintyNine.compress(input) should be(expected)
  }

  "A pack function" should "pack consecutive elements into sublists" in {
    val input = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
    val expected = List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e))

    NintyNine.pack(input) should be(expected)
  }

  "A span function" should "split while a boolean condition is true" in {
    val input = List('a, 'a, 'a, 'b, 'a, 'a)
    val expected = (List('a, 'a, 'a), List('b, 'a, 'a))

    NintyNine.span(input)(_ == 'a) should be(expected)
  }

  "An encode function" should "pack consecutive elements into a tuple with length and value" in {
    val input = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
    val expected = List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))

    NintyNine.encode(input) should be(expected)
  }

  "An encodeModifed function" should "behave as encode but not make tuples for entries with only one element" in {
    val input = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
    val expected = List((4,'a), 'b, (2,'c), (2,'a), 'd, (4,'e))

    NintyNine.encodeModified(input) should be(expected)
  }

  "A repeat function" should "repeat the an argument n times" in {
    NintyNine.repeat(5, 'a') should be (List('a','a','a','a','a'))
  }

  it should "return an empty list if n is equals to 0" in {
    NintyNine.repeat(0, 'a') should be(Nil)
  }

  "A decode function" should "take an encoded list and expand (decoded) the result" in {
    val input = List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))
    val expected = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)

    NintyNine.decode(input) should be(expected)
  }

  "A decode2 function" should "take an encoded list and expand (decoded) the result" in {
    val input = List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))
    val expected = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)

    NintyNine.decode(input) should be(expected)
  }

  "A duplicate function" should "duplicate all elements in the list" in {
    val input = List('a, 'b, 'c, 'c, 'd)
    val expected = List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd)

    NintyNine.duplicate(input) should be(expected)
  }

  "A duplicateN function" should "duplicate all elements in the list N times" in {
    val inputN = 3
    val inputList = List('a, 'b, 'c, 'c, 'd)
    val expected = List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd)

    NintyNine.duplicateN(inputN, inputList) should be(expected)
  }

  "The drop function" should "remove every N element from a list" in {
    val inputN = 3
    val inputList = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
    val expected = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)

    NintyNine.drop(inputN, inputList) should be(expected)
  }

  "The split function" should "split a list in two parts where the first part is of length N" in {
    val inputN = 3
    val inputList = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
    val expected = (List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k))

    NintyNine.split(inputN, inputList) should be(expected)
  }

  "The slice function" should "slice up a list of elements according to I and K" in {
    val start = 3
    val end = 7

    val input = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
    val expected =  List('d, 'e, 'f, 'g)

    NintyNine.slice(start, end, input) should be(expected)
  }

  it should "behave the same as sliceF" in {
    val start = 3
    val end = 7

    val input = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)

    NintyNine.sliceF(start, end, input) should be(NintyNine.slice(start, end, input))

  }
}
