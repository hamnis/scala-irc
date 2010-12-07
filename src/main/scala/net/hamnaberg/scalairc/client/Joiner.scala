package net.hamnaberg.scalairc.client

import net.hamnaberg.scalairc.{Channel, RPL_ENDOFMOTD, Message, JOIN}

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
      case Message(s, RPL_ENDOFMOTD, _) => writer.write(new Message(None, JOIN, List(channel.name)))
      case _ =>
    }
  }
}
