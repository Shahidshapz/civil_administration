package com.example.villageoffie.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.villageoffie.R;
import com.example.villageoffie.pojo.viewuserapplication;
import com.example.villageoffie.userpackage.Viewissued;
import com.example.villageoffie.village.ViewApplications;
import com.example.villageoffie.village.ViewtradeApplications;

import java.util.List;

public class TradeAdapter extends RecyclerView.Adapter<TradeAdapter.AppViewHolder> {
    Context context;
    List<viewuserapplication> applist;

    public TradeAdapter(Context context, List<viewuserapplication> applist) {
        this.context = context;
        this.applist = applist;
    }

    @NonNull
    @Override
    public TradeAdapter.AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.viewapplications, parent, false);
        return new TradeAdapter.AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TradeAdapter.AppViewHolder holder, int position) {
        final viewuserapplication s = applist.get(position);
        holder.name.setText("Applicant Name\t:\t" + s.getName());
        if (s.getStatus().equals("0")) {
            holder.status.setText("Status    : Pending");
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
                            ed.putString("tid", s.getTid());
                            ed.putString("shopname", s.getShopname());
                            ed.putString("oname", s.getShopowner());
                            ed.putString("purpose", s.getShoppurpose());
                            ed.putString("ward", s.getWard());
                            ed.putString("bno", s.getBno());
                            ed.putString("address", s.getAddress());
                            ed.putString("adate", s.getDor());
                            ed.putString("name", s.getName());
                            ed.putString("umobile", s.getMobile());
                            ed.putString("pic", s.getImage());
                            ed.putString("uid", s.getUid());
                            ed.commit();
                            Intent i = new Intent(context, ViewtradeApplications.class);
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
        else if (s.getStatus().equals("1")) {
            holder.status.setText("Status    : Approved");
            SharedPreferences sp = context.getSharedPreferences("login", Context.MODE_PRIVATE);
            if (sp.getString("utype", "").equals("user")) {
                holder.cv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getRootView().getContext());
                        alertbox.setMessage("Do you want to view your e-Certificate??");
                        alertbox.setTitle("warning");
                        alertbox.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences sp = context.getSharedPreferences("viewApp", Context.MODE_PRIVATE);
                                SharedPreferences.Editor ed = sp.edit();
//                                ed.putString("cid", s.getCId());
//                                ed.putString("uid", s.getUserId());
                                ed.commit();
                                Intent i = new Intent(context, Viewissued.class);
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
        } else if (s.getStatus().equals("2")) {
            holder.status.setText("Status    : Rejected");
        }
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
