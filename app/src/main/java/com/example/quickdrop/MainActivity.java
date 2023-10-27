package com.example.quickdrop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final Intent i = new Intent();
    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.main_color));

        TextView textEnteres = findViewById(R.id.textEnteres);
        TextView textCreateAccaunt = findViewById(R.id.textCreateAccaunt);

        textEnteres.setOnClickListener(view ->{
            i.setClass(getApplicationContext(), EnteresActivity.class);
            startActivity(i);
            finish();
        });

        textEnteres.setOnClickListener(view ->{
            i.setClass(getApplicationContext(), RegistrationActivity.class);
            startActivity(i);
            finish();
        });

    }

    @Override
    public void onBackPressed(){
        if(backPressedTime+2000>System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }else{
            backToast = Toast.makeText(getBaseContext(), "Нажмите ещё раз для выхода", Toast.LENGTH_LONG);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}