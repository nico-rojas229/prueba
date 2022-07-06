package webRTC.VideoCall.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    /**
     * Metodo que escucha la conexion webSocket mientras esta conectado
     * @param event
     */
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info(event.toString());
    }

    /**
     * Metodo que escucha la conexion webSocket cuando se desconecta
     * @param event
     */
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        logger.info(event.toString());
    }

}
