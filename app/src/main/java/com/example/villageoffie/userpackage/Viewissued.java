package com.example.villageoffie.userpackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
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
import com.example.villageoffie.pojo.viewAppli;
import com.example.villageoffie.village.VillageHome;
import com.example.villageoffie.village.verifyDocs;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Viewissued extends AppCompatActivity {
    SharedPreferences sp;
    String userid, cid;
    ImageView imageView, aslip;
    TextView applyfor, aname, adate, afee, age, address, village, taluk, district, job, Mobile, mname, fname, adoc;
    String job1, category,appid;
    EditText opinion;
    Button issue, reject;
    private File pdfFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewissued);
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
                try {
                    createPdfWrapper();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        });


        sp = getSharedPreferences("viewApp", Context.MODE_PRIVATE);
        userid = sp.getString("uid", "");
        cid = sp.getString("cid", "");
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<viewAppli> call = apiService.getAppli("villageviewApplications", userid, cid);
        call.enqueue(new Callback<viewAppli>() {
            @Override
            public void onResponse(Call<viewAppli> call, Response<viewAppli> response) {
                aname.setText("Applicant name\t\t\t\t\t\t\t:\t" + response.body().getName());
                age.setText("Age\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t:\t" + response.body().getAge());
                address.setText("Address\t\t\t\t\t\t\t\t\t\t\t\t\t:\t" + response.body().getAddress());
                village.setText("Village\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t:\t" + response.body().getVillage());
                taluk.setText("Taluk\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t:\t" + response.body().getTaluk());
                district.setText("District\t\t\t\t\t\t\t\t\t\t\t\t\t\t:\t" + response.body().getDistrict());
                job.setText(response.body().getComment());
                job1 = response.body().getJob();
                Mobile.setText("Mobile\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t:\t" + response.body().getMobile());
                applyfor.setText(response.body().getCName());
                adate.setText("Date:\t" + response.body().getCdate());
                afee.setText("Paid Fee\t\t\t\t\t\t\t\t\t\t\t\t\t:\t" + response.body().getFee());
                mname.setText("Mother's name\t\t\t\t\t\t\t\t:\t" + response.body().getMname());
                fname.setText("Father's Name\t\t\t\t\t\t\t\t:\t" + response.body().getFname());
                appid=response.body().getAppId();

            }

            @Override
            public void onFailure(Call<viewAppli> call, Throwable t) {

            }
        });

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), UserHome.class));
        finish();
    }

    private void createPdfWrapper() throws FileNotFoundException,DocumentException{

        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    showMessageOKCancel("You need to allow access to Storage",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                0);
                                    }
                                }
                            });
                    return;
                }

                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        0);
            }
            return;
        }else {
            createPdf();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    try {
                        createPdfWrapper();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Permission Denied
                    Toast.makeText(this, "WRITE_EXTERNAL Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void createPdf() throws FileNotFoundException, DocumentException {

        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            Log.d("@@", "Created a new directory for PDF");
        }

        pdfFile = new File(docsFolder.getAbsolutePath(),"HelloWorld.pdf");
        OutputStream output = new FileOutputStream(pdfFile);
        Document document = new Document();
        PdfWriter.getInstance(document, output);
        document.open();
        document.add(new Paragraph(applyfor.getText().toString()));

        document.close();
        previewPdf();

    }

    private void previewPdf() {

        PackageManager packageManager = getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() > 0) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(pdfFile);
            intent.setDataAndType(uri, "application/pdf");

            startActivity(intent);
        }else{
            Toast.makeText(this,"Download a PDF Viewer to see the generated PDF",Toast.LENGTH_SHORT).show();
        }
    }
}
