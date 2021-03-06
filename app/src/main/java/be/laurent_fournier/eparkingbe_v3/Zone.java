package be.laurent_fournier.eparkingbe_v3;

/**
 * Created by Lowlow on 12/11/2014.
 * Version ${VERSION}.
 */

public class Zone {
    private int zoneId = 0, zoneProvider = 0;
    private String zoneName = null, zoneCode = null, zoneGeoposition = null;

    public Zone(int zoneId, String zoneName, String zoneCode, int zoneProvider, String zoneGeoposition) {
        this.zoneId = zoneId;
        this.zoneName = zoneName;
        this.zoneCode = zoneCode;
        this.zoneProvider = zoneProvider;
        this.zoneGeoposition = zoneGeoposition;
    }

    public Zone(String zoneName, String zoneCode, int zoneProvider, String zoneGeoposition) {
        this.zoneName = zoneName;
        this.zoneCode = zoneCode;
        this.zoneProvider = zoneProvider;
        this.zoneGeoposition = zoneGeoposition;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public int getZoneProvider() {
        return zoneProvider;
    }

    public void setZoneProvider(int zoneProvider) {
        this.zoneProvider = zoneProvider;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getZoneGeoposition() {
        return zoneGeoposition;
    }

    public void setZoneGeoposition(String zoneGeoposition) {
        this.zoneGeoposition = zoneGeoposition;
    }

    @Override
    public String toString() {
        return zoneCode;
    }
}
