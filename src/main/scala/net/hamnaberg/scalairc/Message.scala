package net.hamnaberg.scalairc

/**
 * <message>  ::= [':' <prefix> <SPACE> ] <command> <params> <crlf>
 * <prefix>   ::= <servername> | <nick> [ '!' <user> ] [ '@' <host> ]
 * <command>  ::= <letter>  { <letter> } | <number> <number> <number>
 * <SPACE>    ::= ' '  { ' ' }
 * <params>   ::= <SPACE> [ ':' <trailing> | <middle> <params> ]
 *
 * <middle>   ::= <Any *non-empty* sequence of octets not including SPACE
 *              or NUL or CR or LF, the first of which may not be ':'>
 * <trailing> ::= <Any, possibly *empty*, sequence of octets not including
 *                 NUL or CR or LF>
 *
 * <crlf>     ::= CR LF
 *
 */
class Message(val origin: Option[Origin], val command: NameAndFormat, val arguments: List[String], val text: Option[String] = None) extends Format {

  def format = {
    val list = text.map(":%s".format(_)).getOrElse("") :: arguments.mkString(" ", " ", " ") :: command.format ::
            origin.map(x => ":%s ".format(x.name)).getOrElse("") :: Nil
    list.reverse.mkString("","", "\r\n")
  }
  
  override def toString = "Message(%s, %s, %s)".format(origin, command, arguments)
}

object Message {
  def apply(text: String) = {
    val message = MessageParser().parse(text)
    message match {
      case Message(None, PING, Nil, Some(y)) => Ping(y)
      case x => x
    }
  }

  def apply(origin: Option[Origin], command: NameAndFormat, arguments: List[String], text: Option[String]) = {
    command match {
      case PING => Ping(text.get)
      case x =>  new Message(origin, command, arguments, text)
    }
  }

  def unapply(message: Message) : Option[(Option[Origin], NameAndFormat, List[String], Option[String])] = {
    Some(message.origin, message.command, message.arguments, message.text)
  }
}

case class Ping(private val t: String) extends Message(None, PING, Nil, Some(t))
case class Pong(private val t: String) extends Message(None, PONG, Nil, Some(t))
