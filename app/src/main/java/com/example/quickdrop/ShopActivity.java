package com.example.quickdrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {

    private final Intent i = new Intent();
    private SharedPreferences SaveBasket;
    private List<Product> basketList;
    private List<Product> productList = new ArrayList<>();
    private final String KEY = "Product";
    private DatabaseReference DataBase;
    private ListView listProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.main_color));

        ImageView image_basket = findViewById(R.id.image_basket);
        ImageView image_avatar = findViewById(R.id.image_avatar);
        TextView text_count = findViewById(R.id.text_count);
        listProduct = findViewById(R.id.listProduct);


        SaveBasket = getSharedPreferences("basketList", Activity.MODE_PRIVATE);
        String jsonFavorite = SaveBasket.getString("basketList", "");
        Gson gson = new Gson();
        Type type = new TypeToken<List<Product>>() {}.getType();
        basketList = gson.fromJson(jsonFavorite, type);
        if(basketList==null){basketList=new ArrayList<>();}
        if(basketList.isEmpty()){
            text_count.setText("");
        }else{
            text_count.setText(String.valueOf(basketList.size()));
        }

        DataBase = FirebaseDatabase.getInstance().getReference(KEY);
        getFromDataBase();


        CustomProductAdapter adapterOrder = new
                CustomProductAdapter(ShopActivity.this,
                productList);
        listProduct.setAdapter(
                adapterOrder);

        image_basket.setOnClickListener(view ->{
            i.setClass(getApplicationContext(), BasketActivity.class);
            startActivity(i);
            SharedPreferences.Editor editorFavorite = SaveBasket.edit();
            Gson gsonMap = new Gson();
            String jsonFavorites = gsonMap.toJson(basketList);
            editorFavorite.putString("basketList", jsonFavorites);
            editorFavorite.apply();
        });

        image_avatar.setOnClickListener(view ->{
            i.setClass(getApplicationContext(), ProfileActivity.class);
            startActivity(i);
        });

        listProduct.setOnItemClickListener((parent, view, position, id) -> {
            basketList.add(productList.get(position));
            text_count.setText(String.valueOf(basketList.size()));
        });
    }

    private void getFromDataBase() {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productList = new ArrayList<>();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Product book = ds.getValue(Product.class);
                    if (book != null) {
                        productList.add(book);
                    }
                }
                setupListView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("TAG", error.getMessage());
            }
        };
        DataBase.addValueEventListener(valueEventListener);
    }

    private void setupListView() {

        CustomProductAdapter adapter = new CustomProductAdapter(this, productList);
        listProduct.setAdapter(adapter);
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