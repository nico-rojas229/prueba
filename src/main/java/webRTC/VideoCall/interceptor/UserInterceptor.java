package webRTC.VideoCall.interceptor;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import webRTC.VideoCall.user.User;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class UserInterceptor implements ChannelInterceptor {

    /**
     * Metodo interceptor del usuario antes de hacer un send
     * @param message
     * @param channel
     * @return
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.CONNECT.equals(Objects.requireNonNull(accessor).getCommand())) {
            Object raw = message.getHeaders().get(SimpMessageHeaderAccessor.NATIVE_HEADERS);

            if (raw instanceof Map) {
                Object name = ((Map) raw).get("username");

                if (name instanceof ArrayList) {
                    accessor.setUser(new User(((ArrayList<String>) name).get(0).toString()));
                }
            }
        }
        return message;
    }

}
