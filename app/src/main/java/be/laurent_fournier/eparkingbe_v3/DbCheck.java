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
    public static final String TABLE_USER = "user";
    public static final String COLUMN_IDu = "_id";
    public static final String COLUMN_NAMEu = "name";
    public static final String COLUMN_PASSu = "password";

    public static final String TABLE_ZONE = "zone";
    public static final String COLUMN_IDz = "_id";
    public static final String COLUMN_NAMEz = "name";
    public static final String COLUMN_ZONEIDz = "zoneid";
    public static final String COLUMN_DISTRIz = "distributor";
    public static final String COLUMN_GEOPOSz = "geoposition";

    public static final String TABLE_AUTO = "auto";
    public static final String COLUMN_IDa = "_id";
    public static final String COLUMN_AUTOIDa = "autoid";
    public static final String COLUMN_GEOPOSa = "geoposition";

    public static final String TABLE_BUFFER = "buffer";
    public static final String COLUMN_IDb = "_id";
    public static final String COLUMN_BUFFERb = "buffer";

    public static final String TABLE_HISTORY = "history";
    public static final String COLUMN_IDh = "_id";
    public static final String COLUMN_HISTORYh = "history";

    private static final String DATABASE_NAME = "eParking.db";
    private static final int DATABASE_VERSION = 1;

    private static final String USER_CREATE = "create table " + TABLE_USER
            + "(" + COLUMN_IDu + " integer primary key autoincrement, " + COLUMN_NAMEu + " text not null, " + COLUMN_PASSu + " text);";

    private static final String ZONE_CREATE = "create table " + TABLE_ZONE
            + "(" + COLUMN_IDz + " integer primary key autoincrement, " + COLUMN_NAMEz + " text not null, "
            + COLUMN_ZONEIDz + " text, " + COLUMN_DISTRIz + " integer, " + COLUMN_GEOPOSz + "text);";

    private static final String AUTO_CREATE = "create table " + TABLE_AUTO
            + "(" + COLUMN_IDa + " integer primary key autoincrement, " + COLUMN_AUTOIDa + " text not null, " + COLUMN_GEOPOSa + " text);";

    private static final String BUFFER_CREATE = "create table " + TABLE_BUFFER
            + "(" + COLUMN_IDb + " integer primary key autoincrement, " + COLUMN_BUFFERb + " text);";

    private static final String HISTORY_CREATE = "create table " + TABLE_HISTORY
            + "(" + COLUMN_IDh + " integer primary key autoincrement, " + COLUMN_HISTORYh + " text);";

    public DbCheck(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(USER_CREATE);
        database.execSQL(ZONE_CREATE);
        database.execSQL(AUTO_CREATE);
        database.execSQL(BUFFER_CREATE);
        database.execSQL(HISTORY_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DbCheck.class.getName(),
                "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
}
