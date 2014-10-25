package org.wennergr.fp

import org.scalatest.{FreeSpec, Matchers, FlatSpec, FunSuite}
import org.wennnergr.fp.PatternsAndRecurrsion


class PatternsAndRecurrsionTest extends FreeSpec with Matchers {

  "the length function" - {
    "should return the length of a string" in {
      PatternsAndRecurrsion.length(List(1,2,3,4,5)) should be(5)
    }
    "it should return 0 if the list is empty" in {
      PatternsAndRecurrsion.length(List()) should be (0)
    }

  }

  "the take function" - {
    "Should return the first N element from a list" in {
      PatternsAndRecurrsion.take(List(1,2,3,4),2) should be(List(1,2))
    }
  }

  "the takeWhile function" - {
    "Should return the elements while expression is true" in {
      PatternsAndRecurrsion.takeWhile(List(1,2,3,4))(x => x < 4) should be(List(1,2,3))
    }
  }

  // flatten(List(List(1, 1), 2, List(3, List(5, 8))))
  // List(1, 1, 2, 3, 5, 8)
  "the flatten function" - {
    "Should flatten all the things!" in {
      PatternsAndRecurrsion.flatten(List(List(1, 1), List(2), List(3, List(5, 8)))) should be (List(1, 1, 2, 3, 5, 8))
    }
  }
}
