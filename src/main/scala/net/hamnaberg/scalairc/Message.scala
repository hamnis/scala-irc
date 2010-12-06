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
class Message(val origin: Option[Origin], val command: NameAndFormat, val arguments: List[String]) {

  def format = {
    val builder = new StringBuilder()
    origin.foreach(x => builder.append(":").append(x.name).append(" "))
    builder.append(command.format)
    builder.append(arguments.mkString(" "," ", "\r\n"))
    builder.toString
  }
}

case class Ping(override val arguments: List[String]) extends Message(None, PING, arguments)
case class Pong(override val arguments: List[String]) extends Message(None, PONG, arguments)

object Message {
  def apply(text: String) = {
    val message = MessageParser().parse(text)
    message match {
      case Message(None, PING, y) => Ping(y)
      case x => x
    }
  }

  def apply(origin: Option[Origin], command: NameAndFormat, arguments: List[String]) = {
    command match {
      case PING => Ping(arguments) 
      case x =>  new Message(origin, command, arguments)
    }
  }

  def unapply(message: Message) : Option[(Option[Origin], NameAndFormat, List[String])] = {
    Some(message.origin, message.command, message.arguments)
  }
}
