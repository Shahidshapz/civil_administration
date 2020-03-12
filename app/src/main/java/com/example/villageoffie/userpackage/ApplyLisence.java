package com.example.villageoffie.userpackage;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.villageoffie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApplyLisence extends Fragment {
TextView btax,tl;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_apply_lisence, container, false);
        btax=v.findViewById(R.id.btax);
        tl=v.findViewById(R.id.tl);
        btax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),BuildingPermission.class));
            }
        });
        tl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),TradeLisence.class));
            }
        });
        return  v;
    }

}
