package net.hamnaberg.scalairc

/**
 * Created by IntelliJ IDEA.
 * User: maedhros
 * Date: Dec 1, 2010
 * Time: 4:58:38 PM
 * To change this template use File | Settings | File Templates.
 */

case class Channel(name: String, users: Map[Role, List[User]], modes: List[Mode]) extends Origin
