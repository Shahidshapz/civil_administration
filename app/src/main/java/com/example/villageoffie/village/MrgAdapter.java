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

public class MrgAdapter extends RecyclerView.Adapter<MrgAdapter.AppViewHolder> {
    Context context;
    List<viewuserapplication> applist;

    public MrgAdapter(Context context, List<viewuserapplication> applist) {
        this.context = context;
        this.applist = applist;
    }

    @NonNull
    @Override
    public MrgAdapter.AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.viewapplications, parent, false);
        return new MrgAdapter.AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MrgAdapter.AppViewHolder holder, int position) {
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
                        ed.putString("mid", s.getMid());
                        ed.putString("hname", s.getHname());
                        ed.putString("hsex", s.getHsex());
                        ed.putString("hdob", s.getHdob());
                        ed.putString("dom", s.getDom());
                        ed.putString("place", s.getPlace());
                        ed.putString("dor", s.getDor());
                        ed.putString("hmname", s.getHmname());
                        ed.putString("hfname", s.getHfname());
                        ed.putString("hnation", s.getHnationality());
                        ed.putString("hjob", s.getHjob());
                        ed.putString("haddress", s.getHaddress());

                        ed.putString("wname", s.getWname());
                        ed.putString("wsex", s.getWsex());
                        ed.putString("wdob", s.getWdob());
                        ed.putString("wmname", s.getWmname());
                        ed.putString("wfname", s.getWfname());
                        ed.putString("wnation", s.getWnationality());
                        ed.putString("wjob", s.getWjob());
                        ed.putString("waddress", s.getWaddress());
                        ed.putString("hpic", s.getHpic());
                        ed.putString("wpic", s.getWpic());
                        ed.commit();
                        Intent i = new Intent(context, ViewMrgApp.class);
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
