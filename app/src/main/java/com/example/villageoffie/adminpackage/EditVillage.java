package com.example.villageoffie.adminpackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.villageoffie.Others.Validations;
import com.example.villageoffie.R;
import com.example.villageoffie.pojo.reg;
import com.example.villageoffie.web.ApiClient;
import com.example.villageoffie.web.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditVillage extends AppCompatActivity {
EditText vname,taluk,district,state,place,pin,mob;
Button Edit;
SharedPreferences sp;
String vid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_village);

        vname=findViewById(R.id.vnameedit);
        taluk=findViewById(R.id.vtalukedit);
        district=findViewById(R.id.districtedit);
        state=findViewById(R.id.vstateedit);
        place=findViewById(R.id.vplaceedit);
        pin=findViewById(R.id.vpinedit);
        mob=findViewById(R.id.vmobedit);
        Edit=findViewById(R.id.button3);
        sp=getSharedPreferences("village",getApplicationContext().MODE_PRIVATE);
        vname.setText(sp.getString("vname",""));
        taluk.setText(sp.getString("vtaluk",""));
        district.setText(sp.getString("vdistrict",""));
        state.setText(sp.getString("vstate",""));
        place.setText(sp.getString("vplace",""));
        pin.setText(sp.getString("vpin",""));
        mob.setText(sp.getString("vmob",""));
      vid=sp.getString("vid","");
     Edit.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             if (vname.getText().toString().isEmpty()) {
                 vname.setError("Please enter your village name");
             } else if (taluk.getText().toString().isEmpty()) {
                 taluk.setError("Please enter taluk name");
             } else if (district.getText().toString().isEmpty()) {
                 district.setError("Please enter district");
             }
             else if (state.getText().toString().isEmpty()) {
                 state.setError("Please enter state");
             }
             else if (place.getText().toString().isEmpty()) {
                 place.setError("Please enter your place");
             }
             else if (pin.getText().toString().isEmpty()) {
                 pin.setError("Please enter pincode");
             } else if ((mob.getText().toString().isEmpty() || !mob.getText().toString().matches(Validations.mobile)))
             { mob.setError("please enter valid contact number");
             }else{
             ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
             Call<reg> call = apiInterface.villageedit("editvillage",vid,vname.getText().toString(),taluk.getText().toString(),
             district.getText().toString(),state.getText().toString(),place.getText().toString(),pin.getText().toString(),mob.getText().toString()
                     );
             call.enqueue(new Callback<reg>() {
                 @Override
                 public void onResponse(Call<reg> call, Response<reg> response) {
                     Toast.makeText(EditVillage.this, "Edited", Toast.LENGTH_SHORT).show();
                     Intent i=new Intent(getApplicationContext(),ViewVillages.class);
                     startActivity(i);
                 }

                 @Override
                 public void onFailure(Call<reg> call, Throwable t) {
                     Toast.makeText(EditVillage.this, t+"", Toast.LENGTH_SHORT).show();
                 }
             });
         } }
     });

    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),ViewVillages.class);
        startActivity(i);
    }
}
