package net.hamnaberg.scalairc

/**
 * Created by IntelliJ IDEA.
 * User: maedhros
 * Date: Dec 6, 2010
 * Time: 10:23:24 AM
 * To change this template use File | Settings | File Templates.
 */

trait MessageParser {
  def parse(source: String) : Message
}

object MessageParser {
  private val instance : MessageParser = new DefaultMessageParser()

  def apply() = instance

  private class DefaultMessageParser extends MessageParser {
    def parse(source: String) : Message = {
      val value = if (source.startsWith(":")) source.substring(1) else source
      val parts = value.split(":", 2).toList
      val list = parts.headOption.map(x => x.split(" ").map(_.trim).toList).getOrElse(Nil)
      val remainder = parts.drop(1)
      if (list.length == 1) {
        return new Message(None, Command(list.head).get, remainder)
      }
      if (list.length >= 3) {
        val server = new Server(list.head)
        val nameable = list(1)
        val command = Command(nameable).getOrElse(Status(nameable.toInt))
        return new Message(Some(server), command, list.drop(2) ++ remainder)
      }
      else {
        return null
      }
    }
  }
}
