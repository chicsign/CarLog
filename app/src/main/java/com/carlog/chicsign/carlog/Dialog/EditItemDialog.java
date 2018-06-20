package com.carlog.chicsign.carlog.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.carlog.chicsign.carlog.Database.ScrapDB;
import com.carlog.chicsign.carlog.Interface.ICarLog;
import com.carlog.chicsign.carlog.R;
import com.carlog.chicsign.carlog.model.CarLogModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Chicsign on 2018-05-21.
 */

public class EditItemDialog extends Dialog {
    private EditText _price;
    private EditText _liter;
    private OnDismissListener _listener;
    private Context cxt;
    private Runnable mRunnable;
    private int position;
    private HashMap<String, Object> bundles;
    private ScrapDB scrapDB = null;
    private ArrayList<ICarLog> mEditList = new ArrayList<>();

    public EditItemDialog(Context context, HashMap<String, Object> bundle) {
        super(context);
        cxt = context;
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        bundles = bundle;
    }

    @Override
    public void onCreate(Bundle $savedInstanceState) {
        super.onCreate($savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        setTitle("이용 내역");
        getDBInfo();
        _price = (EditText) findViewById(R.id.edit_oil_price);
        _liter = (EditText) findViewById(R.id.edit_oil_liter);
        Button btn = (Button) findViewById(R.id.edit_dismissBtn);
        setData(bundles);
        CarLogModel scrapCarLogModel = (CarLogModel) mEditList.get(position);
        scrapDB.scrap_insert(scrapCarLogModel);
        _price.setText(scrapCarLogModel.getPrice());
        _liter.setText(scrapCarLogModel.getLiter());
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Runnable run = mRunnable;
                dismiss();
                CarLogModel scrapCarLogModel = (CarLogModel) mEditList.get(position);
                scrapCarLogModel.setPrice(scrapCarLogModel.getPrice());
                scrapCarLogModel.setLiter(scrapCarLogModel.getLiter());
                scrapDB.scrap_update(scrapCarLogModel);
                run.run();
            }
        });
    }

    private ArrayList<ICarLog> getDBInfo() {
        scrapDB = ScrapDB.getScrapDB(cxt);
        CarLogModel scrapCarLogModel = new CarLogModel();
        mEditList = new ArrayList<>();
        mEditList.addAll(scrapDB.scrap_select(scrapCarLogModel));

        return mEditList;
    }

    public void setData(HashMap<String, Object> bundles) {
        if (bundles.containsKey("run")) {
            mRunnable = (Runnable) bundles.get("run");
            position = (int) bundles.get("position");
        }
    }

    public String getPrice() {
        return _price.getText().toString();
    }

    public String getLiter() {
        return _liter.getText().toString();
    }

    public void setOnDismissListener(OnDismissListener listener) {
        _listener = listener;
    }

}
