package net.hamnaberg.scalairc

/**
 * Created by IntelliJ IDEA.
 * User: maedhros
 * Date: Dec 1, 2010
 * Time: 5:00:19 PM
 * To change this template use File | Settings | File Templates.
 */

sealed abstract class Role(val name: String) {
  override def equals(any: Any) = any match {
    case Role(n) => n == name
    case _ => false
  }

  override def hashCode() = name.hashCode()
}

object Role {
  def apply(name: String): Role = name match {
    case OPERATOR.name => OPERATOR
    case VOICED.name => VOICED
    case NORMAL.name => NORMAL
    case BANNED.name => BANNED
  }

  def unapply(role: Role) = Some(role.name)

  case object OPERATOR extends Role("@")

  case object VOICED extends Role("v")

  case object NORMAL extends Role("")

  case object BANNED extends Role("b")

}

