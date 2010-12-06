package net.hamnaberg.scalairc

/**
 * Created by IntelliJ IDEA.
 * User: maedhros
 * Date: Dec 6, 2010
 * Time: 12:53:34 PM
 * To change this template use File | Settings | File Templates.
 */

case class Response(origin: Origin, status: Status, remainder: List[String])
