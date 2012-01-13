package net.hamnaberg.scalairc

import org.specs2.mutable.Specification

/**
 * Created by IntelliJ IDEA.
 * User: maedhros
 * Date: Dec 8, 2010
 * Time: 9:48:48 AM
 * To change this template use File | Settings | File Templates.
 */

class MessageTest extends Specification {
  "A Message" should {
	"when parsed yield the same result" in {
	    val expected = "USER guest 0 * :Ronnie Reagan\r\n"
	    val actual = Message(expected).format
		actual must not be null
	    actual shouldEqual(expected)
	  }	
  }
}  
