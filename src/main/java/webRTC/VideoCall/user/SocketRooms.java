package webRTC.VideoCall.user;

import org.springframework.web.socket.WebSocketSession;
import java.util.List;

public class SocketRooms {

    private String sessionId;

    private List<WebSocketSession> sessions;

    private List<UserDto> users;

    public String getSessionId() {
        return sessionId;
    }

    public List<WebSocketSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<WebSocketSession> sessions) {
        this.sessions = sessions;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "SocketRooms{" +
                "sessionId='" + sessionId + '\'' +
                ", sessions=" + sessions +
                ", users=" + users +
                '}';
    }
}
