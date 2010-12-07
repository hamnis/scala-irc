package net.hamnaberg.scalairc

/**
 * Created by IntelliJ IDEA.
 * User: maedhros
 * Date: Dec 6, 2010
 * Time: 12:31:27 PM
 * To change this template use File | Settings | File Templates.
 */

trait Origin extends Name

object Origin {
  def apply(origin: String) : Origin = {
    origin match {
      case x if (x contains "#") => Channel(x)
      case x if (x contains "!") => new Nick(x)
      case x => new Server(x)
    }
  }
}
