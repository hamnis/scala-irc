package net.hamnaberg.scalairc.client

import java.io.BufferedWriter
import net.hamnaberg.scalairc.Message

/**
 * Created by IntelliJ IDEA.
 * User: maedhros
 * Date: Dec 6, 2010
 * Time: 8:05:32 PM
 * To change this template use File | Settings | File Templates.
 */

trait MessageWriter {
  def write(message: Message)
}

class BufferedMessageWriter(writer: BufferedWriter) extends MessageWriter {
  def write(message: Message) {
    writer.write(message.format)
    writer.flush
  }
}
