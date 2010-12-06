package net.hamnaberg.scalairc.client

import java.net.{InetSocketAddress, Socket}
import io.Source
import java.io.{OutputStreamWriter, BufferedWriter}
import net.hamnaberg.scalairc._

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
        case Ping(List(y)) => {
          println("Answering to PING with a PONG")
          writer.write(Pong(List(y)))
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
        case Message(_, RPL_MYINFO, _) => return true
        case Message(_, ERR_NICKNAMEINUSE, _) => return false
        case _ =>
      }
    }
    false
  }
}

object Client {
  def apply(host: String, port: Int = 6667, timeout: Int = 20000, listeners: List[MessageListener] = List(new MessagePrinter)) = {
    val address = new InetSocketAddress(host, port)
    val connector = new SocketConnector(new Socket(), address, timeout)
    if (!connector.connect) {
      throw new IllegalStateException("Unable to connect to " + address)
    }

    new Client(connector, listeners)
  }

  def main(args: Array[String]) {
    val client = Client("arrakis.bouvet.no")
    client.login(User("Ngarthl", "Erlend Hamnaberg"))
  }

  object HEIJOINER extends MessageListener {
    def onMessage(message: Message)(implicit writer: MessageWriter) = {
      message match {
        case Message(s, RPL_ENDOFMOTD, _) => writer.write(new Message(None, JOIN, List("#hei")))
        case _ =>
      }
    }
  }
}
