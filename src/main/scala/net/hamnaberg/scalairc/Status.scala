package net.hamnaberg.scalairc

/**
 * Created by IntelliJ IDEA.
 * User: maedhros
 * Date: Dec 1, 2010
 * Time: 9:12:18 PM
 * To change this template use File | Settings | File Templates.
 */

abstract sealed class Status(val code: Int, val name: String) extends NameAndFormat {
  def format = {
    code match {
      case x if (x < 10) => "00" + x
      case x if (x < 100) => "0" + x
      case x => x.toString
    }
  }

  override def equals(any: Any) = any match {
    case Status(code, name) => this.code == code && name == this.name
  }

  override def hashCode() = name.hashCode() + code
}

object Status {
  lazy val statuses = load()

  def apply(code: Int) = {
    statuses.find(x => x.code == code).getOrElse(new Unknown(code))
  }

  def unapply(status: Status): Option[(Int, String)] = {
    Some(status.code, status.name)
  }

  private def load() = {
    Set(RPL_WELCOME, RPL_YOURHOST, RPL_CREATED, RPL_MYINFO, RPL_BOUNCE, RPL_ENDOFWHOWAS, RPL_TRACELINK,
      RPL_USERHOST, RPL_NOTOPIC, ERR_CHANOPRIVSNEEDED, RPL_ADMINEMAIL,
      ERR_USERNOTINCHANNEL, RPL_TRACESERVER, RPL_WHOREPLY, ERR_NEEDMOREPARAMS, ERR_ALREADYREGISTRED, RPL_TRACEUSER,
      RPL_TRACECONNECTING, RPL_NAMREPLY, ERR_NOTONCHANNEL, RPL_STATSCLINE, RPL_WHOISSERVER, RPL_ENDOFINFO, RPL_LISTEND,
      RPL_LOCAL_USERS, RPL_GLOBAL_USERS, ERR_YOUREBANNEDCREEP, RPL_STATSILINE, ERR_FILEERROR, RPL_TRACEOPERATOR,
      ERR_KEYSET, ERR_USERONCHANNEL, RPL_ENDOFSTATS, RPL_ADMINLOC2, ERR_WASNOSUCHNICK, RPL_REHASHING, RPL_VERSION,
      ERR_USERSDONTMATCH, RPL_STATSLINKINFO, RPL_USERSSTART, RPL_ENDOFWHOIS, RPL_STATSOLINE, ERR_NOTEXTTOSEND,
      RPL_UNAWAY, RPL_ENDOFNAMES, ERR_NOPERMFORHOST, ERR_TOOMANYTARGETS, ERR_NOORIGIN, RPL_NOUSERS, RPL_STATSKLINE,
      RPL_CHANNELMODEIS, ERR_TOOMANYCHANNELS, RPL_STATSNLINE, RPL_TRACELOG, ERR_NORECIPIENT, RPL_YOUREOPER,
      RPL_BANLIST, RPL_WHOISIDLE, RPL_MOTD, RPL_WHOISUSER, ERR_NICKCOLLISION, RPL_WHOWASUSER, RPL_LIST,
      ERR_CANTKILLSERVER, RPL_STATSYLINE, RPL_ENDOFMOTD, RPL_UMODEIS, ERR_NOADMININFO, ERR_UNKNOWNMODE,
      RPL_ENDOFWHO, RPL_LINKS, RPL_USERS, ERR_NOOPERHOST, ERR_CHANNELISFULL, RPL_LUSERUNKNOWN, RPL_AWAY,
      ERR_NOTOPLEVEL, ERR_INVITEONLYCHAN, RPL_NONE, RPL_LUSERCLIENT, RPL_ADMINME, ERR_CANNOTSENDTOCHAN,
      ERR_SUMMONDISABLED, ERR_BANNEDFROMCHAN, ERR_NICKNAMEINUSE, RPL_ISON, RPL_STATSUPTIME, RPL_MOTDSTART,
      RPL_TRACENEWTYPE, ERR_UNKNOWNCOMMAND, RPL_INFO, RPL_LISTSTART, ERR_NOSUCHSERVER, ERR_USERSDISABLED,
      RPL_WHOISCHANNELS, RPL_LUSERCHANNELS, ERR_NOLOGIN, RPL_ENDOFUSERS, ERR_NOMOTD, RPL_TIME, RPL_ENDOFLINKS,
      RPL_ADMINLOC1, RPL_TRACEUNKNOWN, ERR_BADCHANNELKEY, ERR_NOPRIVILEGES, ERR_ERRONEUSNICKNAME, ERR_PASSWDMISMATCH,
      RPL_STATSLLINE, ERR_NONICKNAMEGIVEN, RPL_LUSEROP, RPL_NOWAWAY, ERR_NOSUCHCHANNEL, RPL_TRACEHANDSHAKE,
      RPL_INVITING, RPL_TOPIC, RPL_SUMMONING, ERR_UMODEUNKNOWNFLAG, ERR_NOTREGISTERED, RPL_WHOISOPERATOR,
      RPL_STATSHLINE, RPL_ENDOFBANLIST, ERR_NOSUCHNICK, RPL_STATSCOMMANDS, ERR_WILDTOPLEVEL, RPL_LUSERME)
  }

