/**
 * Created by ljw on 10/05/14.
 */
package org.ljw.five2

import scala.Some
import scala.Option

sealed trait Stream[+A]
case object Empty extends Stream[Nothing]
case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]
//case class Some[+A](h: () => A) extends Stream[A]

object Stream {
  def cons[A](hd: => A, tl: => Stream[A]) : Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }
  def empty[A]: Stream[A] = Empty
  def apply[A](as: A*): Stream[A] = if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))
  def headOption: Option = this match {
    case Empty => None
    case Cons(h, t) => Some(h())
  }
  def cons[A](hd: => A, t1: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = t1
    Cons(() => head, () => tail)
  }
}

