package be.laurent_fournier.eparkingbe_v3;

/**
 * Created by Lowlow on 12/11/2014.
 * Version ${VERSION}.
 */

public class Auto {
    private int autoId = 0;
    private String autoLicense = null, autoGeoposition = null;

    public Auto(int autoId, String autoLicense, String autoGeoposition) {
        this.autoId = autoId;
        this.autoLicense = autoLicense;
        this.autoGeoposition = autoGeoposition;
    }

    public Auto(String autoLicense, String autoGeoposition) {
        this.autoLicense = autoLicense;
        this.autoGeoposition = autoGeoposition;
    }

    public int getAutoId() {
        return autoId;
    }

    public void setAutoId(int autoId) {
        this.autoId = autoId;
    }

    public String getAutoLicense() {
        return autoLicense;
    }

    public void setAutoLicense(String autoLicense) {
        this.autoLicense = autoLicense;
    }

    public String getAutoGeoposition() {
        return autoGeoposition;
    }

    public void setAutoGeoposition(String autoGeoposition) {
        this.autoGeoposition = autoGeoposition;
    }

    @Override
    public String toString() {
        return autoLicense;
    }
}