  case class Unknown(override val code: Int) extends Status(code, "Unkown")

  case object RPL_WELCOME extends Status(1, "RPL_WELCOME")

  case object RPL_YOURHOST extends Status(2, "RPL_YOURHOST")

  case object RPL_CREATED extends Status(3, "RPL_CREATED")

  case object RPL_MYINFO extends Status(4, "RPL_MYINFO")

  case object RPL_BOUNCE extends Status(5, "RPL_BOUNCE")

  case object ERR_CANTKILLSERVER extends Status(483, "ERR_CANTKILLSERVER")

  case object RPL_CHANNELMODEIS extends Status(324, "RPL_CHANNELMODEIS")

  case object ERR_CHANOPRIVSNEEDED extends Status(482, "ERR_CHANOPRIVSNEEDED")

  case object RPL_LISTEND extends Status(323, "RPL_LISTEND")

  case object ERR_NOPRIVILEGES extends Status(481, "ERR_NOPRIVILEGES")

  case object RPL_LIST extends Status(322, "RPL_LIST")

  case object RPL_LISTSTART extends Status(321, "RPL_LISTSTART")

  case object RPL_REHASHING extends Status(382, "RPL_REHASHING")

  case object RPL_YOUREOPER extends Status(381, "RPL_YOUREOPER")

  case object RPL_UMODEIS extends Status(221, "RPL_UMODEIS")

  case object ERR_WILDTOPLEVEL extends Status(414, "ERR_WILDTOPLEVEL")

  case object ERR_NOTOPLEVEL extends Status(413, "ERR_NOTOPLEVEL")

  case object RPL_WHOISCHANNELS extends Status(319, "RPL_WHOISCHANNELS")

  case object ERR_NOTEXTTOSEND extends Status(412, "ERR_NOTEXTTOSEND")

  case object RPL_ENDOFWHOIS extends Status(318, "RPL_ENDOFWHOIS")

  case object ERR_NORECIPIENT extends Status(411, "ERR_NORECIPIENT")

  case object RPL_WHOISIDLE extends Status(317, "RPL_WHOISIDLE")

  case object ERR_BADCHANNELKEY extends Status(475, "ERR_BADCHANNELKEY")

  case object ERR_BANNEDFROMCHAN extends Status(474, "ERR_BANNEDFROMCHAN")

  case object RPL_ENDOFWHO extends Status(315, "RPL_ENDOFWHO")

  case object ERR_INVITEONLYCHAN extends Status(473, "ERR_INVITEONLYCHAN")

  case object RPL_WHOWASUSER extends Status(314, "RPL_WHOWASUSER")

  case object ERR_UNKNOWNMODE extends Status(472, "ERR_UNKNOWNMODE")

  case object RPL_ENDOFSTATS extends Status(219, "RPL_ENDOFSTATS")

  case object RPL_WHOISOPERATOR extends Status(313, "RPL_WHOISOPERATOR")

  case object ERR_CHANNELISFULL extends Status(471, "ERR_CHANNELISFULL")

  case object RPL_STATSYLINE extends Status(218, "RPL_STATSYLINE")

  case object RPL_WHOISSERVER extends Status(312, "RPL_WHOISSERVER")

  case object RPL_WHOISUSER extends Status(311, "RPL_WHOISUSER")

