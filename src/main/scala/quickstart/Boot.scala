package quickstart

import xitrum.{Server, SockJsAction, SockJsText}
import xitrum.annotation.{SOCKJS, SockJsCookieNeeded, SockJsNoWebSocket}

trait Echo extends SockJsAction {
  def execute(): Unit = {
    log.debug(getClass.getName + " onOpen")
    context.become {
      case SockJsText(text) =>
        log.debug(getClass.getName + " text: " + text)
        respondSockJsText(text)
    }
  }

  override def postStop(): Unit = {
    log.debug(getClass.getName + " onClose")
  }
}

@SOCKJS("echo")
class NormalEcho extends Echo

@SOCKJS("disabled_websocket_echo")
@SockJsNoWebSocket
class EchoNoWebSocket extends Echo

@SOCKJS("cookie_needed_echo")
@SockJsCookieNeeded
class EchoCookieNeeded extends Echo

@SOCKJS("close")
class Close extends SockJsAction {
  def execute(): Unit = {
    log.debug(getClass.getName + " onOpen");
    respondSockJsClose()
  }

  override def postStop(): Unit = {
    log.debug(getClass.getName + " onClose")
  }
}

object Boot {
  def main(args: Array[String]): Unit = {
    Server.start()
    Server.stopAtShutdown()
  }
}
