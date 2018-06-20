package com.carlog.chicsign.carlog.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.carlog.chicsign.carlog.model.CarLogModel;

import java.util.ArrayList;

/**
 * Created by chicsign on 2018-05-29.
 */

public class ScrapQuery {
    private ScrapQuery() {
    }

    // table ScrapQuery Info
    public static final String tableName = "Scrap";
    public static final String scrapSeq = "scrapSeq";
    public static final String scrapPrice = "scrapPrice";
    public static final String scrapLiter = "scrapLiter";


    public static final String SQL_CREATE_TBL = "CREATE TABLE IF NOT EXISTS " + tableName
            + " ( " + scrapSeq + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + scrapPrice + " TEXT NOT NULL, " + scrapLiter + " TEXT NOT NULL) ";

    public static final String SQL_DROP_TBL = "DROP TABLE IF EXISTS " + tableName;

    // 스크랩 조회
    public static ArrayList<CarLogModel> scrap_select(SQLiteDatabase database, CarLogModel carLogModel) {

        final String SQL_SELECT = "select * from " + tableName;

        CarLogModel scrapCarLogModel = null;
        ArrayList<CarLogModel> list = new ArrayList<CarLogModel>();
        Cursor cursor = database.rawQuery(SQL_SELECT, null);
        while (cursor.moveToNext()) {
            scrapCarLogModel = new CarLogModel();
            scrapCarLogModel.setScrapSeq(cursor.getInt(cursor.getColumnIndexOrThrow(scrapSeq)));
            scrapCarLogModel.setPrice(cursor.getString(cursor.getColumnIndexOrThrow(scrapPrice)));
            scrapCarLogModel.setLiter(cursor.getString(cursor.getColumnIndexOrThrow(scrapLiter)));

            list.add(scrapCarLogModel);
        }
        return list;
    }


    // 스크랩 저장
    public static CarLogModel scrap_insert(SQLiteDatabase database, final CarLogModel scrapCarLogModel) {
        ContentValues values = new ContentValues();
        values.put(ScrapQuery.scrapPrice, scrapCarLogModel.getPrice());
        values.put(ScrapQuery.scrapLiter, scrapCarLogModel.getLiter());
        scrapCarLogModel.setScrapSeq((int) database.insert(ScrapQuery.tableName, null, values));
        return scrapCarLogModel;
    }

    public static void scrap_update(SQLiteDatabase database, CarLogModel scrapCarLogModel) {
        ContentValues values = new ContentValues();
        values.put(ScrapQuery.scrapPrice, scrapCarLogModel.getPrice());
        values.put(ScrapQuery.scrapLiter, scrapCarLogModel.getLiter());

        database.update(tableName, values, scrapSeq + " = ?", new String[]{String.valueOf(scrapCarLogModel.getScrapSeq())});
    }


    public static void scrap_delete(SQLiteDatabase database, CarLogModel scrapCarLogModel) {
        database.delete(tableName, scrapSeq + " = ?", new String[]{String.valueOf(scrapCarLogModel.getScrapSeq())});

    }


    public static void clear_ScrapDB(SQLiteDatabase database) {
        database.execSQL("DELETE FROM " + tableName);
    }

}