  case object RPL_ENDOFMOTD extends Status(376, "RPL_ENDOFMOTD")

  case object RPL_STATSKLINE extends Status(216, "RPL_STATSKLINE")

  case object RPL_MOTDSTART extends Status(375, "RPL_MOTDSTART")

  case object RPL_STATSILINE extends Status(215, "RPL_STATSILINE")

  case object RPL_ENDOFINFO extends Status(374, "RPL_ENDOFINFO")

  case object RPL_STATSNLINE extends Status(214, "RPL_STATSNLINE")

  case object RPL_STATSCLINE extends Status(213, "RPL_STATSCLINE")

  case object RPL_MOTD extends Status(372, "RPL_MOTD")

  case object RPL_STATSCOMMANDS extends Status(212, "RPL_STATSCOMMANDS")

  case object RPL_INFO extends Status(371, "RPL_INFO")

  case object RPL_STATSLINKINFO extends Status(211, "RPL_STATSLINKINFO")

  case object ERR_NOORIGIN extends Status(409, "ERR_NOORIGIN")

  case object ERR_USERSDONTMATCH extends Status(502, "ERR_USERSDONTMATCH")

  case object ERR_UMODEUNKNOWNFLAG extends Status(501, "ERR_UMODEUNKNOWNFLAG")

  case object ERR_TOOMANYTARGETS extends Status(407, "ERR_TOOMANYTARGETS")

  case object ERR_WASNOSUCHNICK extends Status(406, "ERR_WASNOSUCHNICK")

  case object ERR_TOOMANYCHANNELS extends Status(405, "ERR_TOOMANYCHANNELS")

  case object ERR_CANNOTSENDTOCHAN extends Status(404, "ERR_CANNOTSENDTOCHAN")

  case object ERR_NOSUCHCHANNEL extends Status(403, "ERR_NOSUCHCHANNEL")

  case object ERR_KEYSET extends Status(467, "ERR_KEYSET")

  case object ERR_NOSUCHSERVER extends Status(402, "ERR_NOSUCHSERVER")

  case object ERR_NOSUCHNICK extends Status(401, "ERR_NOSUCHNICK")

  case object ERR_YOUREBANNEDCREEP extends Status(465, "ERR_YOUREBANNEDCREEP")

  case object RPL_NOWAWAY extends Status(306, "RPL_NOWAWAY")

  case object ERR_PASSWDMISMATCH extends Status(464, "ERR_PASSWDMISMATCH")

  case object RPL_UNAWAY extends Status(305, "RPL_UNAWAY")

  case object ERR_NOPERMFORHOST extends Status(463, "ERR_NOPERMFORHOST")

  case object RPL_ENDOFWHOWAS extends Status(369, "RPL_ENDOFWHOWAS")

  case object ERR_ALREADYREGISTRED extends Status(462, "ERR_ALREADYREGISTRED")

  case object RPL_ISON extends Status(303, "RPL_ISON")

  case object RPL_ENDOFBANLIST extends Status(368, "RPL_ENDOFBANLIST")

  case object ERR_NEEDMOREPARAMS extends Status(461, "ERR_NEEDMOREPARAMS")

  case object RPL_TRACENEWTYPE extends Status(208, "RPL_TRACENEWTYPE")

  case object RPL_USERHOST extends Status(302, "RPL_USERHOST")

  case object RPL_BANLIST extends Status(367, "RPL_BANLIST")

  case object RPL_AWAY extends Status(301, "RPL_AWAY")

  case object RPL_ENDOFNAMES extends Status(366, "RPL_ENDOFNAMES")

  case object RPL_TRACESERVER extends Status(206, "RPL_TRACESERVER")

  case object RPL_NONE extends Status(300, "RPL_NONE")

  case object RPL_ENDOFLINKS extends Status(365, "RPL_ENDOFLINKS")

  case object RPL_TRACEUSER extends Status(205, "RPL_TRACEUSER")

  case object RPL_LINKS extends Status(364, "RPL_LINKS")

  case object RPL_TRACEOPERATOR extends Status(204, "RPL_TRACEOPERATOR")

  case object RPL_TRACEUNKNOWN extends Status(203, "RPL_TRACEUNKNOWN")

  case object RPL_TRACEHANDSHAKE extends Status(202, "RPL_TRACEHANDSHAKE")

