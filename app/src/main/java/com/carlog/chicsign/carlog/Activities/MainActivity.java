package com.carlog.chicsign.carlog.Activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.carlog.chicsign.carlog.Adapter.ItemAddAdapter;
import com.carlog.chicsign.carlog.Database.MySQLiteOpenHelper;
import com.carlog.chicsign.carlog.Dialog.AddItemDialog;
import com.carlog.chicsign.carlog.R;
import com.carlog.chicsign.carlog.model.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chicsign on 2018-05-19.
 */

public class MainActivity extends Activity implements DialogInterface.OnDismissListener, View.OnClickListener {

    ListView listView;
    List<Model> list = new ArrayList<Model>();
    private final String dbName = "carlogDB";
    private final String tableName = "carlog";

    ArrayList<HashMap<String, String>> carlogList;
    private static final String TAG_PRICE = "price";
    private static final String TAG_LITER = "liter";

    SQLiteDatabase sampleDB = null;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.listView);
        carlogList = new ArrayList<HashMap<String, String>>();
        try {


            sampleDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);

            //테이블이 존재하지 않으면 새로 생성합니다.
            sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " + tableName
                    + " (price VARCHAR(20), liter VARCHAR(20) );");

            //테이블이 존재하는 경우 기존 데이터를 지우기 위해서 사용합니다.
            sampleDB.execSQL("DELETE FROM " + tableName);


            sampleDB.close();

        } catch (SQLiteException se) {
            Toast.makeText(getApplicationContext(), se.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("", se.getMessage());


        }

        showList();
        TextView addBtn = (TextView) findViewById(R.id.input_add);
        addBtn.setOnClickListener(this);

    }

    private void showList() {
        try {

            SQLiteDatabase ReadDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);


            //SELECT문을 사용하여 테이블에 있는 데이터를 가져옵니다..
            Cursor c = ReadDB.rawQuery("SELECT * FROM " + tableName, null);

            if (c != null) {

                if (c.moveToFirst()) {
                    do {

                        //테이블에서 두개의 컬럼값을 가져와서
                        String Name = c.getString(c.getColumnIndex("price"));
                        String Phone = c.getString(c.getColumnIndex("liter"));

                        //HashMap에 넣
                        HashMap<String, String> persons = new HashMap<String, String>();

                        persons.put(TAG_PRICE, Name);
                        persons.put(TAG_LITER, Phone);

                        //ArrayList에 추가합니다..
                        carlogList.add(persons);

                    } while (c.moveToNext());
                }
            }

            ReadDB.close();


            //새로운 apapter를 생성하여 데이터를 넣은 후..
            adapter = new SimpleAdapter(
                    this, carlogList, R.layout.card_item,
                    new String[]{TAG_PRICE, TAG_LITER},
                    new int[]{R.id.tv_price, R.id.tv_liter}
            );


            //화면에 보여주기 위해 Listview에 연결합니다.
            listView.setAdapter(adapter);


        } catch (SQLiteException se) {
            Toast.makeText(getApplicationContext(), se.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("", se.getMessage());
        }
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        AddItemDialog dialog = (AddItemDialog) dialogInterface;
        String price = dialog.getPrice();
        String liter = dialog.getLiter();
        sampleDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);

        //테이블이 존재하지 않으면 새로 생성합니다.
        sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " + tableName
                + " (price VARCHAR(20), liter VARCHAR(20) );");

        //테이블이 존재하는 경우 기존 데이터를 지우기 위해서 사용합니다.
        sampleDB.execSQL("DELETE FROM " + tableName);


        //새로운 데이터를 테이블에 집어넣습니다..
        sampleDB.execSQL("INSERT INTO " + tableName
                + " (price, liter)  Values ('" + price + "', '" + liter + "');");

        sampleDB.close();

        showList();
    }

    @Override
    public void onClick(View v) {
        AddItemDialog addDl = new AddItemDialog(MainActivity.this);
        addDl.setOnDismissListener(MainActivity.this);
        addDl.show();
    }
}
