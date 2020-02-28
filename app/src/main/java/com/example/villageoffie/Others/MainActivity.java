package com.example.villageoffie.Others;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.villageoffie.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Thread background = new Thread() {
            public void run() {
                try {

                    sleep(2*1000);


                    Intent i=new Intent(getApplicationContext(),Login.class);
                    startActivity(i);


                    finish();
                } catch (Exception e) {
                }
            }
        };

        background.start();
    }

    }

