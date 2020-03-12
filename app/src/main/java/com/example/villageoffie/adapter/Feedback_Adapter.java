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
import com.example.villageoffie.pojo.viewcertificate;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Feedback_Adapter extends RecyclerView.Adapter<Feedback_Adapter.CertificateViewHolder> {
    Context context;
    List<viewcertificate> certificatelist;
    String certificateid;

    public Feedback_Adapter(Context applicationContext, List<viewcertificate> certificatelist) {
        this.context = applicationContext;
        this.certificatelist = certificatelist;
    }

    @NonNull
    @Override
    public Feedback_Adapter.CertificateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_feed, parent, false);
        return new Feedback_Adapter.CertificateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Feedback_Adapter.CertificateViewHolder holder, int position) {
        final viewcertificate s = certificatelist.get(position);
        holder.cname.setText("feedback from\t\t\t:\t" + s.getFrom());
        holder.req.setText("Feedback\t\t\t\t\t\t:\t" + s.getFeedback());
        holder.fee.setText("Received date\t\t\t\t\t\t\t\t\t\t:\t" + s.getDate());
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getRootView().getContext());
//                alertbox.setMessage("Do you want to modify Certificate details");
//                alertbox.setTitle("warning");
//                alertbox.setPositiveButton("delete", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        certificateid = s.getCId();
//                        deletecertificate();
//
//                    }
//
//                    private void deletecertificate() {
//                        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
//                        Call<reg> call = apiService.certificatedelete("deletecertificate", certificateid);
//                        call.enqueue(new Callback<reg>() {
//                            @Override
//                            public void onResponse(Call<reg> call, Response<reg> response) {
//                                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
//                                Intent i = new Intent(context, ViewCertificates.class);
//                                context.startActivity(i);
//                            }
//
//                            @Override
//                            public void onFailure(Call<reg> call, Throwable t) {
//                                Toast.makeText(context, t + "", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                });
//                alertbox.setNegativeButton("edit", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        SharedPreferences sp = context.getSharedPreferences("edit", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor ed = sp.edit();
//                        ed.putString("cname", s.getCName());
//                        ed.putString("creq", s.getCReq());
//                        ed.putString("cfee", s.getCFee());
//                        ed.putString("cid", s.getCId());
//                        ed.commit();
//                        Intent i = new Intent(context, EditCertificate.class);
//                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        context.startActivity(i);
//
//
//                    }
//                });
//                alertbox.show();
//            }
//        });

    }


    @Override
    public int getItemCount() {
        return certificatelist.size();
    }

    public class CertificateViewHolder extends RecyclerView.ViewHolder {
        TextView cname, req, fee;
        CardView cardView;

        public CertificateViewHolder(@NonNull View itemView) {
            super(itemView);
            cname = itemView.findViewById(R.id.cnameview);
            req = itemView.findViewById(R.id.creqview);
            fee = itemView.findViewById(R.id.cfeeview);
            cardView = itemView.findViewById(R.id.cardcertificate);
        }
    }
}
