package com.example.sobhagya.VeryImportantMessages;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sobhagya on 30-06-2016.
 */
public class Datat extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "sms";
    public static final String TABLE_NAME = "tab";
    public static final String COL_1 = "ABC";

    public Datat(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ABC TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXITS " + TABLE_NAME, null);
    }

    public boolean ins(String abc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, abc);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor fetch() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;

    }
   public Integer dlt(){
        SQLiteDatabase db=this.getWritableDatabase();
        Integer res= db.delete(TABLE_NAME,null,null);
        return res;

    }
}