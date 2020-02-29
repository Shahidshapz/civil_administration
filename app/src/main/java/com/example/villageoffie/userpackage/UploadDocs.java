package com.example.villageoffie.userpackage;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadDocs extends Fragment {
Spinner spinner;
Button upload,add;
String dname,uid;
SharedPreferences sp;
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

    float scaleWidth ;

    float scaleHeight;

    ByteArrayOutputStream outputStream;

    Bitmap bitmap;
    public UploadDocs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_upload_docs, container, false);
        spinner=view.findViewById(R.id.spinner);
        upload=view.findViewById(R.id.upload);
        add=view.findViewById(R.id.addupload);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dname= spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] values =
                {"select a document","Aadhar Card","Ration Card","SSLC","voters Idcard","Birth certificate","Pancard","Building Tax","Property Tax"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
SelectImage();
            }


        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dname.equals("select a document")) {
                    Toast.makeText(getContext(), "please select a document", Toast.LENGTH_SHORT).show();
                }
                else if(!upload.getText().toString().equals("uploaded")){
                    Toast.makeText(getContext(), "please select a photo", Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences sp = getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
                    uid = sp.getString("userid", "");
                    // Toast.makeText(getContext(), dname+"\n"+uid+"\n"+encodedImage, Toast.LENGTH_SHORT).show();

                    ApiInterface apiService =
                            ApiClient.getClient().create(ApiInterface.class);
                    Call<reg> call = apiService.upload("upload_Documents", uid, dname, encodedImage);
                    call.enqueue(new Callback<reg>() {
                        @Override
                        public void onResponse(Call<reg> call, Response<reg> response) {
                            Toast.makeText(getContext(), "successfully Added", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<reg> call, Throwable t) {
                            Toast.makeText(getContext(), t + "", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return view;

    }
    private void SelectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library","Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
                bm= MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), data.getData());
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
    }

