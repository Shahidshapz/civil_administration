package com.example.villageoffie.adminpackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.villageoffie.Others.Login;
import com.example.villageoffie.R;
import com.example.villageoffie.pojo.reg;
import com.example.villageoffie.village.Validation;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCertificates extends AppCompatActivity {
    EditText certificatename, certireq, certifee;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_certificates);
        certificatename = findViewById(R.id.certiname);
        certireq = findViewById(R.id.required);
        certifee = findViewById(R.id.fee);
        add = findViewById(R.id.certiadd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (certificatename.getText().toString().isEmpty() || !certificatename.getText().toString().matches(Validation.text)) {
                    certificatename.setError("Please enter Certificate name");
                } else if (certireq.getText().toString().isEmpty() || !certireq.getText().toString().matches(Validation.text)) {
                    certireq.setError("Please enter Required documents");
                } else if (certifee.getText().toString().isEmpty()) {
                    certifee.setError("Please enter government fee");
                } else {
                    ApiInterface apiService =
                            ApiClient.getClient().create(ApiInterface.class);

                    Call<reg> call = apiService.addcertificate("add_certificate", certificatename.getText().toString(), certireq.getText().toString(), certifee.getText().toString());
                    call.enqueue(new Callback<reg>() {
                        @Override
                        public void onResponse(Call<reg> call, Response<reg> response) {
                            Toast.makeText(AddCertificates.this, response.body().getMessage() + "", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), AddCertificates.class);
                            startActivity(i);
                        }

                        @Override
                        public void onFailure(Call<reg> call, Throwable t) {
                            Toast.makeText(AddCertificates.this, t + "", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), Admin.class);
        startActivity(i);
    }
}
