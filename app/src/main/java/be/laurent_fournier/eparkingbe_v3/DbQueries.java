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
/*    public User getUser() {
        User user = null;
        Cursor cursor = db.query("user", new String[] {"name", "password"}, null, null, null, null, "name");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            user = new User(cursor.getString(0), cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();

        return user;
    }*/

    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        Cursor cursor = db.query("user", new String[] {"name", "password"}, null, null, null, null, "name");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User user = new User(cursor.getString(0), cursor.getString(1));
            users.add(user);
            cursor.moveToNext();
        }
        cursor.close();
        return users;
    }

    public List<Zone> getZones() {
        List<Zone> zones = new ArrayList<Zone>();
        Cursor cursor = db.query("zone", new String[] {"_id", "name", "zoneid", "provider", "geoposition"}, null, null, null, null, "_id");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Zone zone = new Zone(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4));
            zones.add(zone);
            cursor.moveToNext();
        }
        cursor.close();
        return zones;
    }

    public List<Auto> getAutos() {
        List<Auto> autos = new ArrayList<Auto>();
        Cursor cursor = db.query("auto", new String[] {"_id", "autoid", "geoposition"}, null, null, null, null, "_id");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Auto auto = new Auto(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            autos.add(auto);
            cursor.moveToNext();
        }
        cursor.close();
        return autos;
    }

    public Buffer getBuffer() {
        Buffer buffer = null;
        Cursor cursor = db.query("buffer", new String[] {"_id", "iduser", "idzone", "idauto", "buffer"}, null, null, null, null, "_id");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            buffer = new Buffer(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4));
            cursor.moveToNext();
        }
        cursor.close();

        return buffer;
    }

    public History getHistory() {
        History history = null;
        Cursor cursor = db.query("history", new String[] {"_id", "history"}, null, null, null, null, "_id");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            history = new History(cursor.getInt(0), cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();

        return history;
    }

    /* **************************************************************
     * Define Setters                                               *
     * It probably won't need any others                            *
     * Than buffers, users, and auto                                 *
     * **************************************************************/
/*    public void setAutoBuffer(String plaque, int zone) {
        ContentValues autoValues = new ContentValues();
        autoValues.put("plaque", plaque);
        autoValues.put("zone", zone);
        db.update("buffer", autoValues, "_id = 1", null);
    }*/
}
