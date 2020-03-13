package com.example.villageoffie.userpackage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.villageoffie.R;
import com.example.villageoffie.pojo.reg;
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

public class MarrigeCer extends AppCompatActivity {
    EditText name,sex,dob,btime,place,dor,mname,fname,address,dtime,dreason,national,job,
            wname,wsex,wdob,wmname,wfname,waddress,wnational,wjob;
    Button apply,huspic,wifepic;
    private static int RESULT_LOAD_IMAGE = 1;
    DatePickerDialog datePickerDialog;
    private int mYear, mMonth, mDay, mHour, mMinute;

    Button img, certificate, submit;
   String qul,exval,encodedImage1;
    String encodedImage;
    int newWidth = 500;
    int newHeight = 500;
    int t1;
    Matrix matrix;
    Bitmap resizedBitmap;
    float scaleWidth;
    float scaleHeight;
    ByteArrayOutputStream outputStream;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private Bitmap bitmapProfile = null;
    private String userChosenTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marrige_cer);
        name=findViewById(R.id.cname);
        sex=findViewById(R.id.sex);
        dob=findViewById(R.id.dob);
        btime=findViewById(R.id.time);
        place=findViewById(R.id.place);
        dor=findViewById(R.id.dor);

        mname=findViewById(R.id.mname);
        fname=findViewById(R.id.fname);
        national=findViewById(R.id.national);
        job=findViewById(R.id.job);
        address=findViewById(R.id.address);


        wname=findViewById(R.id.wname);
        wsex=findViewById(R.id.wsex);
        wdob=findViewById(R.id.wdob);
        wmname=findViewById(R.id.wmname);
        wfname=findViewById(R.id.wfname);
        wnational=findViewById(R.id.wnational);
        wjob=findViewById(R.id.wjob);
        waddress=findViewById(R.id.waddress);

        apply=findViewById(R.id.btnapply);
        huspic=findViewById(R.id.btnhuspic);
        wifepic=findViewById(R.id.btnwifepic);

        dob.setFocusable(false);
        btime.setFocusable(false);
        wdob.setFocusable(false);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker();
            }
        });
        btime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker1();
            }
        });
        wdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker2();
            }
        });
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c);
        dor.setText(formattedDate);
        dor.setFocusable(false);
        huspic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1=1;
                selectImage();
            }
        });
        wifepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1=2;
                selectImage();
            }
        });
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
                String userid = sp.getString("userid", "");

                // Toast.makeText(this, cname, Toast.LENGTH_SHORT).show();

                if (name.getText().toString().isEmpty()) {
                    name.setError("Please enter Husband name");
                } else if (sex.getText().toString().isEmpty()) {
                    sex.setError("Please enter sex");
                } else if (dob.getText().toString().isEmpty()) {
                    dob.setError("Please enter date of birth");
                }
                else if (btime.getText().toString().isEmpty()) {
                    btime.setError("Please enter Marriage date");
                }else if (place.getText().toString().isEmpty()) {
                    place.setError("Please place of marriage");
                }
                else if (dor.getText().toString().isEmpty()) {
                    dor.setError("Please enter date of registration");
                }
               else if (mname.getText().toString().isEmpty()) {
                    mname.setError("Please enter mother name");
                }
                else if (fname.getText().toString().isEmpty()) {
                    fname.setError("Please enter father name");
                }
                else if (national.getText().toString().isEmpty()) {
                    national.setError("Please enter nationality");
                }
                else if (job.getText().toString().isEmpty()) {
                    job.setError("Please enter Job");
                }
                else if (address.getText().toString().isEmpty()) {
                    address.setError("Please enter address");
                }


               else if (wname.getText().toString().isEmpty()) {
                    wname.setError("Please enter wife name");
                } else if (wsex.getText().toString().isEmpty()) {
                    wsex.setError("Please enter sex");
                } else if (wdob.getText().toString().isEmpty()) {
                    wdob.setError("Please enter date of birth");
                }

                else if (wmname.getText().toString().isEmpty()) {
                    wmname.setError("Please enter mother name");
                }
                else if (wfname.getText().toString().isEmpty()) {
                    wfname.setError("Please enter father name");
                }
                else if (wnational.getText().toString().isEmpty()) {
                    wnational.setError("Please enter nationality");
                }
                else if (wjob.getText().toString().isEmpty()) {
                    wjob.setError("Please enter Job");
                }
                else if (waddress.getText().toString().isEmpty()) {
                    waddress.setError("Please enter address");
                }
                else {

                    ApiInterface apiService =
                            ApiClient.getClient().create(ApiInterface.class);
                    Call<reg> call = apiService.applicatin2("application2", userid, name.getText().toString(), sex.getText().toString(),
                            dob.getText().toString(), btime.getText().toString(), place.getText().toString(), dor.getText().toString(),
                            mname.getText().toString(), fname.getText().toString(), national.getText().toString(), job.getText().toString(), address.getText().toString(),
                            wname.getText().toString(), wsex.getText().toString(), wdob.getText().toString(), wmname.getText().toString(), wfname.getText().toString(),
                            wnational.getText().toString(), wjob.getText().toString(), waddress.getText().toString(), encodedImage, encodedImage1);
                    call.enqueue(new Callback<reg>() {
                        @Override
                        public void onResponse(Call<reg> call, Response<reg> response) {
                            Log.d("@@", response.body().getMessage());
                            Toast.makeText(MarrigeCer.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), UserHome.class));
                            finish();
                        }

                        @Override
                        public void onFailure(Call<reg> call, Throwable t) {
                            Toast.makeText(MarrigeCer.this, t + "", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }
    private void selectImage() {
        //final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MarrigeCer.this);
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
                bm = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
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
        if(t1==1) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageBytes = baos.toByteArray();

            encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            huspic.setText("uploaded");
        }
        if(t1==2) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageBytes = baos.toByteArray();

            encodedImage1 = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            wifepic.setText("uploaded");}
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

    private void datePicker() {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(MarrigeCer.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        if (monthOfYear < 10 && dayOfMonth < 10) {

                            dob.setText("0" + dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year);

                        }
                        if (monthOfYear < 10 && dayOfMonth >= 10) {
                            dob.setText(dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year);
                        }
                        if (monthOfYear >= 10 && dayOfMonth < 10) {
                            dob.setText("0" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                        if (monthOfYear >= 10 && dayOfMonth >= 10) {
                            dob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }

                        //*************Call Time Picker Here ********************

                    }
                }, mYear, mMonth, mDay);
      //  datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
    private void datePicker1() {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(MarrigeCer.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        if (monthOfYear < 10 && dayOfMonth < 10) {

                            btime.setText("0" + dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year);

                        }
                        if (monthOfYear < 10 && dayOfMonth >= 10) {
                            btime.setText(dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year);
                        }
                        if (monthOfYear >= 10 && dayOfMonth < 10) {
                            btime.setText("0" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                        if (monthOfYear >= 10 && dayOfMonth >= 10) {
                            btime.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }

                        //*************Call Time Picker Here ********************

                    }
                }, mYear, mMonth, mDay);
      //  datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
    private void datePicker2() {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(MarrigeCer.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        if (monthOfYear < 10 && dayOfMonth < 10) {

                            wdob.setText("0" + dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year);

                        }
                        if (monthOfYear < 10 && dayOfMonth >= 10) {
                            wdob.setText(dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year);
                        }
                        if (monthOfYear >= 10 && dayOfMonth < 10) {
                            wdob.setText("0" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                        if (monthOfYear >= 10 && dayOfMonth >= 10) {
                            wdob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }

                        //*************Call Time Picker Here ********************

                    }
                }, mYear, mMonth, mDay);
       // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),UserHome.class));finish();
    }
}
