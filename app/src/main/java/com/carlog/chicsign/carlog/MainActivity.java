package com.carlog.chicsign.carlog;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Chicsign on 2018-05-19.
 */

public class MainActivity extends Activity {
    private ListView listView;
    static final String[] LIST_MENU = {"LIST1", "LIST2", "LIST3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, LIST_MENU);


        listView = (ListView) findViewById(R.id.car_listView);
        listView.setAdapter(adapter);


    }

}
