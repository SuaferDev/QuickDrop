package com.example.quickdrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.main_color));



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
            }else{
                setError();
            }
        });

        edittext_login.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edittext_login.setBackgroundResource(R.drawable.background_edite_text);
            }
            @Override public void afterTextChanged(Editable editable) {}
        });

        edittext_password.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edittext_password.setBackgroundResource(R.drawable.background_edite_text);
            }
            @Override public void afterTextChanged(Editable editable) {}
        });
    }

    private void setError(){
        edittext_login.setBackgroundResource(R.drawable.background_edit_text_red);
        edittext_password.setBackgroundResource(R.drawable.background_edit_text_red);
    }


    private void loginUser(String email, String password){
        auth.signInWithEmailAndPassword(email , password).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                UserData.getInstance().setLogin(email);
                Toast.makeText(EnteresActivity.this, "Мы рады Вас видеть", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EnteresActivity.this , ProfileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(e -> {
            edittext_password.setText("");
            setError();
        });

    }
}