package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Model.Client;
import com.example.Model.Driver;
import com.example.Service.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterDriverActivity extends AppCompatActivity {

    int insertedID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_driver);
    }

    public void registerDriverMethod(View view){
        EditText name = (EditText)findViewById(R.id.nameDriverText);
        String nameDriver = name.getText().toString();
        EditText email = (EditText)findViewById(R.id.emailDriverText);
        String emailDriver = email.getText().toString();
        EditText phone = (EditText)findViewById(R.id.phoneDriverText);
        String phoneDriver = phone.getText().toString();
        EditText password = (EditText)findViewById(R.id.passwordDriverText);
        String passwordClient = password.getText().toString();

        Driver driver = new Driver(1,nameDriver,emailDriver,phoneDriver,passwordClient,"driver",1);
        makeTheCall(driver);
    }

    public void toggleToCar(View view){
        Log.d("driverIDToggle","driverID"+insertedID);
        if(insertedID!=0) {
            Intent i = new Intent(this, RegisterCarActivity.class);
            i.putExtra("driverID", insertedID);
            startActivity(i);
        }
    }

    private void makeTheCall(Driver driver) {
        Log.d("call","aici");
        WebService.getInstance().saveDriver(driver).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                insertedID = response.body();
                Log.d("dsdas","hello driver");
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e("UserErrror", "error on log in" + t.toString());
            }
        });
        Toast.makeText(this,"Registred",Toast.LENGTH_LONG).show();
    }
}