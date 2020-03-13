package com.example.villageoffie.userpackage;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.villageoffie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaxPay extends Fragment {
    TextView pbt, ppt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tax_pay, container, false);
        pbt = v.findViewById(R.id.pbt);
        ppt = v.findViewById(R.id.ppt);
        pbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),ViewMyProperty.class));
            }
        });
        ppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),ViewMyShop.class));
            }
        });
        return v;
    }
}