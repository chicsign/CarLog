package com.carlog.chicsign.carlog.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.carlog.chicsign.carlog.model.Model;

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
    public static ArrayList<Model> scrap_select(SQLiteDatabase database, Model model) {

        final String SQL_SELECT = "select * from " + tableName;

        Model scrapModel = null;
        ArrayList<Model> list = new ArrayList<Model>();
        Cursor cursor = database.rawQuery(SQL_SELECT, null);
        while (cursor.moveToNext()) {
            scrapModel = new Model();
            scrapModel.setScrapSeq(cursor.getInt(cursor.getColumnIndexOrThrow(scrapSeq)));
            scrapModel.setPrice(cursor.getString(cursor.getColumnIndexOrThrow(scrapPrice)));
            scrapModel.setLiter(cursor.getString(cursor.getColumnIndexOrThrow(scrapLiter)));

            list.add(scrapModel);
        }
        return list;
    }


    // 스크랩 저장
    public static Model scrap_insert(SQLiteDatabase database, final Model scrapModel) {
        ContentValues values = new ContentValues();
        values.put(ScrapQuery.scrapPrice, scrapModel.getPrice());
        values.put(ScrapQuery.scrapLiter, scrapModel.getLiter());
        scrapModel.setScrapSeq((int) database.insert(ScrapQuery.tableName, null, values));
        return scrapModel;
    }

    public static void scrap_update(SQLiteDatabase database, Model scrapModel) {
        ContentValues values = new ContentValues();
        values.put(ScrapQuery.scrapPrice, scrapModel.getPrice());
        values.put(ScrapQuery.scrapLiter, scrapModel.getLiter());

        database.update(tableName, values, scrapSeq + " = ?", new String[]{String.valueOf(scrapModel.getScrapSeq())});
    }


    public static void scrap_delete(SQLiteDatabase database, Model scrapModel) {
        database.delete(tableName, scrapSeq + " = ?", new String[]{String.valueOf(scrapModel.getScrapSeq())});

    }


    public static void clear_ScrapDB(SQLiteDatabase database) {
        database.execSQL("DELETE FROM " + tableName);
    }

}
