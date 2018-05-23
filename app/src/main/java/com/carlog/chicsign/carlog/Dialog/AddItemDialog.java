package com.carlog.chicsign.carlog.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.carlog.chicsign.carlog.R;

/**
 * Created by Chicsign on 2018-05-21.
 */

public class AddItemDialog extends Dialog {
    private EditText _price;
    private EditText _liter;
    private OnDismissListener _listener;
    private Context cxt;

    public AddItemDialog(Context context) {
        super(context);
        cxt = context;
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);


    }

    @Override
    public void onCreate(Bundle $savedInstanceState) {
        super.onCreate($savedInstanceState);
        setContentView(R.layout.activity_add_item);
        setTitle("Enter Your Name");

        _price = (EditText) findViewById(R.id.oil_price);
        _liter = (EditText) findViewById(R.id.oil_liter);
        Button btn = (Button) findViewById(R.id.dismissBtn);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (_listener == null) {
                } else {
                    _listener.onDismiss(AddItemDialog.this);
                }
                dismiss();
            }
        });
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
