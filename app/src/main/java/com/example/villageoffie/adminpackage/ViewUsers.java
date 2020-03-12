package com.example.villageoffie.adminpackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.villageoffie.R;
import com.example.villageoffie.adapter.VillageAdapter;
import com.example.villageoffie.pojo.viewvillage;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewUsers extends AppCompatActivity {
    RecyclerView recyclerView;
    List<viewvillage> villagelist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);
        recyclerView=findViewById(R.id.recyclevillage);
        recyclerView=findViewById(R.id.recyclevillage);
        villagelist=new ArrayList<>();
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        ApiInterface apiService =

                ApiClient.getClient().create(ApiInterface.class);
        Call<List<viewvillage>> call=apiService.villageview("view_users");
        call.enqueue(new Callback<List<viewvillage>>() {
            @Override
            public void onResponse(Call<List<viewvillage>> call, Response<List<viewvillage>> response) {
                villagelist=response.body();
                UserAdapter VA=new UserAdapter(getApplicationContext(),villagelist);
                recyclerView.setAdapter(VA);
            }

            @Override
            public void onFailure(Call<List<viewvillage>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(), Admin.class);
        startActivity(i);

    }
}

