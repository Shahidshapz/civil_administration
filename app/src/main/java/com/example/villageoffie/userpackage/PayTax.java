package com.example.villageoffie.userpackage;


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
public class PayTax extends Fragment {

TextView vbp,vtl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_pay_tax, container, false);
        vbp=v.findViewById(R.id.Vbp);
        vtl=v.findViewById(R.id.vtl);
        vbp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        vtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return v;
    }

}
