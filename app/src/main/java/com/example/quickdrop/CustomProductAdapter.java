package com.example.quickdrop;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class CustomProductAdapter extends ArrayAdapter {

    private final List<Product> arr;
    private Context mContext;

    public CustomProductAdapter(Context context, List<Product> arr) {
        super(context, R.layout.custom_product_element, arr);
        this.arr = arr;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_card, parent, false);
        }
        TextView text_name = view.findViewById(R.id.text_name);
        TextView text_price = view.findViewById(R.id.text_price);
        text_name.setText(arr.get(position).getName());
        text_price.setText(arr.get(position).getName());

        return view;
    }
}