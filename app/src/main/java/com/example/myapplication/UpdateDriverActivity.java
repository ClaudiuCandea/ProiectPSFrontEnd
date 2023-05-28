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
import com.example.Model.Driver;
import com.example.Service.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDriverActivity extends AppCompatActivity {

    Car driverCar;
    Driver connectedDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_driver);
        Intent i = getIntent();
        driverCar = new Car();
        connectedDriver = new Driver();
        connectedDriver.setDriverID(i.getIntExtra("driverID",0));
        connectedDriver.setNoTakenOrders(i.getIntExtra("noTakenOrders",0));
        connectedDriver.setUserEmail(i.getStringExtra("email"));
        connectedDriver.setUserId(i.getIntExtra("userID",0));
        connectedDriver.setUserName(i.getStringExtra("name"));
        connectedDriver.setUserPhone(i.getStringExtra("phone"));
        connectedDriver.setUserPassword(i.getStringExtra("password"));
        driverCar.setCarID(i.getIntExtra("car_id",0));
        driverCar.setRegistrationNumber(i.getStringExtra("registrationNumber"));
        driverCar.setModel(i.getStringExtra("model"));
        driverCar.setProducer(i.getStringExtra("producer"));
        driverCar.setNoTakenOrders(i.getIntExtra("NoTakenOrders",0));
        connectedDriver.setObserver(driverCar);
        connectedDriver.setUserType("driver");
        driverCar.setDriverID(connectedDriver.getDriverID());
        EditText password = (EditText) findViewById(R.id.passwordDriverTextUpdate);
        EditText name = (EditText) findViewById(R.id.nameDriverTextUpdate);
        EditText  phone = (EditText) findViewById(R.id.phoneDriverTextUpdate);
        EditText  email = (EditText) findViewById(R.id.emailDriverTextUpdate);
        password.setText(connectedDriver.getUserPassword());
        name.setText(connectedDriver.getUserName());
        phone.setText(connectedDriver.getUserPhone());
        email.setText(connectedDriver.getUserEmail());
        EditText  registration = (EditText) findViewById(R.id.registrationTextUpdate);
        EditText  producer = (EditText) findViewById(R.id.producerTextUpdate);
        EditText  model = (EditText) findViewById(R.id.modelTextUpdate);
        registration.setText(driverCar.getRegistrationNumber());
        model.setText(driverCar.getModel());
        producer.setText(driverCar.getProducer());

    }

     public void updateDriverAndCar(View view){
        EditText password = (EditText) findViewById(R.id.passwordDriverTextUpdate);
        EditText name = (EditText) findViewById(R.id.nameDriverTextUpdate);
        EditText  phone = (EditText) findViewById(R.id.phoneDriverTextUpdate);
        EditText  email = (EditText) findViewById(R.id.emailDriverTextUpdate);
        EditText  registration = (EditText) findViewById(R.id.registrationTextUpdate);
        EditText  producer = (EditText) findViewById(R.id.producerTextUpdate);
         EditText  model = (EditText) findViewById(R.id.modelTextUpdate);
        connectedDriver.setUserPassword(password.getText().toString());
        connectedDriver.setUserName(name.getText().toString());
        connectedDriver.setUserPhone(phone.getText().toString());
        connectedDriver.setUserEmail(email.getText().toString());
        driverCar.setProducer(producer.getText().toString());
        driverCar.setModel(model.getText().toString());
        driverCar.setRegistrationNumber(registration.getText().toString());
        Log.d("driver",connectedDriver.toString());
        makeTheDriverCall(connectedDriver);
     }

    private void makeTheDriverCall(Driver driver) {
        Log.d("call","aici");
        WebService.getInstance().updateDriver(driver).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.d("UpdateDriver","returned "+ response.body());
                makeTheCarCall(driverCar);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e("UpdateError", "error on driverUpdate" + t.toString());
            }
        });
    }

    private void makeTheCarCall(Car car) {
        Log.d("call","aici");
        WebService.getInstance().updateCar(car).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.d("UpdateCar","returned "+ response.body());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e("UpdateError", "error on carUpdate" + t.toString());
            }
        });
        Toast.makeText(this,"Updated",Toast.LENGTH_LONG).show();
    }
}