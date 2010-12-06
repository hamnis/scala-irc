package net.hamnaberg.scalairc

/**
 * Created by IntelliJ IDEA.
 * User: maedhros
 * Date: Dec 1, 2010
 * Time: 5:07:35 PM
 * To change this template use File | Settings | File Templates.
 */

abstract sealed case class Command(name: String) extends NameAndFormat {
  val format = name
}

object Command {
  lazy val commands = findCommands()
  
  def apply(name:String) = {
    commands.find(c => c.name == name)
  }

  private def findCommands() = {
    Set(JOIN, PART, MODE, TOPIC, NAMES, LIST, PASS, USER, NICK, SERVER, OPER, QUIT, SQUIT, INVITE, KICK, VERSION, STATE,
      LINKS, TIME, CONNECT, TRACE, ADMIN, INFO, PRIVMSG, NOTICE, WHO, WHOIS, WHOWAS, KILL, PING, PONG, ERROR, AWAY,
      REHASH, RESTART, SUMMON, USERS, WALLOPS, USERHOST, ISON)
  }
}

case object JOIN extends Command("JOIN")
case object PART extends Command("PART")
case object MODE extends Command("MODE")
case object TOPIC extends Command("TOPIC")
case object NAMES extends Command("NAMES")
case object LIST extends Command("LIST")
case object PASS extends Command("PASS")
case object USER extends Command("USER")
case object NICK extends Command("NICK")
case object SERVER extends Command("SERVER")
case object OPER extends Command("OPER")
case object QUIT extends Command("QUIT")
case object SQUIT extends Command("SQUIT")
case object INVITE extends Command("INVITE")
case object KICK extends Command("KICK")
case object VERSION extends Command("VERSION")
case object STATE extends Command("STATE")
case object LINKS extends Command("LINKS")
case object TIME extends Command("TIME")
case object CONNECT extends Command("CONNECT")
case object TRACE extends Command("TRACE")
case object ADMIN extends Command("ADMIN")
case object INFO extends Command("INFO")
case object PRIVMSG extends Command("PRIVMSG")
case object NOTICE extends Command("NOTICE")
case object WHO extends Command("WHO")
case object WHOIS extends Command("WHOIS")
case object WHOWAS extends Command("WHOWAS")
case object KILL extends Command("KILL")
case object PING extends Command("PING")
case object PONG extends Command("PONG")
case object ERROR extends Command("ERROR")
case object AWAY extends Command("AWAY")
case object REHASH extends Command("REHASH")
case object RESTART extends Command("RESTART")
case object SUMMON extends Command("SUMMON")
case object USERS extends Command("USERS")
case object WALLOPS extends Command("WALLOPS")
case object USERHOST extends Command("USERHOST")
case object ISON extends Command("ISON")
