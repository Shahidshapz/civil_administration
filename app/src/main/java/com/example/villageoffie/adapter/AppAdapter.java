package com.example.villageoffie.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.villageoffie.R;
import com.example.villageoffie.adminpackage.EditCertificate;
import com.example.villageoffie.adminpackage.ViewCertificates;
import com.example.villageoffie.pojo.reg;
import com.example.villageoffie.pojo.viewuserapplication;
import com.example.villageoffie.userpackage.Viewissued;
import com.example.villageoffie.village.ViewApplications;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppViewHolder> {
    Context context;
    List<viewuserapplication> applist;

    public AppAdapter(Context context, List<viewuserapplication> applist) {
        this.context = context;
        this.applist = applist;
    }

    @NonNull
    @Override
    public AppAdapter.AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.viewapplications, parent, false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppAdapter.AppViewHolder holder, int position) {
        final viewuserapplication s = applist.get(position);
        holder.name.setText("Applied For\t:\t" + s.getCName());
        if (s.getStatus().equals("0")) {
            holder.status.setText("Status    : Pending");
            SharedPreferences sp = context.getSharedPreferences("login", Context.MODE_PRIVATE);
            if (sp.getString("utype", "").equals("village")) {
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
                                ed.putString("cid", s.getCId());
                                ed.putString("uid", s.getUserId());
                                ed.commit();
                                Intent i = new Intent(context, ViewApplications.class);
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
        } else if (s.getStatus().equals("1")) {
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
                                ed.putString("cid", s.getCId());
                                ed.putString("uid", s.getUserId());
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
