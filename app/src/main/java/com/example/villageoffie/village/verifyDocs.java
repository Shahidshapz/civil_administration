package com.example.villageoffie.village;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.villageoffie.Others.Login;
import com.example.villageoffie.R;
import com.example.villageoffie.adapter.DocumentAdapter;
import com.example.villageoffie.pojo.viewdocument;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class verifyDocs extends AppCompatActivity {
String uid;
RecyclerView recyclerView;
    List<viewdocument>doclist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_docs);
        SharedPreferences sp = getSharedPreferences("verify", Context.MODE_PRIVATE);
uid=sp.getString("uid","");
        recyclerView=findViewById(R.id.verifyrecycle);
        doclist=new ArrayList<>();
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ApiInterface apiService =

                ApiClient.getClient().create(ApiInterface.class);
        Call<List<viewdocument>> call=apiService.docs("view_documents",uid);
        call.enqueue(new Callback<List<viewdocument>>() {
            @Override
            public void onResponse(Call<List<viewdocument>> call, Response<List<viewdocument>> response) {
                doclist=response.body();
                DocumentAdapter DA =new DocumentAdapter(getApplicationContext(),doclist);
                recyclerView.setAdapter(DA);
            }

            @Override
            public void onFailure(Call<List<viewdocument>> call, Throwable t) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), ViewApplications.class));
        finish();
    }
}
