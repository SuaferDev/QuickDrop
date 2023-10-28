package com.example.quickdrop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class ShopActivity extends AppCompatActivity {

    private final Intent i = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.main_color));

        ImageView image_basket = findViewById(R.id.image_basket);
        ImageView image_avatar = findViewById(R.id.image_avatar);

        image_basket.setOnClickListener(view ->{
            i.setClass(getApplicationContext(), BasketActivity.class);
            startActivity(i);
            finish();
        });

        image_avatar.setOnClickListener(view ->{
            i.setClass(getApplicationContext(), ProfileActivity.class);
            startActivity(i);
            finish();
        });
    }

}