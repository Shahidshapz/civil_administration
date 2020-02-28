package com.example.villageoffie.userpackage;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.villageoffie.R;
import com.example.villageoffie.adapter.UserCertificateView;
import com.example.villageoffie.pojo.viewcertificate;
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
public class AvailableCertificate extends Fragment {
    RecyclerView recyclerView;
    List<viewcertificate>certificatelist;
    public AvailableCertificate() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_available_certificate, container, false);
        recyclerView=view.findViewById(R.id.recyclecerti_userview);
        certificatelist=new ArrayList<>();
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        ApiInterface apiService =

                ApiClient.getClient().create(ApiInterface.class);
        Call<List<viewcertificate>> call=apiService.certificateview("view_certificate");
        call.enqueue(new Callback<List<viewcertificate>>() {
            @Override
            public void onResponse(Call<List<viewcertificate>> call, Response<List<viewcertificate>> response) {
                certificatelist=response.body();
                UserCertificateView UA=new UserCertificateView(getActivity(),certificatelist);
                recyclerView.setAdapter(UA);

            }

            @Override
            public void onFailure(Call<List<viewcertificate>> call, Throwable t) {

            }
        });

        return view;
    }

}
