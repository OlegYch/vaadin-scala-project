package com.olegych

import org.eclipse.jetty.server.{Connector, Server}
import org.slf4j.LoggerFactory
import org.eclipse.jetty.webapp.WebAppContext
import org.slf4j.bridge.SLF4JBridgeHandler

object WebLauncher extends App {
  SLF4JBridgeHandler.install()

  val production = "false"
  val mainClass = getClass

  private final val l = LoggerFactory.getLogger(mainClass)

  val server: Server = new Server
  server.setConnectors(Array[Connector](new org.eclipse.jetty.server.nio.SelectChannelConnector {
    setPort(9001)
    setMaxIdleTime(1000 * 60 * 60)
    setSoLingerTime(-1)
    setForwarded(true)
  }))
  server.setHandler(new WebAppContext {
    setThrowUnavailableOnStartupException(true)
    setServer(server)
    setContextPath("/")
    setInitParameter("productionMode", production)
    setWar(mainClass.getResource("").toExternalForm.replaceAll("^jar:", "")
        .replaceAll("!?/" + mainClass.getPackage.getName.replaceAll("\\.", "/"), ""))
  })
  try {
    server.start
    System.in.read
    server.stop
    server.join
  } catch {
    case e => {
      l.error("Exception during server start", e)
      System.exit(-1)
    }
  }
}
