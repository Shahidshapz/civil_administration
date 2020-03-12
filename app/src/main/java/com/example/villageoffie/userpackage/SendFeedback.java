package com.example.villageoffie.userpackage;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.villageoffie.R;
import com.example.villageoffie.pojo.login;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SendFeedback extends Fragment {
    EditText rname,feed;
    Button rsend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_blank, container, false);
        SharedPreferences sp = getContext().getSharedPreferences("login", Context.MODE_PRIVATE);

        rname=v.findViewById(R.id.rfname);
        rname.setEnabled(false);
        feed=v.findViewById(R.id.rfeedback);
        rsend=v.findViewById(R.id.rfreg);
        rname.setText(sp.getString("uname", ""));

        rsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(feed.getText().toString().isEmpty()){
                    feed.setError("Enter any reviews");
                }
                else {
                    startSendFeedback();
                }
            }
        });
        return v;
    }

    private void startSendFeedback() {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c);
        ApiInterface apiinterface = ApiClient.getClient().create(ApiInterface.class);
        Call<login> call = apiinterface.getfeedback("sendFeedback", rname.getText().toString(), feed.getText().toString(),formattedDate);
        call.enqueue(new Callback<login>() {
            @Override
            public void onResponse(Call<login> call, Response<login> response) {
                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                feed.setText("");

            }

            @Override
            public void onFailure(Call<login> call, Throwable t) {

            }
        });

    }

}