package com.example.villageoffie.adminpackage;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.villageoffie.R;
import com.example.villageoffie.pojo.reg;
import com.example.villageoffie.village.Validation;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddVillage extends AppCompatActivity {
    EditText name, district, state, place, pinno, mobile, username, password;
    Button add;
    Spinner taluk;
    String of;
String officer[]={"Choose a officer","Permission_Approval_officer","Certificate_Approval_officer","Tax_approving_officer"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_village);
        name = findViewById(R.id.namevill);
        taluk = findViewById(R.id.nametaluk);
        district = findViewById(R.id.namedistrict);
        state = findViewById(R.id.statename);
        place = findViewById(R.id.nameplace);
        pinno = findViewById(R.id.pin);
        mobile = findViewById(R.id.mob);
        username = findViewById(R.id.uservill);
        password = findViewById(R.id.passvill);
        add = findViewById(R.id.addvillage);
        taluk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               of=taluk.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter aa=new ArrayAdapter(this,android.R.layout.simple_spinner_item,officer);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        taluk.setAdapter(aa);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().isEmpty() || !name.getText().toString().matches(Validation.text)) {
                    name.setError("Please enter your name");
                } else if (of.equals("Choose a officer")) {
                    Toast.makeText(AddVillage.this, "please choose a officer", Toast.LENGTH_SHORT).show();
                } else if (district.getText().toString().isEmpty() || !district.getText().toString().matches(Validation.text)) {
                    district.setError("Please enter your District");
                } else if (state.getText().toString().isEmpty() || !state.getText().toString().matches(Validation.text)) {
                    state.setError("Please enter your state");
                } else if (place.getText().toString().isEmpty() || !place.getText().toString().matches(Validation.text)) {
                    place.setError("Please enter your place");
                } else if (pinno.getText().toString().isEmpty() || !pinno.getText().toString().matches(Validation.pin)) {
                    pinno.setError("Please enter pincode");
                } else if (username.getText().toString().isEmpty()) {
                    username.setError("Please enter your username");
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Please enter your password");
                } else if (mobile.getText().toString().isEmpty() || !mobile.getText().toString().matches(Validation.mobile)) {
                    mobile.setError("Please enter your Mobile number");
                } else {
                    ApiInterface apiService =
                            ApiClient.getClient().create(ApiInterface.class);

                    Call<reg> call = apiService.addvillage("add_village", name.getText().toString(), of,
                            district.getText().toString(), state.getText().toString(), place.getText().toString(), pinno.getText().toString()
                            , mobile.getText().toString(), username.getText().toString(), password.getText().toString());

                    call.enqueue(new Callback<reg>() {
                        @Override
                        public void onResponse(Call<reg> call, Response<reg> response) {
                            if(response.body().getMessage().equals("This Officer was already Available")){
                                Toast.makeText(AddVillage.this, response.body().getMessage() + "", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                SmsManager sms = SmsManager.getDefault();
                                sms.sendTextMessage(mobile.getText().toString().trim(), null, "Notification from Civil Administration.You are add to this App. you can now login using the following credentials.Thank you\nusername:" + username.getText().toString() + "\npassword:" + password.getText().toString(), null, null);

                                Toast.makeText(AddVillage.this, response.body().getMessage() + "", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), AddVillage.class);
                                startActivity(i);
                            }

                        }

                        @Override
                        public void onFailure(Call<reg> call, Throwable t) {
                            Toast.makeText(AddVillage.this, t + "", Toast.LENGTH_SHORT).show();
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
