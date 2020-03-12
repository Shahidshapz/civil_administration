package com.example.villageoffie.adminpackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.villageoffie.Others.Login;
import com.example.villageoffie.R;

public class Admin extends AppCompatActivity {
Button addvillage,addcerti,viewvill,viewcerti,log,users,feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        addvillage=findViewById(R.id.villadd);
        addcerti=findViewById(R.id.certadd);
        viewvill=findViewById(R.id.viewvill);
        viewcerti=findViewById(R.id.viewcert);
        users=findViewById(R.id.user);
        feedback=findViewById(R.id.viewfeed);
        log=findViewById(R.id.logoutadmin);
       addvillage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i=new Intent(getApplicationContext(),AddVillage.class);
               startActivity(i);
           }
       });
       addcerti.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i=new Intent(getApplicationContext(),AddCertificates.class);
               startActivity(i);
           }
       });
       viewvill.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i=new Intent(getApplicationContext(),ViewVillages.class);
               startActivity(i);
           }
       });
       viewcerti.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i=new Intent(getApplicationContext(),ViewCertificates.class);
               startActivity(i);
           }
       });
        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),ViewUsers.class);
                startActivity(i);
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Viewfeedback.class);
                startActivity(i);
            }
        });
       log.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i=new Intent(getApplicationContext(), Login.class);
               startActivity(i);
           }
       });

    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(), Login.class);
        startActivity(i);
    }
}
