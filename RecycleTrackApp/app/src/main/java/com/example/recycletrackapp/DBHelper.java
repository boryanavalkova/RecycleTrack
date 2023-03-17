package com.example.recycletrackapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String LOGWASTE = "LOGWASTE";
    public static final String COLUMN_RECYCLEDWASTE = "RECYCLEDWASTE";
    public static final String COLUMN_GENERALWASTE = "GENERALWASTE";
    public static final String COLUMN_ID = "ID";

    public DBHelper(@Nullable Context context) {
        super(context, "recycleDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTablesStr = "CREATE TABLE " + LOGWASTE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_RECYCLEDWASTE + ", " + COLUMN_GENERALWASTE + ")";

        db.execSQL(createTablesStr);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //add items to DB
    public boolean added(LogModel logModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_RECYCLEDWASTE, logModel.getRecycled());
        cv.put(COLUMN_GENERALWASTE, logModel.getGeneral());
        long insert = db.insert(LOGWASTE, null,cv);

        if (insert == -1){
            return false;
        } else {
            return true;
        }
    }

    //get items from DB
    public List<LogModel> getDBdata(){
        List<LogModel> listReturn = new ArrayList<>();
        String query = "SELECT * FROM " + LOGWASTE;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                int ID = cursor.getInt(0);
                int recycledNum = cursor.getInt(1);
                int generalNum = cursor.getInt(2);
                LogModel logmodel = new LogModel(ID, recycledNum, generalNum);

            } while (cursor.moveToNext());

        } else {
            //don't add anything
        }
        cursor.close();
        db.close();
        return listReturn;
    }
}
