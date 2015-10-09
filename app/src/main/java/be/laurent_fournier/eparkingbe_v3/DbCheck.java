package be.laurent_fournier.eparkingbe_v3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Lowlow on 11/11/2014.
 * Version ${VERSION}.
 *
 * Database creation sql statements
 * I separated every tables to keep it open to changes
 */

public class DbCheck extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "eParking.db";
    private static final int DATABASE_VERSION = 2;

    public DbCheck(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /* *********************************************************
     * Define the commands to Create tables and their columns  *
     * As well as setting a few data                           *
     * *********************************************************/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user (_id integer primary key autoincrement, name text not null, password text);");
        db.execSQL("CREATE TABLE zone (_id integer primary key autoincrement, name text not null, zoneid text not null, provider integer not null, geoposition text);");
        db.execSQL("CREATE TABLE auto (_id integer primary key autoincrement, autoid text not null, geoposition text);");
        db.execSQL("CREATE TABLE buffer (_id integer primary key autoincrement, iduser integer, idzone integer, idauto integer, buffer text);");
        db.execSQL("CREATE TABLE history (_id integer primary key autoincrement, history text);");

        db.execSQL("INSERT INTO user (_id, name) VALUES (1, 'Lowlow')");
        db.execSQL("INSERT INTO user (_id, name) VALUES (2, 'Lupi')");
        db.execSQL("INSERT INTO zone (_id, name, zoneid, provider) VALUES (null, 'Verviers', 'VER1', 1)");
        db.execSQL("INSERT INTO zone (_id, name, zoneid, provider) VALUES (null, 'Verviers', 'VER2', 1)");
        db.execSQL("INSERT INTO zone (_id, name, zoneid, provider) VALUES (null, 'Verviers', 'VER3', 1)");
        db.execSQL("INSERT INTO zone (_id, name, zoneid, provider) VALUES (null, 'Liège', 'LIE1', 2)");
        db.execSQL("INSERT INTO zone (_id, name, zoneid, provider) VALUES (null, 'Liège', 'LIE2', 2)");
        db.execSQL("INSERT INTO auto (_id, autoid) VALUES (null, '1DLI125')");
        db.execSQL("INSERT INTO auto (_id, autoid) VALUES (null, '1AWR849')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DbCheck.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data.");

        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS zone");
        db.execSQL("DROP TABLE IF EXISTS auto");
        db.execSQL("DROP TABLE IF EXISTS buffer");
        db.execSQL("DROP TABLE IF EXISTS history");

        onCreate(db);
    }

    public static int getDatabaseVersion() {
        return DATABASE_VERSION;
    }
}
