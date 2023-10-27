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

public class CustomOrderAdapter extends ArrayAdapter {

    private final List<Order> arr;
    private Context mContext;

    public CustomOrderAdapter(Context context, List<Order> arr) {
        super(context, R.layout.custom_order_element, arr);
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
        LinearLayout linear_status = view.findViewById(R.id.linear_status);
        TextView text_orderNumber = view.findViewById(R.id.text_orderNumber);
        TextView text_status = view.findViewById(R.id.text_status);
        TextView text_time = view.findViewById(R.id.text_time);

        text_orderNumber.setText(arr.get(position).getId());
        text_status.setText(arr.get(position).getStringStatus());

        if(arr.get(position).getStatus()!=2){
            text_time.setText(arr.get(position).getTime());
            if(arr.get(position).getStatus()==0){
                linear_status.setBackgroundResource(R.drawable.order_grey);
            }else{
                linear_status.setBackgroundResource(R.drawable.order_yellow);
            }
        }else{
            text_time.setText("");
            linear_status.setBackgroundResource(R.drawable.order_green);
        }

        return view;
    }
}
