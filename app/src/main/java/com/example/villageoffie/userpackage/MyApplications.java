package com.example.villageoffie.userpackage;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.villageoffie.R;
import com.example.villageoffie.adapter.AppAdapter;
import com.example.villageoffie.pojo.viewcertificate;
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
public class MyApplications extends Fragment {
TextView birth,death,mrg;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_applications, container, false);
        birth=view.findViewById(R.id.birth);
        death=view.findViewById(R.id.Death);
        mrg=view.findViewById(R.id.Marrige);
     birth.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

         }
     });
        death.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return  view;
    }

}
