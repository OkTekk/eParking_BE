package be.laurent_fournier.eparkingbe_v3;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lowlow on 11/11/2014.
 * Version ${VERSION}.
 */

public class DbQueries {
    private SQLiteDatabase db;
    private DbCheck dbCheck;

    private String[] zoneArray = { "_id", "name", "zoneid", "provider", "geoposition"};

    public DbQueries(Context context) {
        dbCheck = new DbCheck(context);
    }

    public void open() throws SQLException {
        db = dbCheck.getWritableDatabase();
    }

    public void close() {
        dbCheck.close();
    }

    /* **************************************************************
     * Define Getters for all Tables                                *
     * 'Zone' is bumped in a list to give access to every codes     *
     * While other tables require only one entry at the time        *
     * **************************************************************/
    public User getUser() {
        Cursor cursor = db.query("user", new String[] {"name", "password"}, null, null, null, null, "name");
        cursor.moveToFirst();
        User user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        cursor.close();

        return user;
    }

    public List<Zone> getZones() {
        List<Zone> zones = new ArrayList<Zone>();
        Cursor cursor = db.query("zone", new String[] {"name", "zoneid", "provider", "geoposition"}, null, null, null, null, "name");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Zone zone = new Zone(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4));
            zones.add(zone);
            cursor.moveToNext();
        }
        cursor.close();
        return zones;
    }

    public Auto getAuto() {
        Cursor cursor = db.query("auto", new String[] {"name", "password"}, null, null, null, null, "_id");
        cursor.moveToFirst();
        Auto auto = new Auto(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        cursor.close();

        return auto;
    }

    public Buffer getBuffer() {
        Cursor cursor = db.query("buffer", new String[] {"_id", "iduser", "idzone", "idauto", "buffer"}, null, null, null, null, "_id");
        cursor.moveToFirst();
        Buffer buffer = new Buffer(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4));
        cursor.close();

        return buffer;
    }

    public History getHistory() {
        Cursor cursor = db.query("history", new String[] {"_id", "history"}, null, null, null, null, "_id");
        cursor.moveToFirst();
        History history = new History(cursor.getInt(0), cursor.getString(1));
        cursor.close();

        return history;
    }

    /* **************************************************************
     * Define Setters for all Tables                                *
     * **************************************************************/
}
