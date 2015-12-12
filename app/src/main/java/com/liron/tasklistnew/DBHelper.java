package com.liron.tasklistnew;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
    Creates the database if doesn't exists
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;

    private static final String DB_NAME = "dbtasks.db";

    public DBHelper (Context context){
        super (context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql=
                "CREATE TABLE " + TasksCons.TABLE_NAME
                        +"("
                        + TasksCons._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                        + TasksCons.DESC + " TEXT ,"
                        + TasksCons.IS_DONE + " INTEGER"
                        /*+ TasksCons.TIME + " INTEGER, "
                        + TasksCons.LAT + " REAL ,"
                        + TasksCons.LNG + " REAL "*/
                        +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + TasksCons.TABLE_NAME;
        db.execSQL(sql);

        onCreate(db);
    }


}


