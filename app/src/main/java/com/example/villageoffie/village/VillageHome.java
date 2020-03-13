package com.example.villageoffie.village;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.villageoffie.Others.Login;
import com.example.villageoffie.R;
import com.example.villageoffie.userpackage.ViewBuildingPermit;
import com.example.villageoffie.userpackage.ViewTradeLisence;

public class VillageHome extends AppCompatActivity {
    TextView vbp, vtl,vbcer,vdcer,vmrg,vbtax,vptax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_village_home);
        vbp = findViewById(R.id.Vbp);
        vtl = findViewById(R.id.vtl);
        vbcer = findViewById(R.id.bcer);
        vdcer = findViewById(R.id.dcer);
        vmrg = findViewById(R.id.vmrg);
        vbtax = findViewById(R.id.btax);
        vptax = findViewById(R.id.ptax);

        SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
        String type=sp.getString("utype","");
if(type.equals("Permission_Approval_officer")){
    vbp.setVisibility(View.VISIBLE);
    vtl.setVisibility(View.VISIBLE);
}
      else  if(type.equals("Certificate_Approval_officer")){
    vbcer.setVisibility(View.VISIBLE);
    vdcer.setVisibility(View.VISIBLE);
    vmrg.setVisibility(View.VISIBLE);
        }
       else if(type.equals("Tax_approving_officer")){
    vbtax.setVisibility(View.VISIBLE);
    vptax.setVisibility(View.VISIBLE);
        }

        vbp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Appliedlist.class));
            }
        });
        vtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TradeAppliedList.class));
            }
        });
        vbcer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BirthAppliedlist.class));
            }
        });
        vdcer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DeathAppliedList.class));
            }
        });
        vmrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MrgAppliedList.class));
            }
        });
        vbtax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ViewTradeLisence.class));
            }
        });
        vptax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ViewBuildingPermit.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "please logout", Toast.LENGTH_SHORT).show();
    }

}
