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

public class CustomPayTypeAdapter extends ArrayAdapter {

    private final List<PayType> arr;
    private Context mContext;

    public CustomPayTypeAdapter(Context context, List<PayType> arr) {
        super(context, R.layout.custom_card, arr);
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
        LinearLayout linear = view.findViewById(R.id.linear);
        TextView text_cardName = view.findViewById(R.id.text_cardName);
        ImageView image_avatar = view.findViewById(R.id.image_avatar);

        if(arr.get(position).getCardName()==0){
            image_avatar.setImageResource(R.drawable.icon_add);
            text_cardName.setForegroundGravity(Gravity.CENTER);
            text_cardName.setText("Добавить карту");
            image_avatar.setImageResource(R.drawable.icon_add);
            text_cardName.setTextColor(0xFFC1C1C1);
        }else{
            text_cardName.setText(String.valueOf(arr.get(position).getCardName()));
        }

        return view;
    }
}

