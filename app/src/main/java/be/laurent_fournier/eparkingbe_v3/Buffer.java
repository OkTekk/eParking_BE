package be.laurent_fournier.eparkingbe_v3;

/**
 * Created by Lowlow on 12/11/2014.
 * Version ${VERSION}.
 */

public class Buffer {
    private int bufferId = 0;
    private String bufferTable = null;

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

    @Override
    public String toString() {
        return "Buffer ID = " + bufferId + ", Buffer Content = " + bufferTable;
    }
}
