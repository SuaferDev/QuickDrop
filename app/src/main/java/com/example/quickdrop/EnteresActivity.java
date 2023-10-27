package com.example.quickdrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class EnteresActivity extends AppCompatActivity {

    private final Intent i = new Intent();
    private FirebaseAuth auth;

    private EditText edittext_login, edittext_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enteres);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));



        ImageView image_back =  findViewById(R.id.image_back);
        LinearLayout linear_continue = findViewById(R.id.linear_continue);
        edittext_login = findViewById(R.id.edittext_login);
        edittext_password = findViewById(R.id.edittext_password);
        auth = FirebaseAuth.getInstance();

        image_back.setOnClickListener(view ->{
            i.setClass(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();
        });

        linear_continue.findViewById(R.id.linear_continue);

        linear_continue.setOnClickListener(view -> {
            if(!edittext_login.getText().toString().isEmpty() && !edittext_password.getText().toString().isEmpty()){
                loginUser(edittext_login.getText().toString() , edittext_password.getText().toString());
            }
        });
    }


    private void loginUser(String email, String password) {

        auth.signInWithEmailAndPassword(email , password).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Toast.makeText(EnteresActivity.this, "Вы вошли", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EnteresActivity.this , ProfileActivity.class);
                intent.putExtra("user_email", email);
                intent.putExtra("user_password", password);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(e -> Toast.makeText(EnteresActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show());

    }
}