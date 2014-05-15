package org.ljw


import org.specs2.mutable._

class MergeSpec  extends Specification {
  "empty merge return empty" in {
    Merge.mergeSort(List()) mustEqual List()
  }

  "one element returns same" in {
    Merge.mergeSort(List(1)) mustEqual List(1)
  }

  "odd 3reverse" in {
    Merge.mergeSort(List(3,2,1)) mustEqual List(1, 2, 3)
  }

  "even 10 reverse" in {
    Merge.mergeSort(List(9, 8, 7, 6, 5, 4, 3, 2, 1)) mustEqual List(1, 2, 3, 4, 5, 6, 7, 8, 9)
  }


}
