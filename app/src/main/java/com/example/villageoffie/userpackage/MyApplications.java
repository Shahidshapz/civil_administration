package com.example.villageoffie.userpackage;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.villageoffie.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyApplications extends Fragment {
    TextView birth, death, mrg;
    DatePickerDialog datePickerDialog;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_applications, container, false);
        birth = view.findViewById(R.id.birth);
        death = view.findViewById(R.id.Death);
        mrg = view.findViewById(R.id.Marrige);
        birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(view.getRootView().getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.custom_dialog);

                final TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
                text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Get Current Date
                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);

                        datePickerDialog = new DatePickerDialog(view.getRootView().getContext(),
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                        if (monthOfYear < 10 && dayOfMonth < 10) {

                                            text.setText("0" + dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year);

                                        }
                                        if (monthOfYear < 10 && dayOfMonth >= 10) {
                                            text.setText(dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year);
                                        }
                                        if (monthOfYear >= 10 && dayOfMonth < 10) {
                                            text.setText("0" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                        }
                                        if (monthOfYear >= 10 && dayOfMonth >= 10) {
                                            text.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                        }

                                        //*************Call Time Picker Here ********************

                                    }
                                }, mYear, mMonth, mDay);

                        datePickerDialog.show();
                    }
                });

                Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sp = getContext().getSharedPreferences("adate", Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed = sp.edit();
                        ed.putString("date", text.getText().toString());
                        ed.commit();
                        startActivity(new Intent(getContext(), Viewissued.class));
                    }
                });

                dialog.show();

            }
        });
        death.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(view.getRootView().getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.custom_dialog);

                final TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
                text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Get Current Date
                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);

                        datePickerDialog = new DatePickerDialog(view.getRootView().getContext(),
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                        if (monthOfYear < 10 && dayOfMonth < 10) {

                                            text.setText("0" + dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year);

                                        }
                                        if (monthOfYear < 10 && dayOfMonth >= 10) {
                                            text.setText(dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year);
                                        }
                                        if (monthOfYear >= 10 && dayOfMonth < 10) {
                                            text.setText("0" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                        }
                                        if (monthOfYear >= 10 && dayOfMonth >= 10) {
                                            text.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                        }

                                        //*************Call Time Picker Here ********************

                                    }
                                }, mYear, mMonth, mDay);

                        datePickerDialog.show();
                    }
                });

                Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sp = getContext().getSharedPreferences("adate", Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed = sp.edit();
                        ed.putString("date", text.getText().toString());
                        ed.commit();
                        startActivity(new Intent(getContext(), ViewDeathCer.class));
                    }
                });

                dialog.show();
            }
        });
        mrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(view.getRootView().getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.custom_dialog);

                final TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
                text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Get Current Date
                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);

                        datePickerDialog = new DatePickerDialog(view.getRootView().getContext(),
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                        if (monthOfYear < 10 && dayOfMonth < 10) {

                                            text.setText("0" + dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year);

                                        }
                                        if (monthOfYear < 10 && dayOfMonth >= 10) {
                                            text.setText(dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year);
                                        }
                                        if (monthOfYear >= 10 && dayOfMonth < 10) {
                                            text.setText("0" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                        }
                                        if (monthOfYear >= 10 && dayOfMonth >= 10) {
                                            text.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                        }

                                        //*************Call Time Picker Here ********************

                                    }
                                }, mYear, mMonth, mDay);

                        datePickerDialog.show();
                    }
                });

                Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sp = getContext().getSharedPreferences("adate", Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed = sp.edit();
                        ed.putString("date", text.getText().toString());
                        ed.commit();
                        startActivity(new Intent(getContext(), ViewmrgCer.class));
                    }
                });

                dialog.show();
            }
        });
        return view;
    }


}
