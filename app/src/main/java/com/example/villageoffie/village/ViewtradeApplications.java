package com.example.villageoffie.village;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.villageoffie.R;
import com.example.villageoffie.pojo.login;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewtradeApplications extends AppCompatActivity {
    SharedPreferences sp;
    String userid, cid;
    ImageView imageView, aslip;
    TextView applyfor, aname, adate, afee, age, address, village, taluk, district, job, Mobile, mname, fname, adoc;
    String job1, category,appid;
    EditText opinion;
    Button issue, reject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewtrade_applications);
        imageView = findViewById(R.id.apic);
        aslip = findViewById(R.id.aslip);

        opinion = findViewById(R.id.comment);
        issue = findViewById(R.id.btnissue);
        reject = findViewById(R.id.btnrej);

        applyfor = findViewById(R.id.applyfor);
        adate = findViewById(R.id.adate);
        afee = findViewById(R.id.afee);
        aname = findViewById(R.id.aname);
        age = findViewById(R.id.aage);
        address = findViewById(R.id.aaddress);
        village = findViewById(R.id.avillage);
        taluk = findViewById(R.id.ataluk);
        district = findViewById(R.id.adist);
        job = findViewById(R.id.ajob);
        Mobile = findViewById(R.id.amobile);
        mname = findViewById(R.id.amname);
        fname = findViewById(R.id.afname);
        adoc = findViewById(R.id.adoc);
        issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (opinion.getText().toString().isEmpty()) {
                    opinion.setError("Enter your comment");
                } else {
                    category = "Approval";
                    makeDecision();
                }
            }
        });
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "Reject";
                makeDecision();
            }
        });
        adoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp1 = getSharedPreferences("verify", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed = sp1.edit();

                ed.putString("uid",sp.getString("uid",""));
                ed.commit();
                startActivity(new Intent(getApplicationContext(), verifyDocs.class));
                finish();
            }
        });
        sp = getSharedPreferences("viewApp", Context.MODE_PRIVATE);

        applyfor.setText(":\t" + sp.getString("shopname",""));
        adate.setText(":\t" + sp.getString("oname",""));
        afee.setText(":\t" + sp.getString("purpose",""));
        aname.setText(":\t" + sp.getString("ward",""));
        age.setText(":\t" + sp.getString("bno",""));
//                district.setText(":\t" + response.body().getDistrict());
//                job.setText(":\t" + response.body().getJob());
//                job1 = response.body().getJob();

//                applyfor.setText(":\t" + response.body().getCName());
//                adate.setText(":\t" + response.body().getCdate());
//                afee.setText(":\t" + response.body().getFee());

               fname.setText(":\t" +  sp.getString("address",""));
        mname.setText(":\t" +  sp.getString("adate",""));
        Mobile.setText(":\t" +  sp.getString("name",""));
//                appid=response.body().getAppId();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageBytes = baos.toByteArray();
        imageBytes = Base64.decode(sp.getString("pic",""), Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        imageView.setImageBitmap(decodedImage);
        appid=sp.getString("tid","");


    }

    private void makeDecision() {
        ApiInterface apiinterface = ApiClient.getClient().create(ApiInterface.class);
        Call<login> call = apiinterface.checkuserApproval("ApprovalTrade", category,appid,opinion.getText().toString());
        call.enqueue(new Callback<login>() {
            @Override
            public void onResponse(Call<login> call, Response<login> response) {
                Log.d("@@",response.body().getMessage());
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),TradeAppliedList.class));
                finish();
            }

            @Override
            public void onFailure(Call<login> call, Throwable t) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), TradeAppliedList.class));
        finish();
    }
}
