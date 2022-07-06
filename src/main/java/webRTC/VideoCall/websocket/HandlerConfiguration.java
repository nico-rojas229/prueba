package webRTC.VideoCall.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Controller
@Configuration
@EnableWebSocket
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class HandlerConfiguration implements WebSocketConfigurer {

    /**
     * Metodo para agregar los handlers a los url que queramos
     * @param registry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        registry.addHandler(signalHandler(), "/VideoCall/videoCall/webSocket",
                "/videoCall/webSocket/{sessionId}").setAllowedOriginPatterns("*");

    }

    /**
     * Vinculamos nuestra clase WebSocketHandler que tiene los metodos del webSocket
     * @return
     */
    @Bean
    public WebSocketHandler signalHandler() {
        return new webRTC.VideoCall.websocket.WebSocketHandler();
    }

}
