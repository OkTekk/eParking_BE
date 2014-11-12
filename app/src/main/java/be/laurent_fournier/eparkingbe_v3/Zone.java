package be.laurent_fournier.eparkingbe_v3;

/**
 * Created by Lowlow on 12/11/2014.
 * Version ${VERSION}.
 */

public class Zone {
    private int zoneId = 0, zoneProvider = 0;
    private String zoneName = null, zoneCode = null, zoneGeoposition = null;

    public int getZoneId() {
        return zoneId;
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
        return "Zone ID = " + zoneId + ", Zone Name = " + zoneName + ", Zone Code='" + zoneCode
                + ", Zone Provider = " + zoneProvider + ", Zone Geoposition='" + zoneGeoposition;
    }
}
