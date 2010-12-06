package net.hamnaberg.scalairc.client

import net.hamnaberg.scalairc.Message

/**
 * Created by IntelliJ IDEA.
 * User: maedhros
 * Date: Dec 6, 2010
 * Time: 8:06:42 PM
 * To change this template use File | Settings | File Templates.
 */

class MessageReader(delegate: Iterator[String]) extends Iterator[Message] {
  def next() = Message(delegate.next)

  def hasNext = delegate.hasNext
}
