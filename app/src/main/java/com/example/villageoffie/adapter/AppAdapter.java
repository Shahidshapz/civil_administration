package com.example.villageoffie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.villageoffie.R;
import com.example.villageoffie.pojo.viewuserapplication;

import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppViewHolder> {
    Context context;
    List<viewuserapplication>applist;

    public AppAdapter(Context context, List<viewuserapplication> applist) {
        this.context=context;
        this.applist=applist;
    }

    @NonNull
    @Override
    public AppAdapter.AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.viewapplications, parent,false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppAdapter.AppViewHolder holder, int position) {
final viewuserapplication s=applist.get(position);
holder.name.setText("Certificate\t:\t"+s.getCName());
if(s.getStatus().equals("0")){
    holder.status.setText("Status    : Pending");

}else if(s.getStatus().equals("1")){
    holder.status.setText("Status    : Approved");
}
    }

    @Override
    public int getItemCount() {
        return applist.size();
    }

    public class AppViewHolder extends RecyclerView.ViewHolder {
        TextView name,status;
        public AppViewHolder(@NonNull View itemView) {

            super(itemView);
            name=itemView.findViewById(R.id.textView3);
            status=itemView.findViewById(R.id.textView4);
        }
    }
}
