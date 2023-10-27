package com.example.quickdrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.profile_color));

        TextView text_name = findViewById(R.id.text_name);

        Intent i = getIntent();
        if(i!=null && i.hasExtra("user_email")){
            userEmail = i.getStringExtra("user_email");
        }

        getIntent().getStringExtra("user_email");
        Toast.makeText(ProfileActivity.this, userEmail, Toast.LENGTH_SHORT).show();


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        databaseReference.orderByChild("email").equalTo(userEmail)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String name = snapshot.child("name").getValue(String.class);
                            Toast.makeText(ProfileActivity.this, name, Toast.LENGTH_SHORT).show();
                            text_name.setText(name);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Ошибка при получении данных
                        System.out.println("Ошибка: " + databaseError.getMessage());
                    }
                });
    }
}