  case object RPL_TRACECONNECTING extends Status(201, "RPL_TRACECONNECTING")

  case object RPL_TRACELINK extends Status(200, "RPL_TRACELINK")

  case object RPL_TRACELOG extends Status(261, "RPL_TRACELOG")

  case object ERR_NOTREGISTERED extends Status(451, "ERR_NOTREGISTERED")

  case object RPL_ADMINEMAIL extends Status(259, "RPL_ADMINEMAIL")

  case object RPL_NAMREPLY extends Status(353, "RPL_NAMREPLY")

  case object RPL_ADMINLOC2 extends Status(258, "RPL_ADMINLOC2")

  case object RPL_WHOREPLY extends Status(352, "RPL_WHOREPLY")

  case object RPL_ADMINLOC1 extends Status(257, "RPL_ADMINLOC1")

  case object RPL_VERSION extends Status(351, "RPL_VERSION")

  case object RPL_ADMINME extends Status(256, "RPL_ADMINME")

  case object RPL_LUSERME extends Status(255, "RPL_LUSERME")

  case object RPL_LUSERCHANNELS extends Status(254, "RPL_LUSERCHANNELS")

  case object RPL_LUSERUNKNOWN extends Status(253, "RPL_LUSERUNKNOWN")

  case object RPL_LUSEROP extends Status(252, "RPL_LUSEROP")

  case object RPL_LUSERCLIENT extends Status(251, "RPL_LUSERCLIENT")

  case object RPL_LOCAL_USERS extends Status(265, "RPL_LOCAL_USERS")

  case object RPL_GLOBAL_USERS extends Status(266, "RPL_GLOBAL_USERS")

  case object ERR_USERSDISABLED extends Status(446, "ERR_USERSDISABLED")

  case object ERR_SUMMONDISABLED extends Status(445, "ERR_SUMMONDISABLED")

  case object ERR_NOLOGIN extends Status(444, "ERR_NOLOGIN")

  case object ERR_USERONCHANNEL extends Status(443, "ERR_USERONCHANNEL")

  case object ERR_NOTONCHANNEL extends Status(442, "ERR_NOTONCHANNEL")

  case object ERR_USERNOTINCHANNEL extends Status(441, "ERR_USERNOTINCHANNEL")

  case object RPL_SUMMONING extends Status(342, "RPL_SUMMONING")

  case object RPL_INVITING extends Status(341, "RPL_INVITING")

  case object RPL_STATSHLINE extends Status(244, "RPL_STATSHLINE")

  case object RPL_STATSOLINE extends Status(243, "RPL_STATSOLINE")

  case object RPL_STATSUPTIME extends Status(242, "RPL_STATSUPTIME")

  case object RPL_STATSLLINE extends Status(241, "RPL_STATSLLINE")

  case object ERR_NICKCOLLISION extends Status(436, "ERR_NICKCOLLISION")

  case object ERR_NICKNAMEINUSE extends Status(433, "ERR_NICKNAMEINUSE")

  case object ERR_ERRONEUSNICKNAME extends Status(432, "ERR_ERRONEUSNICKNAME")

  case object ERR_NONICKNAMEGIVEN extends Status(431, "ERR_NONICKNAMEGIVEN")

  case object ERR_NOOPERHOST extends Status(491, "ERR_NOOPERHOST")

  case object RPL_TOPIC extends Status(332, "RPL_TOPIC")

  case object RPL_NOTOPIC extends Status(331, "RPL_NOTOPIC")

  case object RPL_NOUSERS extends Status(395, "RPL_NOUSERS")

  case object RPL_ENDOFUSERS extends Status(394, "RPL_ENDOFUSERS")

  case object RPL_USERS extends Status(393, "RPL_USERS")

  case object RPL_USERSSTART extends Status(392, "RPL_USERSSTART")

  case object RPL_TIME extends Status(391, "RPL_TIME")

  case object ERR_FILEERROR extends Status(424, "ERR_FILEERROR")

  case object ERR_NOADMININFO extends Status(423, "ERR_NOADMININFO")

  case object ERR_NOMOTD extends Status(422, "ERR_NOMOTD")

  case object ERR_UNKNOWNCOMMAND extends Status(421, "ERR_UNKNOWNCOMMAND")

}