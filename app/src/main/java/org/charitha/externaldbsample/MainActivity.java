package org.charitha.externaldbsample;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.textViewData);

        DBHelper dbHelper = new DBHelper(this);
        Log.v(TAG, "Database is there with version: " + dbHelper.getReadableDatabase().getVersion());
        String sql = "select * from sample";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            Log.v(TAG, "Query Result:" + cursor.getString(1));
            textView.append("\n" + cursor.getString(1));
        }
        cursor.close();
        db.close();
        dbHelper.close();
    }
}
