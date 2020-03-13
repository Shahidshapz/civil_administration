package com.example.villageoffie.userpackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.villageoffie.R;
import com.example.villageoffie.pojo.TradePojo;
import com.example.villageoffie.pojo.Viewbirthpojo;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewTradeLisence extends AppCompatActivity {
    SharedPreferences sp;
    String userid, cid;
    ImageView imageView, aslip;
    TextView applyfor, aname, adate, afee, age, address, village, taluk, district, job, Mobile, mname, fname, adoc;
    String job1, category,appid;
    EditText opinion;
    Button issue, reject;
    private File pdfFile;
    String date;
    ProgressDialog pd;
    LinearLayout st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trade_lisence);
        imageView = findViewById(R.id.apic);
        aslip = findViewById(R.id.aslip);
        SharedPreferences sp = getSharedPreferences("adate", Context.MODE_PRIVATE);
        date=sp.getString("date","");
        Toast.makeText(this, date+"", Toast.LENGTH_SHORT).show();
        opinion = findViewById(R.id.comment);
        issue = findViewById(R.id.btnissue);
        reject = findViewById(R.id.btnrej);
        st = findViewById(R.id.abc);
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
//                try {
//                    pd = new ProgressDialog(ViewTradeLisence.this);
//                    pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                    pd.setMessage("Please wait,when pdf generate completed it will automatically open.");
//                    pd.show();
//                    createPdfWrapper();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (DocumentException e) {
//                    e.printStackTrace();
//                }
                takeScreenshot();
            }
        });
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        final String formattedDate = df.format(c);
        SharedPreferences sp1 = getSharedPreferences("login", Context.MODE_PRIVATE);

        userid = sp1.getString("userid", "");
        Toast.makeText(this, userid+"", Toast.LENGTH_SHORT).show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TradePojo> call = apiService.getTrade("ViewTrade", userid,date);
        call.enqueue(new Callback<TradePojo>() {
            @Override
            public void onResponse(Call<TradePojo> call, Response<TradePojo> response) {
                Toast.makeText(ViewTradeLisence.this, response.body().getAddress()+"", Toast.LENGTH_SHORT).show();


                aname.setText(":\t" + response.body().getShopname());
                age.setText(":\t" + response.body().getShoppurpose());
                mname.setText(":\t" + response.body().getWard());
                fname.setText(":\t" + response.body().getShopowner());
                address.setText(":\t" + response.body().getAddress());
                village.setText(":\t" + response.body().getDor());

                job.setText(response.body().getComment());

                Mobile.setText(":\t" + response.body().getBno());
                //  applyfor.setText(response.body().getCName());
                adate.setText(formattedDate);
                // afee.setText(":\t" + response.body().getFee());

                //  appid=response.body().getAppId();
        }

            @Override
            public void onFailure(Call<TradePojo> call, Throwable t) {

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

        pdfFile = new File(docsFolder.getAbsolutePath(), aname.getText().toString()+"."+applyfor.getText().toString()+".pdf");
        OutputStream output = new FileOutputStream(pdfFile);
        Document document = new Document();
        PdfWriter.getInstance(document, output);
        document.open();


        try {
            document.setPageSize(PageSize.LETTER);
            document.setMargins(36, 72, 108, 180);
            document.setMarginMirroring(false);
            Drawable d = getResources().getDrawable(R.drawable.glogo);
            BitmapDrawable bitDw = ((BitmapDrawable) d);
            Bitmap bmp = bitDw.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Image image = Image.getInstance(stream.toByteArray());
            image.setAbsolutePosition(260, 750);
            image.scaleToFit(850, 80);
            document.add(image);

            String toptext = "GOVERNMENT OF KERALA";
            float gfntSize, glineSpacing;
            gfntSize = 9.7f;
            glineSpacing = 10f;
            String textvil =applyfor.getText().toString();
            Paragraph govttext = new Paragraph(new Phrase(glineSpacing, toptext + Chunk.NEWLINE + Chunk.NEWLINE + textvil,
                    FontFactory.getFont(FontFactory.COURIER, gfntSize)));
            govttext.setSpacingAfter(100);
            govttext.setSpacingBefore(60);
            govttext.setAlignment(Element.ALIGN_CENTER);
            govttext.setIndentationLeft(140);
            govttext.setIndentationRight(100);
            document.add(govttext);

            //village office text

            try {
                Drawable qr = getResources().getDrawable(R.drawable.qr);
                BitmapDrawable qrdrawable = ((BitmapDrawable) qr);
                Bitmap bitmap = qrdrawable.getBitmap();
                ByteArrayOutputStream qrstream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, qrstream);
                Image fimage = Image.getInstance(qrstream.toByteArray());
                fimage.setAbsolutePosition(40, 650);
                fimage.scaleToFit(850, 80);

                String date = "Date :"+adate.getText().toString();
                Paragraph dataparaagraph = new Paragraph(new Phrase(10, date,
                        FontFactory.getFont(FontFactory.COURIER, gfntSize)));
                dataparaagraph.setIndentationLeft(400);
                dataparaagraph.setSpacingBefore(5);
                dataparaagraph.setSpacingAfter(20);
                document.add(dataparaagraph);
                document.add(fimage);
                Paragraph name = new Paragraph(new Phrase(10, "Applicant Name\t\t\t\t\t\t\t" + aname.getText().toString(),
                        FontFactory.getFont(FontFactory.COURIER, gfntSize)));
                name.setSpacingAfter(20);
                name.setAlignment(Element.ALIGN_CENTER);
                name.setIndentationLeft(140);
                name.setIndentationRight(100);
                document.add(name);
                Paragraph age1 = new Paragraph(new Phrase(10, "Age\t\t\t\t\t\t\t" + age.getText().toString(),
                        FontFactory.getFont(FontFactory.COURIER, gfntSize)));
                age1.setSpacingAfter(20);
                age1.setAlignment(Element.ALIGN_CENTER);
                age1.setIndentationLeft(140);
                age1.setIndentationRight(100);
                document.add(age1);
                Paragraph add = new Paragraph(new Phrase(10, "Address\t\t\t\t\t\t\t" + address.getText().toString(),
                        FontFactory.getFont(FontFactory.COURIER, gfntSize)));
                add.setSpacingAfter(20);
                add.setAlignment(Element.ALIGN_CENTER);
                add.setIndentationLeft(140);
                add.setIndentationRight(100);
                document.add(add);
                Paragraph f = new Paragraph(new Phrase(10, "Father's name\t\t\t\t\t\t\t" + fname.getText().toString(),
                        FontFactory.getFont(FontFactory.COURIER, gfntSize)));
                f.setSpacingAfter(20);
                f.setAlignment(Element.ALIGN_CENTER);
                f.setIndentationLeft(140);
                f.setIndentationRight(100);
                document.add(f);
                Paragraph m = new Paragraph(new Phrase(10, "Mother's's name\t\t\t\t\t\t\t" + mname.getText().toString(),
                        FontFactory.getFont(FontFactory.COURIER, gfntSize)));
                m.setSpacingAfter(20);
                m.setAlignment(Element.ALIGN_CENTER);
                m.setIndentationLeft(140);
                m.setIndentationRight(100);
                document.add(m);
                Paragraph vil = new Paragraph(new Phrase(10, "Village\t\t\t\t\t\t\t" + village.getText().toString(),
                        FontFactory.getFont(FontFactory.COURIER, gfntSize)));
                vil.setSpacingAfter(20);
                vil.setAlignment(Element.ALIGN_CENTER);
                vil.setIndentationLeft(140);
                vil.setIndentationRight(100);
                document.add(vil);
                Paragraph tal = new Paragraph(new Phrase(10, "Taluk\t\t\t\t\t\t\t" + taluk.getText().toString(),
                        FontFactory.getFont(FontFactory.COURIER, gfntSize)));
                tal.setSpacingAfter(20);

                tal.setAlignment(Element.ALIGN_CENTER);
                tal.setIndentationLeft(140);
                tal.setIndentationRight(100);
                document.add(tal);
                Paragraph dis = new Paragraph(new Phrase(10, "District\t\t\t\t\t\t\t" + district.getText().toString(),
                        FontFactory.getFont(FontFactory.COURIER, gfntSize)));
                dis.setSpacingAfter(20);

                dis.setAlignment(Element.ALIGN_CENTER);
                dis.setIndentationLeft(140);
                dis.setIndentationRight(100);
                document.add(dis);
                Paragraph com = new Paragraph(new Phrase(10, job.getText().toString(),
                        FontFactory.getFont(FontFactory.COURIER, gfntSize)));
                com.setSpacingAfter(20);
                com.setAlignment(Element.ALIGN_CENTER);
                com.setIndentationLeft(140);
                com.setIndentationRight(100);
                document.add(com);

                Drawable sin = getResources().getDrawable(R.drawable.cc);
                BitmapDrawable qrdrawable1 = ((BitmapDrawable) sin);
                Bitmap bitmap1 = qrdrawable1.getBitmap();
                ByteArrayOutputStream qrstream1 = new ByteArrayOutputStream();
                bitmap1.compress(Bitmap.CompressFormat.PNG, 100, qrstream1);
                Image fimage1 = Image.getInstance(qrstream1.toByteArray());
                fimage1.setAbsolutePosition(380, 210);
                fimage1.scaleToFit(850, 80);
                document.add(fimage1);
                Paragraph last = new Paragraph(new Phrase(10, "Digitally Signed By Villege officer ,This Signature is Digitally Verified.This Certificate does not require any seal or manual sign .This certificate has Long validity .for more visit our site:https://www.gov.in",
                        FontFactory.getFont(FontFactory.COURIER, gfntSize)));

                last.setSpacingBefore(100);
                last.setSpacingAfter(100);
                document.add(last);

            } catch (Exception e) {
                e.printStackTrace();
            }
            document.close();
            pd.dismiss();
            previewPdf();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    private void takeScreenshot() {
        Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }
    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }
}
