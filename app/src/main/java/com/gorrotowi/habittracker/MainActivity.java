package com.gorrotowi.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gorrotowi.habittracker.database.HabitContract;
import com.gorrotowi.habittracker.database.HabitatOpenHelper;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    SQLiteDatabase db;
    HabitatOpenHelper habitatOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        habitatOpenHelper = new HabitatOpenHelper(this);

        insertData();
        Cursor cursordata = readData();
        try {
            do {
                Log.e(TAG, "readData: " + cursordata.getCount());
                Log.e(TAG, "readData: " + cursordata.getPosition());
                Log.e(TAG, "readData: " + cursordata.getColumnName(cursordata.getColumnIndex(HabitContract.HabitEntry.COLUMN_NAME_NAME)));
                Log.e(TAG, "readData: " + cursordata.getColumnName(cursordata.getColumnIndex(HabitContract.HabitEntry.COLUMN_NAME_AGE)));
                Log.e(TAG, "readData: " + cursordata.getString(cursordata.getColumnIndex(HabitContract.HabitEntry.COLUMN_NAME_NAME)));
                Log.e(TAG, "readData: " + cursordata.getString(cursordata.getColumnIndex(HabitContract.HabitEntry.COLUMN_NAME_AGE)));
                Log.e(TAG, "readData: " + cursordata.getString(cursordata.getColumnIndex(HabitContract.HabitEntry.COLUMN_NAME_TAKE_WATER)));
                Log.e(TAG, "readData: " + cursordata.getString(cursordata.getColumnIndex(HabitContract.HabitEntry.COLUMN_NAME_WALK)));
            }
            while (cursordata.moveToNext());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursordata.close();
        }

    }

    private void insertData() {
        db = habitatOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HabitContract.HabitEntry.COLUMN_NAME_NAME, "Gorrotowi");
        contentValues.put(HabitContract.HabitEntry.COLUMN_NAME_AGE, 24);
        contentValues.put(HabitContract.HabitEntry.COLUMN_NAME_TAKE_WATER, HabitContract.TAKE_WATER);
        contentValues.put(HabitContract.HabitEntry.COLUMN_NAME_WALK, HabitContract.WALK);
        db.insert(HabitContract.HabitEntry.TABLE_NAME, null, contentValues);
        db.close();
    }

    private Cursor readData() {
        db = habitatOpenHelper.getWritableDatabase();
        String[] project = {
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_NAME_NAME,
                HabitContract.HabitEntry.COLUMN_NAME_AGE,
                HabitContract.HabitEntry.COLUMN_NAME_TAKE_WATER,
                HabitContract.HabitEntry.COLUMN_NAME_WALK
        };
        Cursor cursor = db.query(HabitContract.HabitEntry.TABLE_NAME, project, null, null, null, null, null);//db.rawQuery("SELECT * FROM " + HabitContract.HabitEntry.TABLE_NAME, null);
        cursor.moveToFirst();
        return cursor;
    }
}
