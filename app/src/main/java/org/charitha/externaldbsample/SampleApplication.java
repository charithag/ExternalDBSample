package org.charitha.externaldbsample;

import android.app.Application;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by charitha on 10/29/2015.
 */
public class SampleApplication extends Application {

    private static final String TAG = "SampleApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences pref = getSharedPreferences("org.charitha.externaldbsample", 0);
        try {
            if (!pref.getBoolean("db_conversion", false)) {
                DBHelper dbHelper = new DBHelper(this);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String destPath = db.getPath();
                db.close();
                dbHelper.close();

                Log.v(TAG, "dest: " + destPath);
                InputStream in = getAssets().open("default.db");
                OutputStream out = new FileOutputStream(destPath);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                in.close();
                out.close();
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("db_conversion", true);
                editor.apply();
            }
        } catch (FileNotFoundException e) {
            Log.e("TAG", "file not found", e);
        } catch (IOException e) {
            Log.e("TAG", "IO Exception", e);
        }
    }
}
