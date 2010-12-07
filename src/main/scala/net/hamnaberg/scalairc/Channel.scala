package net.hamnaberg.scalairc

/**
 * Created by IntelliJ IDEA.
 * User: maedhros
 * Date: Dec 1, 2010
 * Time: 4:58:38 PM
 * To change this template use File | Settings | File Templates.
 */

class Channel private(val name: String) extends Origin with Target

object Channel {
  def apply(name: String) = new Channel(if (name startsWith "#") name else "#" + name)

  def unapply(channel: Channel) = Some(channel.name)
}
