package net.hamnaberg.scalairc

/**
 * Created by IntelliJ IDEA.
 * User: maedhros
 * Date: Dec 1, 2010
 * Time: 4:57:07 PM
 * To change this template use File | Settings | File Templates.
 */

case class User(nick: String, name: String, email: Option[Email] = None, invisible: Boolean = true) 
