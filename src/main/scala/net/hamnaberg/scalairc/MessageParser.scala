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
      list match {
        case List(a) => new Message(None, Command(a).get, remainder)
        case List(a, b) => {
          val origin = Origin(a)
          new Message(Some(origin), Command(b).get, remainder)
        }
        case List(a, b, _*) => {
          val server = Origin(a)
          val command = Command(b).getOrElse(Status(b.toInt))
          new Message(Some(server), command, list.drop(2) ++ remainder)
        }
        case _ => throw new IllegalArgumentException(value)
      }
    }
  }
}
