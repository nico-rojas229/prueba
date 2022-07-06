package webRTC.VideoCall.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("t_user")
@Data
public class UserEntity {

    @Id
    private String id;

    private String fullName;
    private String userName;
    private String password;
    private Integer idUser;

    public UserEntity(String id, String fullName, String userName, String password, int idUser) {
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.idUser = idUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", idUser=" + idUser +
                '}';
    }
}
