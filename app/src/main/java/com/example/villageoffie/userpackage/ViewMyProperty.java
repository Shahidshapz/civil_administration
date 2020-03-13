package com.example.villageoffie.userpackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.villageoffie.R;
import com.example.villageoffie.adapter.AppAdapter;
import com.example.villageoffie.pojo.Permitpojo;
import com.example.villageoffie.pojo.viewuserapplication;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewMyProperty extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Permitpojo> applist;
    String userid;
    SharedPreferences sp;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_property);
        recyclerView =findViewById(R.id.viewAppli);
        applist = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        final ApiInterface apiService =

                ApiClient.getClient().create(ApiInterface.class);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        userid = sp.getString("userid", "");
        Call<List<Permitpojo>> call = apiService.ViewMyBuild("payBtax",userid);
        call.enqueue(new Callback<List<Permitpojo>>() {
            @Override
            public void onResponse(Call<List<Permitpojo>> call, Response<List<Permitpojo>> response) {
                Log.d("@@",response.body()+"");
                applist = response.body();
                TaxAdapter AA = new TaxAdapter(getApplicationContext(), applist);
                recyclerView.setAdapter(AA);
            }

            @Override
            public void onFailure(Call<List<Permitpojo>> call, Throwable t) {

            }
        });

    }
}
