package net.hamnaberg.scalairc

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec

/**
 * Created by IntelliJ IDEA.
 * User: maedhros
 * Date: Dec 8, 2010
 * Time: 9:48:48 AM
 * To change this template use File | Settings | File Templates.
 */

class MessageTest extends FlatSpec with ShouldMatchers {
  "A Message" should "when parsed yield the same result" in {
    val expected = "USER guest 0 * :Ronnie Reagan\r\n"
    val actual = Message(expected).format
    actual should equal (expected)
  }
}
