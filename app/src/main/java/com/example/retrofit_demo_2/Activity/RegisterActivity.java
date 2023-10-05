package com.example.retrofit_demo_2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofit_demo_2.InstanceClass;
import com.example.retrofit_demo_2.Models.RegisterData;
import com.example.retrofit_demo_2.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText txt1,txt2,txt3;
    Button btn1;
    TextView txt4;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txt1 = findViewById(R.id.main2_name_edit);
        txt2 = findViewById(R.id.main2_email_edit);
        txt3 = findViewById(R.id.main2_password_edit);
        btn1 = findViewById(R.id.main2_sign_up_button);
        txt4 = findViewById(R.id.main2_a_registerd_text);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InstanceClass.API_Calling().registerUser(txt1.getText().toString(),txt2.getText().toString(),txt3.getText().toString()).enqueue(new Callback<RegisterData>() {
                    @Override
                    public void onResponse(Call<RegisterData> call, Response<RegisterData> response) {

                        Toast.makeText(RegisterActivity.this, "Your Successful Registerd", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<RegisterData> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "Your Not Successful Registerd", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}