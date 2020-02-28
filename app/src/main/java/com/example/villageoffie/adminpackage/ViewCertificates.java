package com.example.villageoffie.adminpackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.villageoffie.Others.Login;
import com.example.villageoffie.R;
import com.example.villageoffie.adapter.CertificateAdapter;
import com.example.villageoffie.pojo.viewcertificate;
import com.example.villageoffie.pojo.viewvillage;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCertificates extends AppCompatActivity {
    RecyclerView recyclerView;
    List<viewcertificate>certificatelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_certificates);
        recyclerView=findViewById(R.id.recyclecerti);

        certificatelist=new ArrayList<>();
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        ApiInterface apiService =

                ApiClient.getClient().create(ApiInterface.class);
        Call<List<viewcertificate>> call=apiService.certificateview("view_certificate");
        call.enqueue(new Callback<List<viewcertificate>>() {
            @Override
            public void onResponse(Call<List<viewcertificate>> call, Response<List<viewcertificate>> response) {
                certificatelist=response.body();
                CertificateAdapter CA=new CertificateAdapter(getApplicationContext(),certificatelist);
                recyclerView.setAdapter(CA);
            }

            @Override
            public void onFailure(Call<List<viewcertificate>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i= new Intent(getApplicationContext(),Admin.class);
        startActivity(i);
    }
}
