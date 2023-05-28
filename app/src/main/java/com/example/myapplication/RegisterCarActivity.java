package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Model.Car;
import com.example.Model.Client;
import com.example.Service.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterCarActivity extends AppCompatActivity {
    int driverID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_car);
        Intent i  = getIntent();
        driverID = i.getIntExtra("driverID",0);
    }

    public void registerCarMethod(View view){
        EditText registerText = (EditText) findViewById(R.id.registrationText);
        String registerNumber = registerText.getText().toString();
        EditText producerText = (EditText) findViewById(R.id.producerText);
        String producer = producerText.getText().toString();
        EditText modelText = (EditText) findViewById(R.id.modelText);
        String model = modelText.getText().toString();
        Car car = new Car(1,producer,model,registerNumber,driverID);
        Log.d("driverId","driverId="+driverID);
        makeTheCall(car);

    }

    private void makeTheCall(Car car) {
        WebService.getInstance().saveCar(car).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.d("",response.body().toString());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e("UserErrror", "error on register car" + t.toString());
            }
        });
        Toast.makeText(this,"Registred",Toast.LENGTH_LONG).show();
    }
}