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
      val message = parts match {
        case List(a) => transform(extractParameters(a), None)
        case List(a, b) => transform(extractParameters(a), Some(b))
      }
      message match {
        case None => throw new IllegalArgumentException(value)
        case Some(x) => x
      }
    }

    private def extractParameters(item: String) = {
      Some(item).map(x => x.split(" ").map(_.trim).toList).getOrElse(Nil)
    }

    private def transform(list: List[String], text: Option[String]) = {
      list match {
        case List(a) => Some(Message(None, Command(a).get, Nil, text))
        case List(a, b) => {
          val origin = Origin(a)
          val command = Command(b).getOrElse(Status(b.toInt))
          command match {
            case PING => println(origin); println(command); println(text)
            case _ => 
          }
          Some(Message(Some(origin), command, Nil, text))
        }
        case List(a, b, _*) => {
          val server = Origin(a)
          val command = Command(b).getOrElse(Status(b.toInt))
          Some(Message(Some(server), command, list.drop(2), text))
        }
        case _ => None
      }
    }
  }
}
