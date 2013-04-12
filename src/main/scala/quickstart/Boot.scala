package quickstart

import xitrum.{Action, SockJsActor, SockJsText}
import xitrum.annotation.{SOCKJS, SockJsCookieNeeded, SockJsNoWebSocket}
import xitrum.handler.Server

@SOCKJS("echo")
class Echo extends SockJsActor {
  def execute(action: Action) {
    logger.debug("onOpen")
    context.become {
      case SockJsText(text) => respondSockJsText(text)
    }
  }

  override def postStop() { logger.debug("onClose") }
}

@SOCKJS("disabled_websocket_echo")
@SockJsNoWebSocket
class EchoNoWebSocket extends Echo

@SOCKJS("cookie_needed_echo")
@SockJsCookieNeeded
class EchoCookieNeeded extends Echo

@SOCKJS("close")
class Close extends SockJsActor {
  def execute(action: Action) {
    logger.debug("onOpen");
    respondSockJsClose()
  }

  override def postStop() { logger.debug("onClose") }
}

object Boot {
  def main(args: Array[String]) { Server.start() }
}
