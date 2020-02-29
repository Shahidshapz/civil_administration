package com.example.villageoffie.Others;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.villageoffie.R;
import com.example.villageoffie.pojo.reg;
import com.example.villageoffie.pojo.spinnerresponse;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    EditText name, age, mob, address, job, username, password,cno,cvv,fname,mname;
    Spinner village, taluk, district;
    Button add, upload;
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
    List<spinnerresponse> numberlist;
    String items[];
    Bitmap resizedBitmap;

    float scaleWidth;

    float scaleHeight;

    ByteArrayOutputStream outputStream;

    Bitmap bitmap;
String vil,tal,dist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.namereg);
        age = findViewById(R.id.agereg);
        mob = findViewById(R.id.mobilereg);
        address = findViewById(R.id.addressreg);
        village = findViewById(R.id.villagereg);
        taluk = findViewById(R.id.talukreg);
        district = findViewById(R.id.disreg);
        job = findViewById(R.id.jobreg);
        fname = findViewById(R.id.fname);
        mname = findViewById(R.id.mname);
        username = findViewById(R.id.usrreg);
        password = findViewById(R.id.passreg);
        add = findViewById(R.id.button2);
        cno = findViewById(R.id.cno);
        cvv = findViewById(R.id.cvv);
        upload = findViewById(R.id.button5);
        getspinner();
        getspinner1();
        getspinner2();
        village.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vil=village.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        taluk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tal=taluk.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dist=district.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().isEmpty()) {
                    name.setError("Please enter your name");
                } else if (age.getText().toString().isEmpty()) {
                    age.setError("Please enter your age");
                } else if (address.getText().toString().isEmpty()) {
                    address.setError("Please enter your Address");
                } else if (mob.getText().toString().isEmpty()) {
                    mob.setError("Please enter your mobile number");
                } else if (fname.getText().toString().isEmpty()) {
                    fname.setError("Please enter your fathers name");
                } else if (mname.getText().toString().isEmpty()) {
                    mname.setError("Please enter mothers name");
                } else if (username.getText().toString().isEmpty()) {
                    username.setError("Please enter your username");
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Please enter your password");
                } else if (cno.getText().toString().isEmpty()) {
                    cno.setError("Please enter your card number");
                } else if (cvv.getText().toString().isEmpty()) {
                    cvv.setError("Please enter your cvv");
                } else {
                    ApiInterface apiService =
                            ApiClient.getClient().create(ApiInterface.class);

                    Call<reg> call = apiService.register("user_register", name.getText().toString(), mob.getText().toString(),
                            address.getText().toString(), age.getText().toString(), vil, tal,
                            dist, job.getText().toString(), username.getText().toString(), password.getText().toString(), encodedImage,
                            cno.getText().toString(), cvv.getText().toString(), fname.getText().toString(), mname.getText().toString());
                    call.enqueue(new Callback<reg>() {
                        @Override
                        public void onResponse(Call<reg> call, Response<reg> response) {
                            Toast.makeText(Register.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Login.class));
                            finish();
                        }

                        @Override
                        public void onFailure(Call<reg> call, Throwable t) {
                            Toast.makeText(Register.this, t + "", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

upload.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        SelectImage();
    }


});
    }
    private void SelectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library","Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
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
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
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
        Bitmap bm=null;
        if (data != null) {
            try {
                bm= MediaStore.Images.Media.getBitmap(Register.this.getContentResolver(), data.getData());
                int nh = (int) ( bm.getHeight() * (512.0 / bm.getWidth()) );
                Bitmap scaled = Bitmap.createScaledBitmap(bm, 102, nh, true);
                reZize(bm);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
    void reZize(Bitmap bp){
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
        upload.setText("uploaded");
        // Toast.makeText(getContext(), encodedImage+"", Toast.LENGTH_SHORT).show();
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

    private void getspinner() {
      numberlist=new ArrayList<>();
        final ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<List<spinnerresponse>> call = apiService.spinner("getspinner");
        call.enqueue(new Callback<List<spinnerresponse>>() {
            @Override
            public void onResponse(Call<List<spinnerresponse>> call, Response<List<spinnerresponse>> response) {
                numberlist = response.body();
                Toast.makeText(Register.this, response.body().get(0).getV_name()+"", Toast.LENGTH_SHORT).show();
                items = new String[numberlist.size()];
                for (int i = 0; i < numberlist.size(); i++) {
                    items[i] = numberlist.get(i).getV_name();
                }

                ArrayAdapter aa = new ArrayAdapter(Register.this, android.R.layout.simple_list_item_1, items);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                village.setAdapter(aa);


            }

            @Override
            public void onFailure(Call<List<spinnerresponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getspinner1() {
        numberlist=new ArrayList<>();
        final ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<List<spinnerresponse>> call = apiService.spinner("getspinner1");
        call.enqueue(new Callback<List<spinnerresponse>>() {
            @Override
            public void onResponse(Call<List<spinnerresponse>> call, Response<List<spinnerresponse>> response) {
                numberlist = response.body();
                Toast.makeText(Register.this, response.body().get(0).getV_name()+"", Toast.LENGTH_SHORT).show();
                items = new String[numberlist.size()];
                for (int i = 0; i < numberlist.size(); i++) {
                    items[i] = numberlist.get(i).getV_taluk();
                }

                ArrayAdapter aa = new ArrayAdapter(Register.this, android.R.layout.simple_list_item_1, items);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                taluk.setAdapter(aa);


            }

            @Override
            public void onFailure(Call<List<spinnerresponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getspinner2() {
        numberlist=new ArrayList<>();
        final ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<List<spinnerresponse>> call = apiService.spinner("getspinner2");
        call.enqueue(new Callback<List<spinnerresponse>>() {
            @Override
            public void onResponse(Call<List<spinnerresponse>> call, Response<List<spinnerresponse>> response) {
                numberlist = response.body();
                Toast.makeText(Register.this, response.body().get(0).getV_name()+"", Toast.LENGTH_SHORT).show();
                items = new String[numberlist.size()];
                for (int i = 0; i < numberlist.size(); i++) {
                    items[i] = numberlist.get(i).getV_district();
                }

                ArrayAdapter aa = new ArrayAdapter(Register.this, android.R.layout.simple_list_item_1, items);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                district.setAdapter(aa);


            }

            @Override
            public void onFailure(Call<List<spinnerresponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
}