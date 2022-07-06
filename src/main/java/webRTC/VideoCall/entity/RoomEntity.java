package webRTC.VideoCall.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("t_room")
@Data
public class RoomEntity {

    private String codeRoom;
    private long idRoom;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String duration;

    public RoomEntity(String codeRoom, long idRoom, String startDate, String endDate, String startTime, String endTime, String duration) {
        this.codeRoom = codeRoom;
        this.idRoom = idRoom;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }

    public String getCodeRoom() {
        return codeRoom;
    }

    public void setCodeRoom(String codeRoom) {
        this.codeRoom = codeRoom;
    }

    public long getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(long idRoom) {
        this.idRoom = idRoom;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "RoomEntity{" +
                "codeRoom='" + codeRoom + '\'' +
                ", idRoom=" + idRoom +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
