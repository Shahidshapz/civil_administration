package com.example.villageoffie.village;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.villageoffie.R;
import com.example.villageoffie.pojo.viewuserapplication;

import java.util.List;

public class DeathAdapter extends RecyclerView.Adapter<DeathAdapter.AppViewHolder> {
        Context context;
        List<viewuserapplication> applist;

public DeathAdapter(Context context, List<viewuserapplication> applist) {
        this.context = context;
        this.applist = applist;
        }

@NonNull
@Override
public DeathAdapter.AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.viewapplications, parent, false);
        return new DeathAdapter.AppViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull DeathAdapter.AppViewHolder holder, int position) {
final viewuserapplication s = applist.get(position);
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
        ed.putString("did", s.getDid());
        ed.putString("name", s.getDname());
        ed.putString("sex", s.getSex());
        ed.putString("dob", s.getDob());
        ed.putString("dod", s.getDod());
        ed.putString("place", s.getPlace());
        ed.putString("dor", s.getDor());
        ed.putString("mname", s.getMname());
        ed.putString("fname", s.getFname());
        ed.putString("Address", s.getAddress());
        ed.putString("adate", s.getAdate());
        ed.putString("uname", s.getName());
        ed.putString("umobile", s.getMobile());
        ed.putString("uid", s.getUid());
        ed.putString("dtime", s.getDtime());
        ed.putString("dreason", s.getDreason());
        ed.commit();
        Intent i = new Intent(context, ViewDeathApp.class);
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
