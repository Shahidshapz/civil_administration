package com.example.villageoffie.adminpackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.villageoffie.Others.Login;
import com.example.villageoffie.R;
import com.example.villageoffie.pojo.reg;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditCertificate extends AppCompatActivity {
EditText cname,creq,cfee;
Button Edit;
SharedPreferences sp;
String cid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_certificate);
        cname=findViewById(R.id.cnameedit);
        creq=findViewById(R.id.creqedit);
        cfee=findViewById(R.id.cfeeedit);
        Edit=findViewById(R.id.edit);
        sp=getSharedPreferences("edit",getApplicationContext().MODE_PRIVATE);
        cname.setText(sp.getString("cname",""));
        creq.setText(sp.getString("creq",""));
        cfee.setText(sp.getString("cfee",""));
cid=sp.getString("cid","");
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<reg> call = apiInterface.certficateedit("editcertificate",cid,cname.getText().toString(),creq.getText().toString(),cfee.getText().toString());
            call.enqueue(new Callback<reg>() {
                @Override
                public void onResponse(Call<reg> call, Response<reg> response) {
                    Toast.makeText(EditCertificate.this, "edited", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(), ViewCertificates.class);
                    startActivity(i);
                }

                @Override
                public void onFailure(Call<reg> call, Throwable t) {
                    Toast.makeText(EditCertificate.this, t+"", Toast.LENGTH_SHORT).show();
                }
            });

            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(), ViewCertificates.class);
        startActivity(i);
    }
}
