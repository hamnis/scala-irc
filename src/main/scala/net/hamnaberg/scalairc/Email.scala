package net.hamnaberg.scalairc

/**
 * Created by IntelliJ IDEA.
 * User: maedhros
 * Date: Dec 1, 2010
 * Time: 5:02:31 PM
 * To change this template use File | Settings | File Templates.
 */

case class Email(id: String, server: String)

object Email {
  def apply(value: String) = {
    require(value != null)
    require(value contains "@")
    val parts = value.split('@')
    new Email(parts(0), parts(1)) //TODO: naiive, should probably be better.
  }
}
