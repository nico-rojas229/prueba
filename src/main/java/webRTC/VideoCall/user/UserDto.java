package webRTC.VideoCall.user;

import org.springframework.web.socket.WebSocketSession;

public class UserDto {

    private String fullName;
    private String username;
    private Integer idAffiliate;
    private String role;
    private WebSocketSession session;

    public UserDto() {}

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIdAffiliate() {
        return idAffiliate;
    }

    public void setIdAffiliate(Integer idAffiliate) {
        this.idAffiliate = idAffiliate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public WebSocketSession getSession() {
        return session;
    }

    public void setSession(WebSocketSession session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", idAffiliate=" + idAffiliate +
                ", role='" + role + '\'' +
                ", session=" + session +
                '}';
    }

}
