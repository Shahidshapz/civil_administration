package com.example.villageoffie.userpackage;

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

import androidx.appcompat.app.AppCompatActivity;

import com.example.villageoffie.R;
import com.example.villageoffie.adminpackage.AddVillage;
import com.example.villageoffie.pojo.reg;
import com.example.villageoffie.village.Validation;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BirtCer extends AppCompatActivity {
    EditText name, sex, dob, btime, place, dor, mname, fname, address;
    Button apply;
    DatePickerDialog datePickerDialog;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birt_cer);
        name = findViewById(R.id.cname);
        sex = findViewById(R.id.sex);
        dob = findViewById(R.id.dob);
        btime = findViewById(R.id.time);
        place = findViewById(R.id.place);
        dor = findViewById(R.id.dor);
        mname = findViewById(R.id.mname);
        fname = findViewById(R.id.fname);
        address = findViewById(R.id.address);
        apply = findViewById(R.id.btnapply);
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
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

                if (name.getText().toString().isEmpty() || !name.getText().toString().matches(Validation.text)) {
                    name.setError("Please enter your name");
                } else if (sex.getText().toString().isEmpty()) {
                    sex.setError("Please enter your sex");
                } else if (dob.getText().toString().isEmpty()) {
                    dob.setError("Please enter your date of birth");
                } else if (btime.getText().toString().isEmpty()) {
                    btime.setError("Please enter your birth time");
                } else if (place.getText().toString().isEmpty()) {
                    place.setError("Please enter your birth place");
                }else if (dor.getText().toString().isEmpty()) {
                    dor.setError("Please enter date of registration");
                }
                else if (mname.getText().toString().isEmpty()) {
                    mname.setError("Please enter your mother name");
                }
                else if (fname.getText().toString().isEmpty()) {
                    fname.setError("Please enter your father name");
                }
                else if (address.getText().toString().isEmpty()) {
                    address.setError("Please enter perment address");
                }
                else {
                    ApiInterface apiService =
                            ApiClient.getClient().create(ApiInterface.class);
                    Call<reg> call = apiService.applicatin("application", userid, name.getText().toString(), sex.getText().toString(),
                            dob.getText().toString(), btime.getText().toString(), place.getText().toString(), dor.getText().toString(),
                            mname.getText().toString(), fname.getText().toString(), address.getText().toString());
                    call.enqueue(new Callback<reg>() {
                        @Override
                        public void onResponse(Call<reg> call, Response<reg> response) {
                            Toast.makeText(BirtCer.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), UserHome.class));
                            finish();
                        }

                        @Override
                        public void onFailure(Call<reg> call, Throwable t) {
                            Toast.makeText(BirtCer.this, t + "", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void datePicker() {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(BirtCer.this,
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
       // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private void tiemPicker() {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(BirtCer.this,
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
        startActivity(new Intent(getApplicationContext(), UserHome.class));
        finish();
    }
}
