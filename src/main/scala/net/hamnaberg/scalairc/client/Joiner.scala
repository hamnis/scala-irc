package net.hamnaberg.scalairc.client

import net.hamnaberg.scalairc.{Channel, Message}
import net.hamnaberg.scalairc.Command._
import net.hamnaberg.scalairc.Status._

/**
 * Created by IntelliJ IDEA.
 * User: maedhros
 * Date: Dec 7, 2010
 * Time: 11:57:26 AM
 * To change this template use File | Settings | File Templates.
 */

class Joiner(channel: Channel) extends MessageListener {
  def onMessage(message: Message)(implicit writer: MessageWriter) = {
    message match {
      case Message(s, RPL_ENDOFMOTD, _, _) => writer.write(new Message(None, JOIN, List(channel.name)))
      case _ =>
    }
  }
}
