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
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.villageoffie.R;
import com.example.villageoffie.pojo.viewcertificate;
import com.example.villageoffie.userpackage.AvailableCertificate;
import com.example.villageoffie.userpackage.ViewUser;

import java.net.UnknownServiceException;
import java.util.List;

public class UserCertificateView extends RecyclerView.Adapter<UserCertificateView.ViewHolder> {
    Context context;
    List<viewcertificate> certificatelist;
    String certificateid;

    public UserCertificateView(FragmentActivity activity, List<viewcertificate> certificatelist) {
        this.context = activity;
        this.certificatelist = certificatelist;

    }

    @NonNull
    @Override
    public UserCertificateView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.viewcertificate, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserCertificateView.ViewHolder holder, int position) {
        final viewcertificate s = certificatelist.get(position);

        holder.cname.setText("Certificate Name\t\t:\t" + s.getCName());
        holder.req.setText("Requirments\t\t\t\t\t:\t" + s.getCReq());
        holder.fee.setText("Govt.fee\t\t\t\t\t\t\t\t\t:\t" + s.getCFee());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getRootView().getContext());
                alertbox.setMessage("Do you want to apply for Certificate ");
                alertbox.setTitle("warning");
                alertbox.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });
                alertbox.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sp = context.getSharedPreferences("certificate", Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed = sp.edit();
                        ed.putString("cid", s.getCId());
                        ed.putString("cname", s.getCName());
                        ed.putString("amount", s.getCFee());
                        ed.commit();
                        Intent i = new Intent(context, ViewUser.class);
                        context.startActivity(i);
                    }
                });
                alertbox.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return certificatelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cname, req, fee;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cname = itemView.findViewById(R.id.cnameview);
            req = itemView.findViewById(R.id.creqview);
            fee = itemView.findViewById(R.id.cfeeview);
            cardView = itemView.findViewById(R.id.cardcertificate);
        }
    }
}
