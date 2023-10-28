package com.example.quickdrop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;


public class WhereMyOrderActivity extends AppCompatActivity {

    private final Intent i = new Intent();
    private String time_t;
    private int status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where_my_order);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.main_color));

        ImageView image_back = findViewById(R.id.image_back);
        ImageView image_work = findViewById(R.id.image_work);
        ImageView image_fly = findViewById(R.id.image_fly);
        ImageView image_complete = findViewById(R.id.image_complete);
        ImageView image_progress = findViewById(R.id.image_progress);
        TextView time = findViewById(R.id.time);

        image_back.setOnClickListener(view ->{
            i.setClass(getApplicationContext(), ProfileActivity.class);
            startActivity(i);
            finish();
        });

        Intent i = getIntent();
        if(i!=null && i.hasExtra("time")){time_t = i.getStringExtra("time");}
        getIntent().getStringExtra("time");

        Intent ip = getIntent();
        if (ip != null) {
            String statusStr = ip.getStringExtra("status");
            if (statusStr != null) {
                status = Integer.parseInt(statusStr);
                Toast.makeText(WhereMyOrderActivity.this, "+", Toast.LENGTH_SHORT).show();
            }
        }

        /*
        if(i!=null && i.hasExtra("status")){
            status = Integer.parseInt(
                    Objects.requireNonNull(i.getStringExtra("status")));}
        getIntent().getStringExtra("status");

         */
        if(status==0){
            image_work.setImageResource(R.drawable.icon_progress_work);
            image_work.setBackgroundResource(R.drawable.background_phase);
            image_progress.setImageResource(R.drawable.box_image);
        }
        if(status==1){
            image_fly.setImageResource(R.drawable.icon_progress);
            image_fly.setBackgroundResource(R.drawable.background_phase);
            image_progress.setImageResource(R.drawable.drone_image);
        }
        if(status!=0 && status!=1){
            image_complete.setImageResource(R.drawable.icon_progress_time);
            image_complete.setBackgroundResource(R.drawable.background_phase);
            image_progress.setImageResource(R.drawable.drone_image);
        }
    }
}