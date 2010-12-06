package net.hamnaberg.scalairc

/**
 * Created by IntelliJ IDEA.
 * User: maedhros
 * Date: Dec 1, 2010
 * Time: 5:00:19 PM
 * To change this template use File | Settings | File Templates.
 */

sealed abstract case class Role(id: String)

case object OPERATOR extends Role("@")
case object VOICED extends Role("v")
case object NORMAL extends Role("")
case object BANNED extends Role("b")
