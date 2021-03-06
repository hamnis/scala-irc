package net.hamnaberg.scalairc.client

import java.net.{InetSocketAddress}
import net.hamnaberg.scalairc._
import Command._
import Status._

/**
 * Created by IntelliJ IDEA.
 * User: maedhros
 * Date: Dec 1, 2010
 * Time: 4:56:45 PM
 * To change this template use File | Settings | File Templates.
 */

class Client private(private val connector: Connector, val listeners: List[MessageListener]) {
  private val reader = connector.reader
  private implicit val writer = connector.writer

  def login(user: User) = {
    try {
      println("Connected, now logging in.")
      writer.write(new Message(None, NICK, List(user.nick)))
      val invisible = if (user.invisible) "8" else "0"
      writer.write(new Message(None, USER, List(user.nick, invisible + " * : " + user.name)))
      if (isLoggedIn(reader)) {
        start
      }
    }
    finally connector.close
  }

  private def start() {
    for (message <- reader) {
      message match {
        case Ping(y) => {
          println("Answering to PING with a PONG")
          writer.write(Pong(y))
          println("Answered to PING with a PONG")
        }
        case x => listeners.foreach(_.onMessage(message))
      }
    }
  }

  private def isLoggedIn(lines: Iterator[Message]): Boolean = {
    for (message <- lines) {
      listeners.foreach(_.onMessage(message))
      message match {
        case Message(_, RPL_MYINFO, _, _) => return true
        case Message(_, ERR_NICKNAMEINUSE, _, _) => return false
        case _ =>
      }
    }
    false
  }
}

object Client {
  def apply(host: String, port: Int = 6667, timeout: Int = 20000, listeners: List[MessageListener] = List(MessagePrinter)) = {
    val address = new InetSocketAddress(host, port)
    val connector = new SocketConnector(address, timeout)
    if (!connector.connect) {
      throw new IllegalStateException("Unable to connect to " + address)
    }

    new Client(connector, listeners)
  }

  def main(args: Array[String]) {
    val client = Client("arrakis.bouvet.no", listeners = List(MessagePrinter, new Joiner(Channel("hei"))))
    client.login(User("Ngarthl", "Erlend Hamnaberg"))
  }
}
