package com.carlog.chicsign.carlog.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.carlog.chicsign.carlog.R;
import com.carlog.chicsign.carlog.model.Model;

import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.VIBRATOR_SERVICE;

/**
 * Created by Chicsign on 2018-05-21.
 */

public class ItemAddAdapter extends ArrayAdapter<Model> {
    private final List<Model> list;
    private final Activity context;

    public ItemAddAdapter(Activity context, List<Model> list) {
        super(context, R.layout.card_item, list);
        this.context = context;
        this.list = list;
    }

    static class ViewHolder {
        protected TextView price;
        protected TextView liter;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            convertView = inflator.inflate(R.layout.card_item, null);
            viewHolder = new ViewHolder();
            viewHolder.price = (TextView) convertView.findViewById(R.id.tv_price);
            viewHolder.liter = (TextView) convertView.findViewById(R.id.tv_liter);
            convertView.setTag(viewHolder);
            convertView.setTag(R.id.tv_price, viewHolder.price);
            convertView.setTag(R.id.tv_liter, viewHolder.liter);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.price.setText(list.get(position).getPrice());
        viewHolder.liter.setText(list.get(position).getLiter());

        return convertView;
    }

}
