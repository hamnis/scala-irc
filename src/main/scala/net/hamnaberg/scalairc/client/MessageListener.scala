package net.hamnaberg.scalairc.client

import net.hamnaberg.scalairc.Message

/**
 * Created by IntelliJ IDEA.
 * User: maedhros
 * Date: Dec 6, 2010
 * Time: 3:28:33 PM
 * To change this template use File | Settings | File Templates.
 */

trait MessageListener {
  def onMessage(message: Message)(implicit writer: MessageWriter)
}
