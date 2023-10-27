package com.example.quickdrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private String userEmail;
    private final List<PayType> payTypeList = new ArrayList<>();
    private final List<Order> orderHistory = new ArrayList<>();
    private final List<Order> orderList = new ArrayList<>();
    private ListView list1, listOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.profile_color));

        TextView text_name = findViewById(R.id.text_name);
        TextView text_order_history = findViewById(R.id.text_order_history);
        TextView text_active_order = findViewById(R.id.text_active_order);
        TextView text_check = findViewById(R.id.text_check);
        list1 = findViewById(R.id.list1);
        listOrder = findViewById(R.id.listOrder);

        Intent i = getIntent();
        if(i!=null && i.hasExtra("user_email")){userEmail = i.getStringExtra("user_email");}
        getIntent().getStringExtra("user_email");


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.orderByChild("email").equalTo(userEmail).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String name = snapshot.child("name").getValue(String.class);
                            String surname = snapshot.child("surname").getValue(String.class);
                            text_name.setText(String.valueOf(name+" "+ surname));
                            DataSnapshot d = snapshot.child("paytype");
                            for (DataSnapshot ds : d.getChildren()) {
                                PayType payType = ds.getValue(PayType.class);
                                if (payType != null) {
                                    payTypeList.add(payType);
                                }
                            }
                            CustomPayTypeAdapter adapter = new CustomPayTypeAdapter(ProfileActivity.this, payTypeList);
                            list1.setAdapter(adapter);

                            DataSnapshot oO = snapshot.child("order");
                            for (DataSnapshot ds : oO.getChildren()) {
                                Order payType = ds.getValue(Order.class);
                                if (payType != null) {
                                    orderList.add(payType);
                                }
                            }
                            CustomOrderAdapter adapterOrder = new CustomOrderAdapter(ProfileActivity.this, orderList);
                            listOrder.setAdapter(adapterOrder);

                            DataSnapshot oH = snapshot.child("history");
                            for (DataSnapshot ds : oH.getChildren()) {
                                Order payType = ds.getValue(Order.class);
                                if (payType != null) {
                                    orderList.add(payType);
                                }
                            }
                            if(orderHistory.isEmpty()){
                                text_check.setText("Заказов нет");
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        System.out.println("Ошибка: " + databaseError.getMessage());
                    }
                });

        text_order_history.setOnClickListener(view ->{
            CustomOrderAdapter adapterOrder = new CustomOrderAdapter(ProfileActivity.this, orderList);
            listOrder.setAdapter(adapterOrder);
            text_order_history.setTextColor(0xFF131313);
            text_order_history.setBackgroundResource(R.drawable.button_background);
            text_active_order.setTextColor(0xFFFFFFFF);
            text_active_order.setBackgroundColor(0xFF131313);
            if(orderHistory.isEmpty()){
                text_check.setText("Заказов нет");
            }
        });

        text_active_order.setOnClickListener(view ->{
            CustomOrderAdapter adapterOrder = new CustomOrderAdapter(ProfileActivity.this, orderList);
            listOrder.setAdapter(adapterOrder);
            text_active_order.setTextColor(0xFF131313);
            text_active_order.setBackgroundResource(R.drawable.button_background);
            text_order_history.setTextColor(0xFFFFFFFF);
            text_order_history.setBackgroundColor(0xFF131313);
            if(orderList.isEmpty()){
                text_check.setText("Заказов нет");
            }

        });

    }

    private void createAddCardDialog(){
        Dialog dialog = new Dialog(ProfileActivity.this);
        dialog.setContentView(R.layout.custom_dialog_nointernet);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        dialog.show();
    }
}