package webRTC.VideoCall.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("t_file")
@Data
public class FileEntity {

    private String fileName;
    private String file;
    private RoomEntity idRoom;
    private long size;

    public FileEntity(String fileName, String file, RoomEntity idRoom, long size) {
        this.fileName = fileName;
        this.file = file;
        this.idRoom = idRoom;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public RoomEntity getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(RoomEntity idRoom) {
        this.idRoom = idRoom;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "FileEntity{" +
                "fileName='" + fileName + '\'' +
                ", file='" + file + '\'' +
                ", idRoom=" + idRoom +
                ", size=" + size +
                '}';
    }
}
