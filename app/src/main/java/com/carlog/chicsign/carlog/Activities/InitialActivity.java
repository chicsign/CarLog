package com.carlog.chicsign.carlog.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.carlog.chicsign.carlog.Database.ScrapDB;
import com.carlog.chicsign.carlog.Interface.FolderScrapModel;
import com.carlog.chicsign.carlog.R;
import com.carlog.chicsign.carlog.model.Model;

import java.util.ArrayList;

public class InitialActivity extends Activity {
    private ArrayList<FolderScrapModel> mEditList = new ArrayList<>();
    private Model scrapModel;
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

    private ArrayList<FolderScrapModel> getDBInfo() {

        scrapDB = ScrapDB.getScrapDB(mContext);
        scrapModel = new Model();
        mEditList = new ArrayList<>();

        mEditList.addAll(scrapDB.scrap_select(scrapModel));

        return mEditList;
    }
}
