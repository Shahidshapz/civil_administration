package com.example.villageoffie.Others;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.villageoffie.R;
import com.example.villageoffie.adminpackage.Admin;
import com.example.villageoffie.pojo.login;
import com.example.villageoffie.userpackage.UserHome;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
EditText unme,pwd;
Button logn;
TextView sign;
SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unme = findViewById(R.id.editText);
        pwd = findViewById(R.id.editText2);
        logn = findViewById(R.id.button);
        sign = findViewById(R.id.textView2);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
            }
        });
        logn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface apiService =
                        ApiClient.getClient().create(ApiInterface.class);

                Call<login> call = apiService.getlogin("login", unme.getText().toString(), pwd.getText().toString());
                call.enqueue(new Callback<login>() {
                    @Override
                    public void onResponse(Call<login> call, Response<login> response) {

                        if (response.body().getMessage().equals("success")) {
                            if (response.body().getUtype().equals("user")) {
                                Intent i = new Intent(getApplicationContext(), UserHome.class);
                                startActivity(i);

                            } else if (response.body().getUtype().equals("admin")) {
                                Intent i = new Intent(getApplicationContext(), Admin.class);
                                startActivity(i);
                            }
                        }
                        SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed = sp.edit();
                        ed.putString("userid", response.body().getUserId());
                        ed.commit();
                    }

                    @Override
                    public void onFailure(Call<login> call, Throwable t) {
                        Toast.makeText(Login.this, t + "", Toast.LENGTH_SHORT).show();
                    }
                });


            }

        });

    }
}
