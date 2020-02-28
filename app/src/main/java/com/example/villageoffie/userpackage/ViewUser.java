package com.example.villageoffie.userpackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.villageoffie.R;
import com.example.villageoffie.pojo.reg;
import com.example.villageoffie.pojo.viewcertificate;
import com.example.villageoffie.pojo.viewuser;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import java.io.ByteArrayOutputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewUser extends AppCompatActivity {
ImageView imageView;
TextView name,age,address,village,taluk,district,job,Mobile;
Button button;
String userid,cid,cname;
SharedPreferences sp,sp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);
        imageView=findViewById(R.id.imageView3);
        name=findViewById(R.id.usernameview);
        age=findViewById(R.id.userageview);
        address=findViewById(R.id.useraddressview);
        village=findViewById(R.id.uservillageview);
        taluk=findViewById(R.id.usertalukview);
        district=findViewById(R.id.userdistrictview);
        job=findViewById(R.id.userjobview);
        Mobile=findViewById(R.id.usermobileview);
        button=findViewById(R.id.button4);

                SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
                userid = sp.getString("userid", "");
                ApiInterface apiService =
                        ApiClient.getClient().create(ApiInterface.class);
                Call<viewuser> call=apiService.userview("view_user",userid);
                call.enqueue(new Callback<viewuser>() {
                    @Override
                    public void onResponse(Call<viewuser> call, Response<viewuser> response) {
                        name.setText(response.body().getName());
                        age.setText("Age\t\t\t\t\t\t:\t"+response.body().getAge());
                        address.setText("Address\t\t:\t"+response.body().getAddress());
                        village.setText("Village\t\t\t:\t"+response.body().getVillage());
                        taluk.setText("Taluk\t\t\t\t\t:\t"+response.body().getTaluk());
                        district.setText("District\t\t\t:\t"+response.body().getDistrict());
                        job.setText("Job\t\t\t\t\t\t\t:\t"+response.body().getJob());
                        Mobile.setText("Mobile\t\t\t:\t"+response.body().getMobile());
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] imageBytes = baos.toByteArray();
                        imageBytes = Base64.decode(response.body().getImage(), Base64.DEFAULT);
                        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                        imageView.setImageBitmap(decodedImage);

                    }

                    @Override
                    public void onFailure(Call<viewuser> call, Throwable t) {
                        Toast.makeText(ViewUser.this, t+"", Toast.LENGTH_SHORT).show();
                    }
                });
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
                        userid = sp.getString("userid", "");
                        SharedPreferences sp1 = getSharedPreferences("certificate", Context.MODE_PRIVATE);
                        cid = sp1.getString("cid", "");
                        cname = sp1.getString("cname", "");

                        ApiInterface apiService =
                                ApiClient.getClient().create(ApiInterface.class);
                        Call<reg> call=apiService.app("application",userid,cid,cname);
                        call.enqueue(new Callback<reg>() {
                            @Override
                            public void onResponse(Call<reg> call, Response<reg> response) {
                                Toast.makeText(ViewUser.this, "Success", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<reg> call, Throwable t) {
                                Toast.makeText(ViewUser.this, t+"", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }

}
