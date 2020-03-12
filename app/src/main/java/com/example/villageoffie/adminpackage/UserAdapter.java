package com.example.villageoffie.adminpackage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.villageoffie.R;
import com.example.villageoffie.adapter.VillageAdapter;
import com.example.villageoffie.pojo.reg;
import com.example.villageoffie.pojo.viewvillage;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import java.io.ByteArrayOutputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.VillageViewHolder> {

    List<viewvillage> villagelist;
    Context context;
    String villageid;
    public UserAdapter(Context applicationContext, List<viewvillage> villagelist) {
        this.context=applicationContext;
        this.villagelist=villagelist;


    }

    @NonNull
    @Override
    public UserAdapter.VillageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.viewvillage, parent,false);
        return new UserAdapter.VillageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.VillageViewHolder holder, int position) {
        final viewvillage s=villagelist.get(position);
        holder.vname.setText("User Name\t\t:\t"+s.getName());
        holder.vtaluk.setText("Age\t\t\t\t\t\t\t\t:\t"+s.getAge());
        holder.vdistrict.setText("Address\t\t\t\t\t\t\t:\t"+s.getAddress());
        holder.vstate.setText("Mobile\t\t\t\t\t\t\t\t:\t"+s.getMobile());
        holder.vplace.setText("Father's name\t\t\t\t\t\t\t\t:\t"+s.getFname());
        holder.vpin.setText("Mother's name\t\t\t:\t"+s.getMname());
        holder.vmob.setText("Job\t\t\t\t\t\t\t:\t"+s.getJob());

//    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//    byte[] imageBytes = baos.toByteArray();
//    imageBytes = Base64.decode(s.getImage(), Base64.DEFAULT);
//    Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//    holder.upic.setImageBitmap(decodedImage);

//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getRootView().getContext());
//                alertbox.setMessage("Do you want to modify Village details");
//                alertbox.setTitle("warning");
//                alertbox.setPositiveButton("delete", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        villageid = s.getVId();
//                        deletecertificate();
//
//                    }
//
//                    private void deletecertificate() {
//                        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
//                        Call<reg> call = apiService.villagedelete("deletevillage", villageid);
//                        call.enqueue(new Callback<reg>() {
//                            @Override
//                            public void onResponse(Call<reg> call, Response<reg> response) {
//                                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
//                                Intent i=new Intent(context, ViewVillages.class);
//                                context.startActivity(i);
//                            }
//
//                            @Override
//                            public void onFailure(Call<reg> call, Throwable t) {
//                                Toast.makeText(context, t+"", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                });
//
//
//                alertbox.setNegativeButton("edit", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        SharedPreferences sp= context.getSharedPreferences("village", context.MODE_PRIVATE);
//                        SharedPreferences.Editor ed = sp.edit();
//                        ed.putString("vname", s.getVName());
//                        ed.putString("vtaluk", s.getVTaluk());
//                        ed.putString("vdistrict", s.getVDistrict());
//                        ed.putString("vstate", s.getVState());
//                        ed.putString("vplace", s.getVPlace());
//                        ed.putString("vpin", s.getVPin());
//                        ed.putString("vmob",s.getVMobile());
//                        ed.putString("vid",s.getVId());
//                        ed.commit();
//                        Intent i = new Intent(context, EditVillage.class);
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
        return villagelist.size();
    }

    public class VillageViewHolder extends RecyclerView.ViewHolder {
        TextView vname,vtaluk,vdistrict,vstate,vplace,vpin,vmob;
        CardView cardView;
        ImageView upic;
        public VillageViewHolder(@NonNull View itemView) {
            super(itemView);
            vname=itemView.findViewById(R.id.vnameview);
            vtaluk=itemView.findViewById(R.id.vtalukview);
            vdistrict=itemView.findViewById(R.id.vdistrictview);
            vstate=itemView.findViewById(R.id.vstateview);
            vplace=itemView.findViewById(R.id.vplaceview);
            upic=itemView.findViewById(R.id.upic);
            vpin=itemView.findViewById(R.id.vpinview);
            vmob=itemView.findViewById(R.id.vmobview);
            cardView=itemView.findViewById(R.id.cardvillage);



        }
    }
}
