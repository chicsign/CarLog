package com.carlog.chicsign.carlog.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.carlog.chicsign.carlog.model.Model;

import java.util.ArrayList;

/**
 * Created by chicsign on 2018-05-29.
 */

public class ScrapDB {
    private static ScrapDB scrapDB = null;
    private ConnectDBHelper dbHelper;
    private SQLiteDatabase database;

    private ScrapDB(Context context){
        dbHelper = ConnectDBHelper.getOpenDB(context);
    }

    public static synchronized ScrapDB getScrapDB(Context context){

        if(scrapDB == null)
            scrapDB = new ScrapDB(context);

        return scrapDB;
    }

    public ArrayList<Model> scrap_select(Model scrapModel){

        database = dbHelper.getReadableDatabase(database);
        ArrayList<Model> list = ScrapQuery.scrap_select(database, scrapModel);
        database.close();
        return list;
    }

    public Model scrap_insert(Model model){

        database = dbHelper.getWritableDatabase(database);
        model = ScrapQuery.scrap_insert(database, model);
        database.close();

        return model;
    }

    public void scrap_update(Model model){

        database = dbHelper.getWritableDatabase(database);
        ScrapQuery.scrap_update(database, model);
        database.close();
    }

    public void scrap_delete(Model model){

        database = dbHelper.getWritableDatabase(database);
        ScrapQuery.scrap_delete(database, model);
        database.close();

    }

    public void clear_ScrapDB(){
        database = dbHelper.getWritableDatabase(database);
        ScrapQuery.clear_ScrapDB(database);
        database.close();
    }


}
