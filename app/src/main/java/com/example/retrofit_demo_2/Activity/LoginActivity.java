package com.example.retrofit_demo_2.Activity;

import static com.example.retrofit_demo_2.Activity.SplashScreen_Activity.editor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.retrofit_demo_2.InstanceClass;
import com.example.retrofit_demo_2.Models.LoginData;
import com.example.retrofit_demo_2.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText txt1,txt2;
    Button btn1;
    ImageView img;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txt1 = findViewById(R.id.main3_logout_email_edit);
        txt2 = findViewById(R.id.main3_logout_password_edittext);
        btn1 = findViewById(R.id.main3_logout_sign_in_btn);
        img = findViewById(R.id.main3_logout_back_img);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InstanceClass.API_Calling().loginUser(txt1.getText().toString(),txt2.getText().toString()).enqueue(new Callback<LoginData>() {
                    @Override
                    public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                        if(response.body().getConnection()==1)
                        {
                            if(response.body().getResult()==1)
                            {
                                Toast.makeText(LoginActivity.this, "Your Successful Login", Toast.LENGTH_SHORT).show();

                                editor.putBoolean("isLogin",true);
                                editor.putString("id",response.body().getUserdata().getId());
                                editor.putString("name",response.body().getUserdata().getName());
                                editor.putString("email",response.body().getUserdata().getEmail());
                                editor.putString("password",response.body().getUserdata().getPassword());
                                editor.commit();
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                editor.putString("isLogin","false");
                                Toast.makeText(LoginActivity.this, "Can't Login", Toast.LENGTH_LONG).show();
                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<LoginData> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Your Not Successful Login", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}