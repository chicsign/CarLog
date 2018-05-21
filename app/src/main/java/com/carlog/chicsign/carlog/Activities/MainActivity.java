package com.carlog.chicsign.carlog.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.carlog.chicsign.carlog.Adapter.ItemAddAdapter;
import com.carlog.chicsign.carlog.Dialog.AddItemDialog;
import com.carlog.chicsign.carlog.R;
import com.carlog.chicsign.carlog.model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chicsign on 2018-05-19.
 */

public class MainActivity extends Activity implements DialogInterface.OnDismissListener, View.OnClickListener {

    ListView listView;
    ArrayAdapter<Model> adapter;
    List<Model> list = new ArrayList<Model>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new ItemAddAdapter(this, getModel());
        listView.setAdapter(adapter);

        TextView addBtn = (TextView) findViewById(R.id.input_add);
        addBtn.setOnClickListener(this);


    }

    private List<Model> getModel() {
        return list;
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        AddItemDialog dialog = (AddItemDialog) dialogInterface;
        String price = dialog.getPrice();
        String liter = dialog.getLiter();
        list.add(new Model(price, liter));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        AddItemDialog addDl = new AddItemDialog(MainActivity.this);
        addDl.setOnDismissListener(MainActivity.this);
        addDl.show();

    }
}
