package com.example.villageoffie.userpackage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

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

public class DeathCer extends AppCompatActivity {
    EditText name,sex,dob,btime,place,dor,mname,fname,address,dtime,dreason;
    Button apply;
    DatePickerDialog datePickerDialog;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death_cer);
        name=findViewById(R.id.cname);
        sex=findViewById(R.id.sex);
        dob=findViewById(R.id.dob);
        btime=findViewById(R.id.time);
        place=findViewById(R.id.place);
        dor=findViewById(R.id.dor);
        dtime=findViewById(R.id.dtime);
        dreason=findViewById(R.id.dreason);
        mname=findViewById(R.id.mname);
        fname=findViewById(R.id.fname);
        address=findViewById(R.id.address);
        apply=findViewById(R.id.btnapply);
        dob.setFocusable(false);
        btime.setFocusable(false);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker();
            }
        });
        btime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tiemPicker();
            }
        });
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c);
        dor.setText(formattedDate);
        dor.setFocusable(false);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
                String userid = sp.getString("userid", "");

                // Toast.makeText(this, cname, Toast.LENGTH_SHORT).show();

                ApiInterface apiService =
                        ApiClient.getClient().create(ApiInterface.class);
                Call<reg> call = apiService.applicatin1("application1", userid,name.getText().toString(),sex.getText().toString(),
                        dob.getText().toString(),btime.getText().toString(),place.getText().toString(),dor.getText().toString(),
                        mname.getText().toString(),fname.getText().toString(),address.getText().toString(),dtime.getText().toString(),dreason.getText().toString());
                call.enqueue(new Callback<reg>() {
                    @Override
                    public void onResponse(Call<reg> call, Response<reg> response) {
                        Toast.makeText(DeathCer.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), UserHome.class));
                        finish();
                    }

                    @Override
                    public void onFailure(Call<reg> call, Throwable t) {
                        Toast.makeText(DeathCer.this, t + "", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
    private void datePicker() {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(DeathCer.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        if (monthOfYear < 10 && dayOfMonth < 10) {

                            dob.setText("0" + dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year);

                        }
                        if (monthOfYear < 10 && dayOfMonth >= 10) {
                            dob.setText(dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year);
                        }
                        if (monthOfYear >= 10 && dayOfMonth < 10) {
                            dob.setText("0" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                        if (monthOfYear >= 10 && dayOfMonth >= 10) {
                            dob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }

                        //*************Call Time Picker Here ********************

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private void tiemPicker() {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(DeathCer.this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        mHour = hourOfDay;
                        mMinute = minute;

                        btime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),UserHome.class));finish();
    }
}
