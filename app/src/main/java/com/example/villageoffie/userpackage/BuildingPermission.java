package com.example.villageoffie.userpackage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.villageoffie.Others.Validations;
import com.example.villageoffie.R;
import com.example.villageoffie.pojo.reg;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuildingPermission extends AppCompatActivity {
    EditText name, sex, dob, btime, place, dor, mname, fname, address;
    Button apply;
    DatePickerDialog datePickerDialog;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_permission);
        name = findViewById(R.id.cname);
        sex = findViewById(R.id.sex);
        dob = findViewById(R.id.dob);
        btime = findViewById(R.id.time);

        apply = findViewById(R.id.btnapply);
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        final String formattedDate = df.format(c);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
                String userid = sp.getString("userid", "");

                // Toast.makeText(this, cname, Toast.LENGTH_SHORT).show();

                if (name.getText().toString().isEmpty()) {
                    name.setError("Please enter owner name");
                } else if (sex.getText().toString().isEmpty()) {
                    sex.setError("Please enter no of rooms");
                } else if (dob.getText().toString().isEmpty()) {
                    dob.setError("Please enter total Squarefeet");
                }
                else if (btime.getText().toString().isEmpty()) {
                    btime.setError("Please enter address");
                }
               else{
                ApiInterface apiService =
                        ApiClient.getClient().create(ApiInterface.class);
                Call<reg> call = apiService.applicatin3("application3", userid, name.getText().toString(), sex.getText().toString(),
                        dob.getText().toString(), btime.getText().toString(),formattedDate);
                call.enqueue(new Callback<reg>() {
                    @Override
                    public void onResponse(Call<reg> call, Response<reg> response) {
                        Toast.makeText(BuildingPermission.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), UserHome.class));
                        finish();
                    }

                    @Override
                    public void onFailure(Call<reg> call, Throwable t) {
                        Toast.makeText(BuildingPermission.this, t + "", Toast.LENGTH_SHORT).show();
                    }
                });
            }}
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), UserHome.class));
        finish();
    }
}