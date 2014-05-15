package org.ljw

import scala.annotation.tailrec
import scala.collection.mutable


// @tailrec
/*
def splitCount(l: List[Int], r: List[Int], acc: Int) : (List[Int], Int) = {
  //println("splitCount: l=" + l.toString + " r=" + r.toString + " a:" + acc)
  (l, r) match {
    case (x :: xs, y :: ys) => {
      var leftTemp = List[Int]();
      var rightTemp = List[Int]();
      var accTemp = 0;
      var prependElem = 0;
      if (x <= y) {
        leftTemp = xs
        rightTemp = y::ys
        accTemp = acc
        prependElem = x
  //      val count = splitCount(xs, y :: ys, acc)
  //    (x :: count._1, count._2)
      }
      else {
        // all elements in the left array are bigger than the 1st element in the right
        leftTemp = x :: xs
        rightTemp = ys
        accTemp = acc + l.size
        prependElem = y
//            val count = splitCount(x :: xs, ys, acc + l.size)
//            (y :: count._1, count._2)
      }
      val count = splitCount(leftTemp, rightTemp, accTemp)
      (prependElem :: count._1, count._2)
    }
    case (Nil, y::ys) => (y::ys, acc)
    case (x::xs, Nil) => (x::xs, acc)
  }
}*/

object Control {
  def using[A <: { def close(): Unit }, B](resource: A)(f: A => B): B =
    try {
      f(resource)
    } finally {
      resource.close()
  }
}

object Inv2 {

}

object Inv {
  type CountList = (Long, List[Int])
  def invCount(in : List[Int]) : Long = {

    @tailrec def splitCountTailRec(l: List[Int], r: List[Int], acc: CountList) : CountList = (l, r) match {
      case (Nil, _) => (acc._1, r.reverse ::: acc._2)
      case (_, Nil) => (acc._1, l.reverse ::: acc._2)
      case (x :: xs, y :: ys) => {
        if (x <= y) splitCountTailRec(xs, r, (acc._1, x :: acc._2))
        else splitCountTailRec(l, ys, (acc._1 + l.size, y :: acc._2))
      }
    }

    def invCountHelper(in: List[Int]) : CountList = {
      //println("invCountHelper: " + in.toString)
      if (in.size < 2) (0, in)
      else {
        val l = invCountHelper(in.slice(0, in.size / 2))
        val r  = invCountHelper(in.slice(in.size / 2, in.size))
        val c = splitCountTailRec(l._2, r._2, (0, List()))
        (l._1 + r._1 + c._1, c._2.reverse)
      }
    }
    val end = invCountHelper(in)
    end._1
  }

}
object Main extends App {
  var integerVals = mutable.MutableList[Int]();

  Control.using(io.Source.fromFile(args(0))) { source => {
      for (line <- source.getLines()) {
        integerVals += line.toInt
      }
    }
  }
  val iVals = integerVals.toList

  val r1 = Profile.profile(Inv.invCount(iVals))
  println("Answer: " + r1._1)
  println("Profile: " + r1._2 + "ms")
}

object Merge {
  def mergeSort(in: List[Int]): List[Int] = {
    def merge(l: List[Int], r: List[Int]) : List[Int] = (l, r) match {
      case (x::xs, y::ys) => if (x < y) x:: merge(xs, y::ys) else y::merge(x::xs, ys)
      case (Nil, ys) => ys
      case (xs, Nil) => xs
    }
    if (in.size < 2) in
    else {
      val l = mergeSort(in.slice(0, in.size / 2))
      val r = mergeSort(in.slice(in.size / 2, in.size))
      merge(l, r)
    }


  }

}