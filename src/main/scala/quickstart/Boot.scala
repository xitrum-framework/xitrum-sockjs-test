package quickstart

import xitrum.{Server, SockJsActor, SockJsText}
import xitrum.annotation.{SOCKJS, SockJsCookieNeeded, SockJsNoWebSocket}

@SOCKJS("echo")
class Echo extends SockJsActor {
  def execute() {
    logger.debug(getClass.getName + " onOpen")
    context.become {
      case SockJsText(text) =>
        logger.debug(getClass.getName + " text: " + text)
        respondSockJsText(text)
    }
  }

  override def postStop() {
    logger.debug(getClass.getName + " onClose")
  }
}

@SOCKJS("disabled_websocket_echo")
@SockJsNoWebSocket
class EchoNoWebSocket extends Echo

@SOCKJS("cookie_needed_echo")
@SockJsCookieNeeded
class EchoCookieNeeded extends Echo

@SOCKJS("close")
class Close extends SockJsActor {
  def execute() {
    logger.debug(getClass.getName + " onOpen");
    respondSockJsClose()
  }

  override def postStop() {
    logger.debug(getClass.getName + " onClose")
  }
}

object Boot {
  def main(args: Array[String]) { Server.start() }
}
