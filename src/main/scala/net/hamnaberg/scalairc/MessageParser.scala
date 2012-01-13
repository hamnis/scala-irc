package net.hamnaberg.scalairc

/**
 * Created by IntelliJ IDEA.
 * User: maedhros
 * Date: Dec 6, 2010
 * Time: 10:23:24 AM
 * To change this template use File | Settings | File Templates.
 */

trait MessageParser {
  def parse(source: String): Message
}

object MessageParser {
  private val instance: MessageParser = new DefaultMessageParser()

  def apply() = instance

  private class DefaultMessageParser extends MessageParser {
    def parse(source: String): Message = {
      val hasOrigin = source.startsWith(":")
      val value = (if (hasOrigin) source.substring(1) else source).trim
      val parts = value.split(":", 2).toList
      val message = parts match {
        case List(a) => transform(hasOrigin, extractParameters(a), None)
        case List(a, b) => transform(hasOrigin, extractParameters(a), Some(b))
        case Nil => None
      }
      message match {
        case None => throw new IllegalArgumentException(value)
        case Some(x) => x
      }
    }

    private def extractParameters(item: String) = {
      Some(item).map(x => x.split(" ").map(_.trim).toList).getOrElse(Nil)
    }

    private def transform(hasOrigin: Boolean, list: List[String], text: Option[String]) = {
      list match {
        case List(a) => Some(Message(None, Command(a).get, Nil, text))
        case List(a, b) if (hasOrigin) => {
          val origin = Origin(a)
          val command = Command(b).getOrElse(Status(b.toInt))
          Some(Message(Some(origin), command, Nil, text))
        }
        case List(a, b, _*) if (hasOrigin) => {
          val server = Origin(a)
          val command = Command(b).getOrElse(Status(b.toInt))
          Some(Message(Some(server), command, list.drop(2), text))
        }
        case List(a, _*) => {
          val command = Command(a).getOrElse(Status(a.toInt))
          Some(Message(None, command, list.drop(1), text))
        }
        case _ => None
      }
    }
  }

}
