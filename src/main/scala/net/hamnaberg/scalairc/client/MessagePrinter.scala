package net.hamnaberg.scalairc.client

import net.hamnaberg.scalairc.Message

/**
 * Created by IntelliJ IDEA.
 * User: maedhros
 * Date: Dec 6, 2010
 * Time: 3:58:39 PM
 * To change this template use File | Settings | File Templates.
 */

class MessagePrinter extends MessageListener {
  def onMessage(message: Message) = print(message.format)
}
