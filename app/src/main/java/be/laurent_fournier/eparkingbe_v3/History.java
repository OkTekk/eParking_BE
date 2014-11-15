package be.laurent_fournier.eparkingbe_v3;

/**
 * Created by Lowlow on 12/11/2014.
 * Version ${VERSION}.
 */

public class History {
    private int historyId = 0;
    private String historyTable = null;

    public History(int historyId, String historyTable) {
        this.historyId = historyId;
        this.historyTable = historyTable;
    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public String getHistoryTable() {
        return historyTable;
    }

    public void setHistoryTable(String historyTable) {
        this.historyTable = historyTable;
    }

    @Override
    public String toString() {
        return "History ID = " + historyId + ", History Content = " + historyTable;
    }
}
