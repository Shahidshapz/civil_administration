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
import com.example.villageoffie.adminpackage.EditVillage;
import com.example.villageoffie.adminpackage.ViewVillages;
import com.example.villageoffie.pojo.reg;
import com.example.villageoffie.pojo.viewvillage;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VillageAdapter extends RecyclerView.Adapter<VillageAdapter.VillageViewHolder> {

    List<viewvillage>villagelist;
    Context context;
String villageid;
    public VillageAdapter(Context applicationContext, List<viewvillage> villagelist) {
        this.context=applicationContext;
        this.villagelist=villagelist;


    }

    @NonNull
    @Override
    public VillageAdapter.VillageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.viewvillage, parent,false);
        return new VillageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VillageAdapter.VillageViewHolder holder, int position) {
final viewvillage s=villagelist.get(position);
holder.vname.setText("Officer Name\t\t:\t"+s.getVName());
holder.vtaluk.setText("Designation\t\t\t:\t"+s.getVTaluk());
holder.vdistrict.setText("District\t\t\t\t\t\t\t:\t"+s.getVDistrict());
holder.vstate.setText("State\t\t\t\t\t\t\t\t:\t"+s.getVState());
holder.vplace.setText("Place\t\t\t\t\t\t\t\t:\t"+s.getVPlace());
holder.vpin.setText("Pin Number\t\t\t:\t"+s.getVPin());
holder.vmob.setText("Mobile\t\t\t\t\t\t\t:\t"+s.getVMobile());
holder.cardView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getRootView().getContext());
        alertbox.setMessage("Do you want to modify Village details");
        alertbox.setTitle("warning");
        alertbox.setPositiveButton("delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                villageid = s.getVId();
                deletecertificate();

            }

            private void deletecertificate() {
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<reg> call = apiService.villagedelete("deletevillage", villageid);
                call.enqueue(new Callback<reg>() {
                    @Override
                    public void onResponse(Call<reg> call, Response<reg> response) {
                        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(context, ViewVillages.class);
                        context.startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<reg> call, Throwable t) {
                        Toast.makeText(context, t+"", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


//      alertbox.setNegativeButton("edit", new DialogInterface.OnClickListener() {
//        @Override
//        public void onClick(DialogInterface dialog, int which) {
//            SharedPreferences sp= context.getSharedPreferences("village", context.MODE_PRIVATE);
//            SharedPreferences.Editor ed = sp.edit();
//            ed.putString("vname", s.getVName());
//            ed.putString("vtaluk", s.getVTaluk());
//            ed.putString("vdistrict", s.getVDistrict());
//            ed.putString("vstate", s.getVState());
//            ed.putString("vplace", s.getVPlace());
//            ed.putString("vpin", s.getVPin());
//            ed.putString("vmob",s.getVMobile());
//            ed.putString("vid",s.getVId());
//            ed.commit();
//            Intent i = new Intent(context, EditVillage.class);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            context.startActivity(i);
//
//
//        }
//    });
    //   alertbox.show();
}
    });

}

    @Override
    public int getItemCount() {
        return villagelist.size();
    }

    public class VillageViewHolder extends RecyclerView.ViewHolder {
TextView vname,vtaluk,vdistrict,vstate,vplace,vpin,vmob;
CardView cardView;
        public VillageViewHolder(@NonNull View itemView) {
            super(itemView);
            vname=itemView.findViewById(R.id.vnameview);
            vtaluk=itemView.findViewById(R.id.vtalukview);
            vdistrict=itemView.findViewById(R.id.vdistrictview);
            vstate=itemView.findViewById(R.id.vstateview);
            vplace=itemView.findViewById(R.id.vplaceview);
            vpin=itemView.findViewById(R.id.vpinview);
            vmob=itemView.findViewById(R.id.vmobview);
            cardView=itemView.findViewById(R.id.cardvillage);



        }
    }
}
