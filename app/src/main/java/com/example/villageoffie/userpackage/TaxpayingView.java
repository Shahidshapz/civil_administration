package com.example.villageoffie.userpackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.villageoffie.R;
import com.example.villageoffie.pojo.login;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaxpayingView extends AppCompatActivity {
EditText cno,cvv,amt;
Button pay;
String uid,tid,bid,type;
Double amnt=0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxpaying_view);
        cno=findViewById(R.id.cno);
        cvv=findViewById(R.id.cvv);
        amt=findViewById(R.id.amt);
        pay=findViewById(R.id.time);
        SharedPreferences sp = getSharedPreferences("btax", Context.MODE_PRIVATE);
        if(sp.getString("taxtype","").equals("Professional")){
            type=sp.getString("taxtype","");
            amt.setText("1200");
            amt.setFocusable(false);
            uid= sp.getString("uid","");
                    tid=sp.getString("tid","");

            pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(cno.getText().toString().isEmpty()){
                        cno.setError("Please enter your 10 digit card number");
                    }
                    else if(cvv.getText().toString().isEmpty()){
                        cvv.setError("Please enter your cvv");
                    }
                    else{
                        Payment(uid,tid,type);
                    }
                }
            });
        }
        else{
            type=sp.getString("taxtype","");
            uid= sp.getString("uid","");
            bid=sp.getString("bid","");
            String sqft=sp.getString("sqft","");
            amnt=Double.parseDouble(sqft)*5;
            amt.setText(amnt+"");
            amt.setFocusable(false);
            pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(cno.getText().toString().isEmpty()){
                        cno.setError("Please enter your 10 digit card number");
                    }
                    else if(cvv.getText().toString().isEmpty()){
                        cvv.setError("Please enter your cvv");
                    }
                    else{
                        Payment(uid,bid,type);
                    }
                }
            });

        }


    }

    private void Payment(String uid,String id,String type) {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c);
        ApiInterface apiinterface = ApiClient.getClient().create(ApiInterface.class);
        Call<login> call = apiinterface.paytax("Billpay",uid,id,type,amt.getText().toString() ,formattedDate);
        call.enqueue(new Callback<login>() {
            @Override
            public void onResponse(Call<login> call, Response<login> response) {
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
              startActivity(new Intent(getApplicationContext(),UserHome.class));finish();

            }

            @Override
            public void onFailure(Call<login> call, Throwable t) {

            }
        });

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),TaxPay.class));
        finish();
    }
}
