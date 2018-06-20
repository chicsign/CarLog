package com.carlog.chicsign.carlog.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.carlog.chicsign.carlog.Database.ScrapDB;
import com.carlog.chicsign.carlog.Interface.ICarLog;
import com.carlog.chicsign.carlog.model.CarLogModel;

import java.util.ArrayList;

public class InitialActivity extends Activity {
    private ArrayList<ICarLog> mEditList = new ArrayList<>();
    private CarLogModel scrapCarLogModel;
    private ScrapDB scrapDB = null;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        getDBInfo();

        if(mEditList.size() >0){
            Intent intent = new Intent(InitialActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
        }else {
            Intent intent = new Intent(InitialActivity.this, AddCarInfoActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
        }

    }

    private ArrayList<ICarLog> getDBInfo() {

        scrapDB = ScrapDB.getScrapDB(mContext);
        scrapCarLogModel = new CarLogModel();
        mEditList = new ArrayList<>();

        mEditList.addAll(scrapDB.scrap_select(scrapCarLogModel));

        return mEditList;
    }
}
