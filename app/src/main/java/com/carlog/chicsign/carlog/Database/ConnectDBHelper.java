package com.carlog.chicsign.carlog.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Chicsign on 2018-05-23.
 */

public class ConnectDBHelper extends SQLiteOpenHelper {
    private static ConnectDBHelper openDB;
    public static final String db_name = "CarLog.db";
    private static final int version = 1;

    private static final String ALTER_SCRAP_CLASSIFIED = " ALTER TABLE Scrap ADD isClassified INTEGER DEFAULT 1";

    private ConnectDBHelper(Context context) {
        super(context, db_name, null, version);
    }

    public static synchronized ConnectDBHelper getOpenDB(Context context) {

        if (openDB == null)
            openDB = new ConnectDBHelper(context);
        return openDB;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        database.execSQL(ScrapQuery.SQL_CREATE_TBL); // Create table ScrapQuery
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < newVersion) {
            Log.i("upgrade", "SCRAPdb 변경");
            db.execSQL(ALTER_SCRAP_CLASSIFIED);
        }
        onCreate(db);
    }

    public SQLiteDatabase getWritableDatabase(SQLiteDatabase database) {

        if (database == null || !database.isOpen())
            database = openDB.getWritableDatabase();
        else if (database.isReadOnly()) {
            database.close();
            database = openDB.getWritableDatabase();
        }

        return database;
    }

    public void createTable() {
        openDB.getWritableDatabase();
    }

    public SQLiteDatabase getReadableDatabase(SQLiteDatabase database) {

        if (database == null || !database.isOpen())
            database = openDB.getReadableDatabase();
        else if (!database.isReadOnly()) {
            database.close();
            database = openDB.getReadableDatabase();
        }

        return database;
    }
}
