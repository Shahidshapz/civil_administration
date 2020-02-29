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
import com.example.villageoffie.adapter.DocumentAdapter;
import com.example.villageoffie.pojo.viewdocument;
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
public class ViewMyDocs extends Fragment {
RecyclerView recyclerView;
List<viewdocument>doclist;
String userid;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_view_my_docs, container, false);
        recyclerView=view.findViewById(R.id.recycledocs);
        doclist=new ArrayList<>();
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        SharedPreferences sp =getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        userid = sp.getString("userid", "");
        ApiInterface apiService =

                ApiClient.getClient().create(ApiInterface.class);
        Call<List<viewdocument>> call=apiService.docs("view_documents",userid);
        call.enqueue(new Callback<List<viewdocument>>() {
            @Override
            public void onResponse(Call<List<viewdocument>> call, Response<List<viewdocument>> response) {
                doclist=response.body();
                DocumentAdapter DA =new DocumentAdapter(getContext(),doclist);
                recyclerView.setAdapter(DA);
            }

            @Override
            public void onFailure(Call<List<viewdocument>> call, Throwable t) {

            }
        });
        return  view;
    }

}
