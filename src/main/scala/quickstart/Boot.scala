package quickstart

import xitrum.SockJsHandler
import xitrum.handler.Server
import xitrum.routing.Routes

class Echo extends SockJsHandler {
  def onOpen()                   { logger.debug("onOpen") }
  def onClose()                  { logger.debug("onClose") }
  def onMessage(message: String) { send(message) }
}

class Close extends SockJsHandler {
  def onOpen()                   { logger.debug("onOpen"); close() }
  def onClose()                  { logger.debug("onClose") }
  def onMessage(message: String) {}
}

object Boot {
  def main(args: Array[String]) {
    Routes.sockJs(classOf[Echo],  "echo",                    websocket=true,  cookieNeeded=false)
    Routes.sockJs(classOf[Echo],  "disabled_websocket_echo", websocket=false, cookieNeeded=false)
    Routes.sockJs(classOf[Echo],  "cookie_needed_echo",      websocket=true,  cookieNeeded=true)
    Routes.sockJs(classOf[Close], "close",                   websocket=true,  cookieNeeded=false)
    Server.start()
  }
}
