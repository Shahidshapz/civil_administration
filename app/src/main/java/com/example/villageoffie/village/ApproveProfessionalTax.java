package com.example.villageoffie.village;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.villageoffie.R;
import com.example.villageoffie.pojo.Permitpojo;
import com.example.villageoffie.pojo.TradePojo;
import com.example.villageoffie.userpackage.PayTax;
import com.example.villageoffie.userpackage.PtaxAdapter;
import com.example.villageoffie.userpackage.TaxAdapter;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApproveProfessionalTax extends AppCompatActivity {
    RecyclerView recyclerView;
    List<TradePojo> applist;
    String userid;
    SharedPreferences sp;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_professional_tax);
        recyclerView =findViewById(R.id.recyclecerti);
        applist = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        final ApiInterface apiService =

                ApiClient.getClient().create(ApiInterface.class);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        userid = sp.getString("userid", "");
        Call<List<TradePojo>> call = apiService.Viewptax("viewptax","Professional");
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

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),VillageHome.class));
        finish();
    }
}
