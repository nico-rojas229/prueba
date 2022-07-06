package webRTC.VideoCall.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("t_session")
@Data
public class SessionEntity {

    private String codeSession;
    private String fullName;
    private String localAddress;
    private String remoteAddress;
    private UserEntity idUser;

    public SessionEntity(String codeSession, String fullName, String localAddress, String remoteAddress, UserEntity idUser) {
        this.codeSession = codeSession;
        this.fullName = fullName;
        this.localAddress = localAddress;
        this.remoteAddress = remoteAddress;
        this.idUser = idUser;
    }

    public String getCodeSession() {
        return codeSession;
    }

    public void setCodeSession(String codeSession) {
        this.codeSession = codeSession;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public UserEntity getIdUser() {
        return idUser;
    }

    public void setIdUser(UserEntity idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "SessionEntity{" +
                "codeSession='" + codeSession + '\'' +
                ", fullName='" + fullName + '\'' +
                ", localAddress='" + localAddress + '\'' +
                ", remoteAddress='" + remoteAddress + '\'' +
                ", idUser=" + idUser +
                '}';
    }
}
