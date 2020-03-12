package com.example.villageoffie.village;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.villageoffie.Others.Login;
import com.example.villageoffie.R;
import com.example.villageoffie.adapter.AppAdapter;
import com.example.villageoffie.pojo.viewuserapplication;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Appliedlist extends AppCompatActivity {
    RecyclerView recyclerView;
    List<viewuserapplication> applist;
    String userid;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliedlist);
        recyclerView=findViewById(R.id.viewAppli);
        applist=new ArrayList<>();
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final ApiInterface apiService =

                ApiClient.getClient().create(ApiInterface.class);
        SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
        userid = sp.getString("userid", "");
        Call<List<viewuserapplication>> call=apiService.appview("villageview");
        call.enqueue(new Callback<List<viewuserapplication>>() {
            @Override
            public void onResponse(Call<List<viewuserapplication>> call, Response<List<viewuserapplication>> response) {
                applist=response.body();
                AppAdapter AA=new AppAdapter(getApplicationContext(),applist);
                recyclerView.setAdapter(AA);
            }

            @Override
            public void onFailure(Call<List<viewuserapplication>> call, Throwable t) {

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), VillageHome.class));finish();
    }

}
