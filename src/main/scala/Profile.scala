/**
 * Created by ljw on 6/05/14.
 */

package org.ljw
import System.{currentTimeMillis => _time}
object Profile {
  def profile[R](code: => R, t: Long = _time) = (code, _time - t)
}
