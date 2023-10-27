package com.example.quickdrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {

    private final Intent i = new Intent();
    private EditText edittext_name,edittext_surname,edittext_last_name,edittext_login,edittext_password;
    private ImageView image_confirm;
    private boolean last_name = true;
    private boolean booleanSpam = true;
    private boolean booleanAcsess = false;
    private FirebaseAuth auth;
    private DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.main_color));

        LinearLayout linear_continue = findViewById(R.id.linear_continue);
        LinearLayout linear_no_last_name = findViewById(R.id.linear_no_last_name);
        LinearLayout linear_spam = findViewById(R.id.linear_spam);
        LinearLayout linear_acsses = findViewById(R.id.linear_acsses);

        ImageView image_last_name = findViewById(R.id.image_last_name);
        ImageView image_back = findViewById(R.id.image_back);
        image_confirm = findViewById(R.id.image_confirm);
        edittext_name = findViewById(R.id.edittext_name);
        edittext_surname = findViewById(R.id.edittext_surname);
        edittext_last_name = findViewById(R.id.edittext_last_name);
        edittext_login = findViewById(R.id.edittext_login);
        edittext_password = findViewById(R.id.edittext_password);

        auth = FirebaseAuth.getInstance();
        mRootRef = FirebaseDatabase.getInstance().getReference();

        image_back.setOnClickListener(view ->{
            i.setClass(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();
        });

        linear_no_last_name.setOnClickListener(view ->{
            if(last_name){
                edittext_last_name.setBackgroundResource(R.drawable.rounded_light_grey);
                image_last_name.setImageResource(R.drawable.icon_check_true);
                last_name = false;
            }else{
                last_name = true;
                edittext_last_name.setBackgroundResource(R.drawable.background_edite_text);
                image_last_name.setImageResource(R.drawable.icon_check_box);
            }
        });

        linear_acsses.setOnClickListener(view ->{
            if(booleanAcsess){
                image_confirm.setImageResource(R.drawable.icon_check_box);
                booleanAcsess = false;
            }else{
                booleanAcsess = true;
                image_confirm.setImageResource(R.drawable.icon_check_true);
            }
        });

        linear_spam.setOnClickListener(view ->{
            if(booleanSpam){
                image_confirm.setImageResource(R.drawable.icon_check_box);
                booleanSpam = false;
            }else{
                booleanSpam = true;
                image_confirm.setImageResource(R.drawable.icon_check_true);
            }
        });

        edittext_name.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {edittext_name.setBackgroundResource(R.drawable.background_edite_text);}
            @Override public void afterTextChanged(Editable editable) {}
        });
        edittext_surname.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {edittext_surname.setBackgroundResource(R.drawable.background_edite_text);}
            @Override public void afterTextChanged(Editable editable) {}
        });
        edittext_last_name.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {edittext_last_name.setBackgroundResource(R.drawable.background_edite_text);}
            @Override public void afterTextChanged(Editable editable) {}
        });
        edittext_login.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {edittext_login.setBackgroundResource(R.drawable.background_edite_text);}
            @Override public void afterTextChanged(Editable editable) {}
        });
        edittext_password.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {edittext_password.setBackgroundResource(R.drawable.background_edite_text);}
            @Override public void afterTextChanged(Editable editable) {}
        });

        linear_continue.setOnClickListener(view ->{
            if(checkField()){
                registerUser(edittext_name.getText().toString(), edittext_surname.getText().toString(), edittext_last_name.getText().toString(), edittext_login.getText().toString(),edittext_password.getText().toString());
            }
        });
    }

    private boolean checkField(){
        boolean check = true;
        if(edittext_name.getText().toString().isEmpty()){
            edittext_name.setBackgroundResource(R.drawable.background_edit_text_red);check = false;
        }

        if(edittext_surname.getText().toString().isEmpty()){
            edittext_surname.setBackgroundResource(R.drawable.background_edit_text_red);check = false;
        }

        if(edittext_last_name.getText().toString().isEmpty() && last_name){
            edittext_last_name.setBackgroundResource(R.drawable.background_edit_text_red);check = false;
        }

        if(edittext_login.getText().toString().isEmpty()){
            edittext_login.setBackgroundResource(R.drawable.background_edit_text_red);check = false;
        }

        if(edittext_password.getText().toString().isEmpty()){
            edittext_password.setBackgroundResource(R.drawable.background_edit_text_red);check = false;
        }

        if(!booleanAcsess){
            image_confirm.setImageResource(R.drawable.icon_check_box_red);
        }

        return check;
    }

    private void registerUser(final String name, final String surname, final String lastname, final String email, String password) {
        auth.createUserWithEmailAndPassword(email , password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                HashMap<Object , Object> map = new HashMap<>();
                List<PayType> payTypeList = new ArrayList<>();
                payTypeList.add(new PayType());
                List<Order> historyList = new ArrayList<>();
                List<Order> orderList = new ArrayList<>();
                map.put("name" , name);
                map.put("surname" , surname);
                map.put("lastname" , lastname);
                map.put("email", email);
                map.put("password", password);
                map.put("history", historyList);
                map.put("order", orderList);
                map.put("paytype", payTypeList);
                map.put("id" , Objects.requireNonNull(auth.getCurrentUser()).getUid());

                mRootRef.child("Users").child(auth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegistrationActivity.this, "Регистрация успешна", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegistrationActivity.this , ProfileActivity.class);
                            intent.putExtra("user_email", email);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    }
                });

            }
        }).addOnFailureListener(e -> Toast.makeText(RegistrationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show());

    }

}