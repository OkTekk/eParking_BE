package be.laurent_fournier.eparkingbe_v3;

/**
 * Created by Lowlow on 12/11/2014.
 * Version ${VERSION}.
 */

public class Buffer {
    private int bufferId = 0, userId = 0, zoneId = 0, autoId = 0;
    private String bufferTable = null;

    public Buffer(int bufferId, int userId, int zoneId, int autoId, String bufferTable) {
        this.bufferId = bufferId;
        this.userId = userId;
        this.zoneId = zoneId;
        this.autoId = autoId;
        this.bufferTable = bufferTable;
    }

   public int getBufferId() {
        return bufferId;
    }

    public void setBufferId(int bufferId) {
        this.bufferId = bufferId;
    }

    public String getBufferTable() {
        return bufferTable;
    }

    public void setBufferTable(String bufferTable) {
        this.bufferTable = bufferTable;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public int getAutoId() {
        return autoId;
    }

    public void setAutoId(int autoId) {
        this.autoId = autoId;
    }

    @Override
    public String toString() {
        return "Buffer{" + "bufferId=" + bufferId + ", userId=" + userId + ", zoneId=" +
                zoneId + ", autoId=" + autoId + ", bufferTable='" + bufferTable + '\'' + '}';
    }
}
