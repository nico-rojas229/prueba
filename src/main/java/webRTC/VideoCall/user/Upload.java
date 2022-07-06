package webRTC.VideoCall.user;

public class Upload {

    private final String fileName;
    private final String status;
    private final String message;
    private final long size;
    private final Integer roomId;

    public Upload(String fileName, String status, String message, Integer roomId, long size) {
        this.fileName = fileName;
        this.roomId = roomId;
        this.message = message;
        this.status = status;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public long getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Upload{" +
                "fileName='" + fileName + '\'' +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", size='" + size + '\'' +
                ", roomId=" + roomId +
                '}';
    }

}
