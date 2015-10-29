package org.charitha.externaldbsample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by charitha on 10/29/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String dbName = "data.db";

    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "DATABASES";

    public DBHelper(Context context) {
        super(context, dbName, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v(TAG, "On create Called:" + db.getPath());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}