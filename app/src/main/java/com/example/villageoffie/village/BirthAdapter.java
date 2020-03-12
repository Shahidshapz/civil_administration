package com.example.villageoffie.village;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class BirthAdapter extends RecyclerView.Adapter<BirthAdapter.AppViewHolder> {
    Context context;
    List<viewuserapplication> applist;

    public BirthAdapter(Context context, List<viewuserapplication> applist) {
        this.context = context;
        this.applist = applist;
    }

    @NonNull
    @Override
    public BirthAdapter.AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.viewapplications, parent, false);
        return new BirthAdapter.AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BirthAdapter.AppViewHolder holder, int position) {
        final viewuserapplication s = applist.get(position);
        Toast.makeText(context, s.getName(), Toast.LENGTH_SHORT).show();
        holder.name.setText("Applicant Name\t:\t" + s.getName());
        if (s.getStatus().equals("0")) {
            holder.status.setText("Status    : Pending");
        }
        else if (s.getStatus().equals("1")) {
            holder.status.setText("Status    : Approved");

        } else if (s.getStatus().equals("2")) {
            holder.status.setText("Status    : Rejected");
        }

            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getRootView().getContext());
                    alertbox.setMessage("Do you want to view this Application");
                    alertbox.setTitle("warning");
                    alertbox.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences sp = context.getSharedPreferences("viewApp", Context.MODE_PRIVATE);
                            SharedPreferences.Editor ed = sp.edit();
                            ed.putString("bid", s.getBid());
                            ed.putString("name", s.getBname());
                            ed.putString("sex", s.getSex());
                            ed.putString("dob", s.getDob());
                            ed.putString("btime", s.getBtime());
                            ed.putString("place", s.getPlace());
                            ed.putString("dor", s.getDor());
                            ed.putString("mname", s.getMname());
                            ed.putString("fname", s.getFname());
                            ed.putString("Address", s.getAddress());
                            ed.putString("adate", s.getAdate());
                            ed.putString("uname", s.getName());
                            ed.putString("umobile", s.getMobile());
                            ed.putString("uid", s.getUid());
                            ed.commit();
                            Intent i = new Intent(context, ViewBirthApp.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(i);

                        }


                    });
                    alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();

                        }
                    });
                    alertbox.show();
                }
            });


    }

    @Override
    public int getItemCount() {
        return applist.size();
    }

    public class AppViewHolder extends RecyclerView.ViewHolder {
        TextView name, status;
        CardView cv;

        public AppViewHolder(@NonNull View itemView) {

            super(itemView);
            name = itemView.findViewById(R.id.textView3);
            status = itemView.findViewById(R.id.textView4);
            cv = itemView.findViewById(R.id.cv);
        }
    }
}
