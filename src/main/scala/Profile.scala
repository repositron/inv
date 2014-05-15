/**
 * From Scala Functional Programming book
 */

package org.ljw
import System.{currentTimeMillis => _time}
object Profile {
  def profile[R](code: => R, t: Long = _time) = (code, _time - t)
}
