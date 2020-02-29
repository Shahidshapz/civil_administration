package com.example.villageoffie.userpackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewUser extends AppCompatActivity {
    ImageView imageView;
    TextView name, age, address, village, taluk, district, job, Mobile,amt,cno,cvv;
    Button button;
    String userid, cid, cname;
    SharedPreferences sp, sp1;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private Bitmap bitmapProfile = null;
    private int STORAGE_PERMISSION_CODE = 23;
    boolean somePermissionsForeverDenied = false;
    private String userChosenTask;
    String encodedImage = "", qrstring = "";
    int t1 = 0;
    private static int RESULT_LOAD_IMAGE = 1;
    int newWidth = 200;

    int newHeight = 200;

    Matrix matrix;

    Bitmap resizedBitmap;

    float scaleWidth;

    float scaleHeight;

    ByteArrayOutputStream outputStream;

    Bitmap bitmap;
    Button pload;
String job1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);
        imageView = findViewById(R.id.imageView3);
        amt = findViewById(R.id.amount);
        cno = findViewById(R.id.cno);
        cvv = findViewById(R.id.cvv);
        name = findViewById(R.id.usernameview);
        age = findViewById(R.id.userageview);
        address = findViewById(R.id.useraddressview);
        village = findViewById(R.id.uservillageview);
        taluk = findViewById(R.id.usertalukview);
        district = findViewById(R.id.userdistrictview);
        job = findViewById(R.id.userjobview);
        Mobile = findViewById(R.id.usermobileview);
        button = findViewById(R.id.button4);
        pload = findViewById(R.id.button5);
        pload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });
        sp1=getSharedPreferences("certificate", Context.MODE_PRIVATE);
        SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
        userid = sp.getString("userid", "");
        amt.setText("Certificate Fee\t:\t"+sp1.getString("amount",""));
        cname = sp1.getString("cname", "");
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<viewuser> call = apiService.userview("view_user", userid);
        call.enqueue(new Callback<viewuser>() {
            @Override
            public void onResponse(Call<viewuser> call, Response<viewuser> response) {
                name.setText(response.body().getName());
                age.setText("Age\t\t\t\t\t\t\t\t\t\t\t\t:\t" + response.body().getAge());
                address.setText("Address\t\t\t\t\t:\t" + response.body().getAddress());
                village.setText("Village\t\t\t\t\t\t\t\t:\t" + response.body().getVillage());
                taluk.setText("Taluk\t\t\t\t\t\t\t\t\t\t:\t" + response.body().getTaluk());
                district.setText("District\t\t\t\t\t\t\t:\t" + response.body().getDistrict());
                job.setText("Job\t\t\t\t\t\t\t\t\t\t\t\t:\t" + response.body().getJob());
                job1=response.body().getJob();
                Mobile.setText("Mobile\t\t\t\t\t\t\t:\t" + response.body().getMobile());
                cno.setText("Card number\t\t\t:\t" + response.body().getCno());
                cvv.setText("cvv\t\t\t\t\t:\t" + response.body().getCvv());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] imageBytes = baos.toByteArray();
                imageBytes = Base64.decode(response.body().getImage(), Base64.DEFAULT);
                Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                imageView.setImageBitmap(decodedImage);
                if(cname.equals("Income Certificate")) {
                    if (response.body().getJob().trim().equals("Private") || response.body().getJob().equals("Government")
                            || response.body().getJob().trim().equals("private") || response.body().getJob().equals("government")) {
                        pload.setVisibility(View.VISIBLE);
                    }
                }

            }

            @Override
            public void onFailure(Call<viewuser> call, Throwable t) {
                Toast.makeText(ViewUser.this, t + "", Toast.LENGTH_SHORT).show();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(ViewUser.this, job.getText().toString()+"", Toast.LENGTH_SHORT).show();
                if(cname.equals("Income Certificate")) {
                    if (job1.trim().equals("Private") || job1.trim().equals("private")
                            || job1.trim().equals("Government") || job1.trim().equals("government")) {
                        if (!pload.getText().toString().equals("uploaded")) {
                            Toast.makeText(ViewUser.this, "please upload your salary slip", Toast.LENGTH_SHORT).show();
                        } else {
                            apply();
                        }
                    }
                }else {

                    apply();
                }

            }
        });
    }

    private void apply() {
        SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
        userid = sp.getString("userid", "");

        cid = sp1.getString("cid", "");

        // Toast.makeText(this, cname, Toast.LENGTH_SHORT).show();
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<reg> call = apiService.app("application", userid, cid, cname, encodedImage, formattedDate,sp1.getString("amount", ""));
        call.enqueue(new Callback<reg>() {
            @Override
            public void onResponse(Call<reg> call, Response<reg> response) {
                Toast.makeText(ViewUser.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), UserHome.class));
                finish();
            }

            @Override
            public void onFailure(Call<reg> call, Throwable t) {
                Toast.makeText(ViewUser.this, t + "", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void SelectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(ViewUser.this);
        builder.setTitle("Upload your documents");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                // boolean result = Utility.checkPermission(Register.this);
                if (items[item].equals("Take Photo")) {
                    userChosenTask = "Take Photo";
                    // if (result)
                    cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChosenTask = "Choose from Library";
                    // if (result)
                    galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                    Log.d("dialog dismiss ", "true");
                }
            }
        });
        builder.show();
    }

    private void galleryIntent() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }


    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                int nh = (int) (bm.getHeight() * (512.0 / bm.getWidth()));
                Bitmap scaled = Bitmap.createScaledBitmap(bm, 102, nh, true);
                reZize(bm);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    void reZize(Bitmap bp) {
        int width = bp.getWidth();
        int height = bp.getHeight();
        Matrix matrix = new Matrix();
        scaleWidth = ((float) newWidth) / width;
        scaleHeight = ((float) newHeight) / height;
        matrix.postScale(scaleWidth, scaleHeight);
        resizedBitmap = Bitmap.createBitmap(bp, 0, 0, width, height, matrix, true);
        outputStream = new ByteArrayOutputStream();
        resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        if (resizedBitmap != null) {
            getStringImage(resizedBitmap);
        }
    }

    public void getStringImage(Bitmap bmp) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();

        encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        pload.setText("uploaded");
        // Toast.makeText(getApplicationContext(), encodedImage+"", Toast.LENGTH_SHORT).show();
        //return encodedImage;
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        //Toast.makeText(getContext(), "" + destination, Toast.LENGTH_SHORT).show();
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        bitmapProfile = thumbnail;
        if (bitmapProfile != null) {
            getStringImage(bitmapProfile);
        }


    }
}
