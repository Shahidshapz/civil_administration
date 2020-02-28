package com.example.villageoffie.userpackage;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.villageoffie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RejectedCertificates extends Fragment {


    public RejectedCertificates() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rejected_certificates, container, false);
    }

}
