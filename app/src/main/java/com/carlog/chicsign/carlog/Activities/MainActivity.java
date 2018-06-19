package com.carlog.chicsign.carlog.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.carlog.chicsign.carlog.Database.ScrapDB;
import com.carlog.chicsign.carlog.Dialog.AddItemDialog;
import com.carlog.chicsign.carlog.Dialog.EditItemDialog;
import com.carlog.chicsign.carlog.Interface.FolderScrapModel;
import com.carlog.chicsign.carlog.R;
import com.carlog.chicsign.carlog.model.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chicsign on 2018-05-19.
 */

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
