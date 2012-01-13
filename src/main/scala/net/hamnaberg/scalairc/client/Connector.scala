package net.hamnaberg.scalairc.client

import java.io.Closeable

/**
 * Created by IntelliJ IDEA.
 * User: maedhros
 * Date: Dec 6, 2010
 * Time: 9:22:17 PM
 * To change this template use File | Settings | File Templates.
 */

trait Connector extends Closeable {
  def connect: Boolean

  def reader: MessageReader

  def writer: MessageWriter
}
