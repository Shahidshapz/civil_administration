package com.example.villageoffie.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.villageoffie.R;
import com.example.villageoffie.pojo.viewcertificate;
import com.example.villageoffie.pojo.viewdocument;

import java.io.ByteArrayOutputStream;
import java.util.List;

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
        viewdocument v=certificatelist.get(position);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageBytes = baos.toByteArray();
        imageBytes = Base64.decode(v.getDocument(), Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        holder.doc.setImageBitmap(decodedImage);
        holder.docname.setText(v.getDocName());
    }

    @Override
    public int getItemCount() {
        return certificatelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView doc;
        TextView docname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doc=itemView.findViewById(R.id.imageView4);
            docname=itemView.findViewById(R.id.textView5);
        }
    }
}
