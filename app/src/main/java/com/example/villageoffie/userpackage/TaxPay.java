package com.example.villageoffie.userpackage;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class TaxPay extends Fragment {
    RecyclerView recyclerView;
    List<viewuserapplication> applist;
    String userid;
    SharedPreferences sp;
    String key;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tax_pay, container, false);
        recyclerView = v.findViewById(R.id.viewAppli);
        applist = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        final ApiInterface apiService =

                ApiClient.getClient().create(ApiInterface.class);
        SharedPreferences sp = getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        userid = sp.getString("userid", "");
        Call<List<viewuserapplication>> call = apiService.appview("villageview");
        call.enqueue(new Callback<List<viewuserapplication>>() {
            @Override
            public void onResponse(Call<List<viewuserapplication>> call, Response<List<viewuserapplication>> response) {
                applist = response.body();
                AppAdapter AA = new AppAdapter(getContext(), applist);
                recyclerView.setAdapter(AA);
            }

            @Override
            public void onFailure(Call<List<viewuserapplication>> call, Throwable t) {

            }
        });


        return v;
    }
}