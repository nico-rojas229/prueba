/*
package webRTC.VideoCall.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import webRTC.VideoCall.interceptor.UserInterceptor;

@Controller
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {

    */
/**
     * Metodo para configurar el MessageBroker del WebSocketStomp
     * @param config
     *//*

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    */
/**
     * Metodo para agregar los endPoints con SockJS
     * @param registry
     *//*

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/VideoCall/videoCall/websocket",
                "/WebRTC/webSocket", "/hello")
                .setAllowedOriginPatterns("*").withSockJS();
    }

    */
/**
     * Agregamos el canal interceptor
     * @param registration
     *//*

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new UserInterceptor());
    }

}
*/
