package com.example.villageoffie.userpackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.villageoffie.R;
import com.example.villageoffie.pojo.Permitpojo;
import com.example.villageoffie.pojo.TradePojo;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewMyShop extends AppCompatActivity {
    RecyclerView recyclerView;
    List<TradePojo> applist;
    String userid;
    SharedPreferences sp;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_shop);
        recyclerView =findViewById(R.id.viewAppli);
        applist = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        final ApiInterface apiService =

                ApiClient.getClient().create(ApiInterface.class);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        userid = sp.getString("userid", "");
        Call<List<TradePojo>> call = apiService.gettrade("payptax",userid);
        call.enqueue(new Callback<List<TradePojo>>() {
            @Override
            public void onResponse(Call<List<TradePojo>> call, Response<List<TradePojo>> response) {
                Log.d("@@",response.body()+"");
                applist = response.body();
                PtaxAdapter AA = new PtaxAdapter(getApplicationContext(), applist);
                recyclerView.setAdapter(AA);
            }

            @Override
            public void onFailure(Call<List<TradePojo>> call, Throwable t) {

            }
        });

    }
}
