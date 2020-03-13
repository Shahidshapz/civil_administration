package com.example.villageoffie.userpackage;

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
import com.example.villageoffie.pojo.Permitpojo;
import com.example.villageoffie.pojo.TradePojo;
import com.example.villageoffie.pojo.login;
import com.example.villageoffie.village.ApproveBuildingTax;
import com.example.villageoffie.village.ApproveProfessionalTax;
import com.example.villageoffie.village.ViewApplications;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PtaxAdapter extends RecyclerView.Adapter<PtaxAdapter.AppViewHolder> {
    Context context;
    List<TradePojo> applist;
String payid;
    public PtaxAdapter(Context context, List<TradePojo> applist) {
        this.context = context;
        this.applist = applist;
    }

    @NonNull
    @Override
    public PtaxAdapter.AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.customptax, parent, false);
        return new PtaxAdapter.AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PtaxAdapter.AppViewHolder holder, int position) {
        final TradePojo s = applist.get(position);
        final SharedPreferences sp = context.getSharedPreferences("login", Context.MODE_PRIVATE);

        if(sp.getString("utype","").equals("user")) {
            holder.sname.setText("Shop Name \t:\t" + s.getShopname());
            holder.purpose.setText("Purpose \t\t\t\t\t:\t" + s.getShoppurpose());
            holder.owner.setText("Shop owner \t:\t" + s.getShopowner());
            holder.bno.setText("Building No \t:\t" + s.getBno());
            holder.ward.setText("Ward  \t\t\t\t\t\t\t\t:\t" + s.getAddress());
            holder.address.setText("Address \t\t\t\t\t:\t" + s.getPdate());
        }
        else if(sp.getString("utype","").equals("Tax_approving_officer")) {
            holder.sname.setText("Shop Name \t:\t" + s.getShopname());
            holder.purpose.setText("Purpose \t\t\t\t\t:\t" + s.getShoppurpose());
            holder.owner.setText("Shop owner \t:\t" + s.getShopowner());
            holder.bno.setText("Building No \t:\t" + s.getBno());
            holder.ward.setText("Tax paid  \t\t\t\t\t\t\t\t:\t" + s.getAmount());
            holder.address.setText("Paid Date \t\t\t\t\t:\t" + s.getPdate());
        }
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sp.getString("utype","").equals("user")) {
                    AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getRootView().getContext());
                    alertbox.setMessage("Do you want to Pay tax for this Building");
                    alertbox.setTitle("warning");
                    alertbox.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences sp = context.getSharedPreferences("btax", Context.MODE_PRIVATE);
                            SharedPreferences.Editor ed = sp.edit();
                            ed.putString("uid", s.getUid());
                            ed.putString("tid", s.getTid());
                            ed.putString("sname", s.getShopname());
                            ed.putString("purpose", s.getShoppurpose());
                            ed.putString("bno", s.getBno());
                            ed.putString("address", s.getAddress());
                            ed.putString("taxtype", "Professional");
                            ed.commit();
                            Intent i = new Intent(context, TaxpayingView.class);
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
                else if(sp.getString("utype","").equals("Tax_approving_officer")) {
                    AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getRootView().getContext());
                    alertbox.setMessage("Do you want toApprove this tax??");
                    alertbox.setTitle("warning");
                    alertbox.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                    payid=s.getPayid();
                          Approve();

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
            }
        });


    }
    public void Approve() {
        ApiInterface apiinterface = ApiClient.getClient().create(ApiInterface.class);
        Call<login> call = apiinterface.approvetax("Approvetax",payid);
        call.enqueue(new Callback<login>() {
            @Override
            public void onResponse(Call<login> call, Response<login> response) {
                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, ApproveProfessionalTax.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(i);

            }

            @Override
            public void onFailure(Call<login> call, Throwable t) {

            }
        });

    }
    @Override
    public int getItemCount() {
        return applist.size();
    }

    public class AppViewHolder extends RecyclerView.ViewHolder {
        TextView sname, owner,purpose,bno,ward,address;
        CardView cv;

        public AppViewHolder(@NonNull View itemView) {

            super(itemView);
            sname = itemView.findViewById(R.id.oname);
            purpose = itemView.findViewById(R.id.nroom);
            owner= itemView.findViewById(R.id.sqft);
            address= itemView.findViewById(R.id.address);
            bno= itemView.findViewById(R.id.bno);
            ward= itemView.findViewById(R.id.ward);
            cv = itemView.findViewById(R.id.cv);
        }
    }
}
