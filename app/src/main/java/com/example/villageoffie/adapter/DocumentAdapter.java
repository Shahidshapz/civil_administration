package com.example.villageoffie.adapter;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.villageoffie.R;
import com.example.villageoffie.adminpackage.EditCertificate;
import com.example.villageoffie.adminpackage.ViewCertificates;
import com.example.villageoffie.pojo.reg;
import com.example.villageoffie.pojo.viewcertificate;
import com.example.villageoffie.pojo.viewdocument;
import com.example.villageoffie.userpackage.UserHome;
import com.example.villageoffie.userpackage.ViewMyDocs;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import java.io.ByteArrayOutputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.ViewHolder> {
    Context context;
    List<viewdocument> certificatelist;
    String certificateid;

    public DocumentAdapter(Context context, List<viewdocument> doclist) {
        this.context = context;
        this.certificatelist = doclist;
    }

    @NonNull
    @Override
    public DocumentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_docs, parent, false);
        return new DocumentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentAdapter.ViewHolder holder, int position) {
        final viewdocument v = certificatelist.get(position);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageBytes = baos.toByteArray();
        imageBytes = Base64.decode(v.getDocument(), Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        holder.doc.setImageBitmap(decodedImage);
        holder.docname.setText(v.getDocName());
        SharedPreferences sp = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        if (sp.getString("utype", "").equals("user")) {

            holder.touch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {

                    AlertDialog.Builder alertbox = new AlertDialog.Builder(v1.getRootView().getContext());
                    alertbox.setMessage("Do you want to Delete this document");
                    alertbox.setTitle("warning");
                    alertbox.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            certificateid=v.getDocId();
                            deletecertificate();

                        }

                        private void deletecertificate() {
                            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                            Call<reg> call = apiService.certificatedelete("deletedocument", certificateid);
                            call.enqueue(new Callback<reg>() {
                                @Override
                                public void onResponse(Call<reg> call, Response<reg> response) {
                                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(context, UserHome.class);
                                    context.startActivity(i);
                                }

                                @Override
                                public void onFailure(Call<reg> call, Throwable t) {
                                    Toast.makeText(context, t + "", Toast.LENGTH_SHORT).show();
                                }
                            });
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
    }

    @Override
    public int getItemCount() {
        return certificatelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView doc;
        TextView docname;
        LinearLayout touch;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doc = itemView.findViewById(R.id.imageView4);
            docname = itemView.findViewById(R.id.textView5);
            touch = itemView.findViewById(R.id.doctouch);
        }
    }
}
