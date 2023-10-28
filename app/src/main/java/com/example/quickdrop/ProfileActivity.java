package com.example.quickdrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ProfileActivity extends AppCompatActivity {

    private Intent i = new Intent();
    private String userEmail;
    private final List<PayType> payTypeList = new ArrayList<>();
    private final List<Order> orderHistory = new ArrayList<>();
    private final List<Order> orderList = new ArrayList<>();

    private final List<Adress> adressList = new ArrayList<>();
    private ListView list1, listOrder;
    private boolean status = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.profile_color));

        TextView text_name = findViewById(R.id.text_name);
        TextView text_order_history = findViewById(R.id.text_order_history);
        TextView text_active_order = findViewById(R.id.text_active_order);
        TextView text_check = findViewById(R.id.text_check);
        ImageView image_setting = findViewById(R.id.image_basket);
        list1 = findViewById(R.id.list1);
        listOrder = findViewById(R.id.listOrder);

        userEmail = UserData.getInstance().getLogin();


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.orderByChild("email").equalTo(userEmail).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String name = snapshot.child("name").getValue(String.class);
                            String surname = snapshot.child("surname").getValue(String.class);
                            text_name.setText(String.valueOf(name+" "+ surname));
                            text_name.setTextColor(0xFFFFFFFF);
                            text_name.setBackgroundColor(0xFF131313);

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
                            Toast.makeText(ProfileActivity.this, String.valueOf(payTypeList.size()) , Toast.LENGTH_SHORT).show();
                            for (DataSnapshot ds : oO.getChildren()) {
                                Order order = ds.getValue(Order.class);
                                if (order != null) {
                                    orderList.add(order);
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
                            if(orderList.isEmpty()){
                                text_check.setText("Заказов нет");
                            }else{
                                text_check.setText("");
                            }


                            DataSnapshot oA = snapshot.child("adress");
                            for (DataSnapshot ds : oA.getChildren()) {
                                Adress payType = ds.getValue(Adress.class);
                                if (payType != null) {
                                    adressList.add(payType);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        System.out.println("Ошибка: " + databaseError.getMessage());
                    }
                });

        text_order_history.setOnClickListener(view ->{
            CustomOrderAdapter adapterOrder = new CustomOrderAdapter(ProfileActivity.this, orderHistory);
            listOrder.setAdapter(adapterOrder);
            text_order_history.setTextColor(0xFF131313);
            text_order_history.setBackgroundResource(R.drawable.button_background);
            text_active_order.setTextColor(0xFFFFFFFF);
            text_active_order.setBackgroundColor(0xFF131313);
            if(orderHistory.isEmpty()){
                text_check.setText("Заказов нет");
            }else{
                text_check.setText("");
            }
            status = false;
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
            }else{
                text_check.setText("");
            }
            status = true;

        });
        image_setting.setOnClickListener(view ->{
            i.setClass(getApplicationContext(), WhereMyOrderActivity.class);
            startActivity(i);
        });

        listOrder.setOnItemClickListener((parent, view, position, id) -> {
            if(status){
                Intent intent = new Intent(ProfileActivity.this , WhereMyOrderActivity.class);
                intent.putExtra("time", orderList.get(position).getTime());
                intent.putExtra("status", orderList.get(position).getStatus());
                startActivity(intent);
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

    private void drone(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("drone");

                String userId = "123";
                String name = "John";

                myRef.child(userId).child("name").setValue(name);
            }
        }, 0, 5000);
    }
}