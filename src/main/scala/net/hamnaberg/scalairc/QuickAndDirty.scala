package net.hamnaberg.scalairc

import java.net._
import java.nio.ByteBuffer
import java.io.{OutputStreamWriter, BufferedWriter, BufferedReader, InputStreamReader}
import io.Source

/**
 * Created by IntelliJ IDEA.
 * User: maedhros
 * Date: Dec 1, 2010
 * Time: 5:30:50 PM
 * To change this template use File | Settings | File Templates.
 */

object QuickAndDirty {
  def main(args: Array[String]) {
    //val address = new InetSocketAddress("irc.underworld.no", 6667)
    val address = new InetSocketAddress("arrakis.bouvet.no", 6667)
    val socket = new Socket()
    try {
      socket.connect(address, 20000)
      //val reader = new BufferedReader(new InputStreamReader(socket.getInputStream))
      val writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream))
      if (socket.isConnected) {
        println("Connected, now logging in.")
        //we need to login
        writer.write(new Message(None, NICK, List("scalairc1")).format)
        //writer.write("NICK " + "scalairc1\r\n")
        writer.write(new Message(None, USER, List("scalairc1", "8 * : Foobar")).format)
        //writer.write("USER " + "scalairc1 8 * : Foobar\r\n")
        writer.flush
      }
      else {
        println("Socket failed to connect, unable to continue!")
        return
      }
      println("Reading from source")
      val source = Source.fromInputStream(socket.getInputStream)
      val lines = source.getLines

      def isLoggedIn(lines: Iterator[String]) : Boolean = {
        for (line <- lines) {
          val message = Message(line)
          print(message.format)
          message match {
            case Message(_, RPL_MYINFO, _) => return true
            case Message(_, ERR_NICKNAMEINUSE, _) => return false
            case _ =>
          }
        }
        false
      }
      val loggedIn = isLoggedIn(lines)
      if (loggedIn) {
        for (line <- lines) {
          val message = Message(line)
          message match {
            case Message(None, PING, List(y)) => {
              println("Answering to PING with a PONG")
              writer.write(new Message(None, PONG, List(y)).format)
              writer.flush
              println("Answered to PING with a PONG")
            }
            case x => print(x.format)
          }
        }        
      }
    }
    finally {
      socket.close
    }
  }
}
