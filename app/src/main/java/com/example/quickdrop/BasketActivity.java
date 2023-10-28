package com.example.quickdrop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BasketActivity extends AppCompatActivity {

    private final Intent i = new Intent();
    private SharedPreferences SaveFavorite;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.main_color));

        SaveFavorite = getSharedPreferences("favoritesBook", Activity.MODE_PRIVATE);
        String jsonFavorite = SaveFavorite.getString("favoritesBook", "");
        Gson gson = new Gson();
        Type type = new TypeToken<List<Product>>() {}.getType();
        productList = gson.fromJson(jsonFavorite, type);
        if(productList==null){productList=new ArrayList<>();}

        ImageView image_back = findViewById(R.id.image_back);
        ListView listOrder = findViewById(R.id.listOrder);

        image_back.setOnClickListener(view -> {
            finish();
        });

        CustomProductAdapter adapterOrder = new CustomProductAdapter(BasketActivity.this, productList);
        listOrder.setAdapter(adapterOrder);
    }
}