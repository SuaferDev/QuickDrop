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
    private SharedPreferences SaveBasket;
    private List<Product> basketList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.main_color));

        SaveBasket = getSharedPreferences("basketList", Activity.MODE_PRIVATE);
        String jsonFavorite = SaveBasket.getString("basketList", "");
        Gson gson = new Gson();
        Type type = new TypeToken<List<Product>>() {}.getType();
        basketList = gson.fromJson(jsonFavorite, type);
        if(basketList==null){basketList=new ArrayList<>();}


        ImageView image_back = findViewById(R.id.image_back);
        ListView listOrder = findViewById(R.id.listOrder);

        image_back.setOnClickListener(view -> {
            finish();
        });

        CustomProductAdapter adapterOrder = new CustomProductAdapter(BasketActivity.this, basketList);
        listOrder.setAdapter(adapterOrder);

        listOrder.setOnItemClickListener((parent, view, position, id) -> {

        });
    }

    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences.Editor editorFavorite = SaveBasket.edit();
        Gson gsonMap = new Gson();
        String jsonFavorites = gsonMap.toJson(basketList);
        editorFavorite.putString("basketList", jsonFavorites);
        editorFavorite.apply();
    }
}