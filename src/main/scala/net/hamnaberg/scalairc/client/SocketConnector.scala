package net.hamnaberg.scalairc.client

import java.net.{SocketAddress, Socket}
import java.io.{BufferedWriter, OutputStreamWriter}
import io.{Codec, Source}
import java.nio.charset.CodingErrorAction
/**
 * Created by IntelliJ IDEA.
 * User: maedhros
 * Date: Dec 6, 2010
 * Time: 9:24:53 PM
 * To change this template use File | Settings | File Templates.
 */

class SocketConnector(private val address: SocketAddress, private val timeout: Int, codec: Codec = Codec.default.onMalformedInput(CodingErrorAction.REPLACE)) extends Connector {
  private val socket = new Socket()
  
  def connect = {
    socket.connect(address, timeout)
    socket.isConnected
  }

  def close = socket.close

  lazy val reader = new MessageReader(Source.fromInputStream(socket.getInputStream)(codec).getLines)

  lazy val writer = new BufferedMessageWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream)))
}
