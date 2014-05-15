/**
 * Created by ljw on 4/05/14.
 */
package org.ljw


import org.specs2.mutable._

class InvSpec  extends Specification {

  "empyty should return empty" in {
    Inv.invCount(List()) mustEqual 0
  }
  "0,1,2,3 should return 0 inversions" in {
    Inv.invCount(List(0,1,2,3)) mustEqual 0
  }
  "1 0 should return 1 inversion" in {
    Inv.invCount(List(1,0)) mustEqual 1
  }

  "2 1 0 should return 3*2/2 = 3" in {
    Inv.invCount((List(2, 1, 0))) mustEqual 3
  }

  "3 2 1 0 should return 6" in {
    Inv.invCount(List(3, 2, 1, 0)) mustEqual 6
  }

  "10 reverse should return 10*9/2" in {
    Inv.invCount(List(9, 8, 7, 6, 5, 4, 3, 2, 1, 0)) mustEqual 45
  }

  "same elements aren't inversions" in {
    Inv.invCount(List(0, 0, 0, 0, 0)) mustEqual 0
  }

  "disorder" in {
    Inv.invCount(List(6, 1, 0, 5)) mustEqual 4
  }
}
