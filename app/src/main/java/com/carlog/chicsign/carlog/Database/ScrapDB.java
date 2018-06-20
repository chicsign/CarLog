package com.carlog.chicsign.carlog.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.carlog.chicsign.carlog.model.CarLogModel;

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

    public ArrayList<CarLogModel> scrap_select(CarLogModel scrapCarLogModel){

        database = dbHelper.getReadableDatabase(database);
        ArrayList<CarLogModel> list = ScrapQuery.scrap_select(database, scrapCarLogModel);
        database.close();
        return list;
    }

    public CarLogModel scrap_insert(CarLogModel carLogModel){

        database = dbHelper.getWritableDatabase(database);
        carLogModel = ScrapQuery.scrap_insert(database, carLogModel);
        database.close();

        return carLogModel;
    }

    public void scrap_update(CarLogModel carLogModel){

        database = dbHelper.getWritableDatabase(database);
        ScrapQuery.scrap_update(database, carLogModel);
        database.close();
    }

    public void scrap_delete(CarLogModel carLogModel){

        database = dbHelper.getWritableDatabase(database);
        ScrapQuery.scrap_delete(database, carLogModel);
        database.close();

    }

    public void clear_ScrapDB(){
        database = dbHelper.getWritableDatabase(database);
        ScrapQuery.clear_ScrapDB(database);
        database.close();
    }


}
