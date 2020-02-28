package com.example.villageoffie.adminpackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.villageoffie.Others.Login;
import com.example.villageoffie.R;
import com.example.villageoffie.pojo.login;
import com.example.villageoffie.pojo.reg;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddVillage extends AppCompatActivity {
    EditText name,taluk,district,state,place,pinno,mobile,username,password;
    Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_village);
name=findViewById(R.id.namevill);
        taluk=findViewById(R.id.nametaluk);
        district=findViewById(R.id.namedistrict);
        state=findViewById(R.id.statename);
        place=findViewById(R.id.nameplace);
        pinno=findViewById(R.id.pin);
        mobile=findViewById(R.id.mob);
        username=findViewById(R.id.uservill);
        password=findViewById(R.id.passvill);
        add=findViewById(R.id.addvillage);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface apiService =
                        ApiClient.getClient().create(ApiInterface.class);

                Call<reg> call = apiService.addvillage("add_village", name.getText().toString(), taluk.getText().toString(),
                        district.getText().toString(), state.getText().toString(), place.getText().toString(), pinno.getText().toString()
                        , mobile.getText().toString(), username.getText().toString(), password.getText().toString());

            call.enqueue(new Callback<reg>() {
                @Override
                public void onResponse(Call<reg> call, Response<reg> response) {
                    Toast.makeText(AddVillage.this, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(), AddVillage.class);
                    startActivity(i);
                }

                @Override
                public void onFailure(Call<reg> call, Throwable t) {
                    Toast.makeText(AddVillage.this, t+"", Toast.LENGTH_SHORT).show();
                }
            });
            }

        });
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(), Admin.class);
        startActivity(i);
    }
}
