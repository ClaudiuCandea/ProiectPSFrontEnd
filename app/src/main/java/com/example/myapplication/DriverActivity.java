package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.Model.Car;
import com.example.Model.Driver;
import com.example.Service.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverActivity extends AppCompatActivity {

    Driver connectedDriver;
    Car driverCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        Intent i = getIntent();
        connectedDriver = new Driver();
        connectedDriver.setDriverID(i.getIntExtra("driverID",0));
        connectedDriver.setUserId(i.getIntExtra("userID",0));
        connectedDriver.setNoTakenOrders(i.getIntExtra("noTakenOrders",0));
        connectedDriver.setUserEmail(i.getStringExtra("email"));
        connectedDriver.setUserPassword(i.getStringExtra("password"));
        connectedDriver.setUserName(i.getStringExtra("name"));
        connectedDriver.setUserPhone(i.getStringExtra("phone"));
        connectedDriver.setUserType("driver");
        Log.d("DriverinActivity",connectedDriver.toString());
    }


    public void deleteAccountMethod(View view){
        makeDeleteOrderCall(connectedDriver.getDriverID());
    }

    public void logOutMethod(View view){
        finish();
    }

    public void toggleUpdate(View view){
        makeGetAllCarsCall("Update");
    }

    public void replaceFragment(View view){
        Fragment fragment = OrdersFragment.newInstance(connectedDriver.getDriverID());
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }

    public void updateDriverMethod(){
        Intent i = new Intent(this,UpdateDriverActivity.class);
        i.putExtra("driverID", connectedDriver.getDriverID());
        i.putExtra("userID", connectedDriver.getUserId());
        i.putExtra("password", connectedDriver.getUserPassword());
        i.putExtra("email", connectedDriver.getUserEmail());
        i.putExtra("phone", connectedDriver.getUserPhone());
        i.putExtra("type", "driver");
        i.putExtra("name", connectedDriver.getUserName());
        i.putExtra("noTakenOrders", connectedDriver.getNoTakenOrders());
        i.putExtra("car_id",driverCar.getCarID());
        i.putExtra("model",driverCar.getModel());
        i.putExtra("producer",driverCar.getProducer());
        i.putExtra("registrationNumber",driverCar.getRegistrationNumber());

        startActivity(i);
    }

    private void makeGetAllCarsCall(String nextStep){
        WebService.getInstance().getAllCars().enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                Log.d("getAllCars","getAllCars");
                List<Car> cars = response.body();
                for(Car car: cars) {
                    if (car.getDriverID() == connectedDriver.getDriverID()) {
                        driverCar = car;
                        break;
                    }
                }
                if(nextStep.equals("delete")){
                    makeDeleteCarCall(driverCar.getCarID());
                }
                else{
                    updateDriverMethod();
                }


            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
                Log.e("CarErrror", "error on getAllCArs" + t.toString());
            }
        });
    }

    private void makeDeleteOrderCall(int id){
        WebService.getInstance().deleteOrderDriver(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("deleteOrder","deleted");
                makeGetAllCarsCall("delete");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("UserErrror", "error on delete order" + t.toString());
            }
        });
    }

    private void makeDeleteCall(int id){
        Log.d("deleteDriver",connectedDriver.toString());
        WebService.getInstance().deleteDriver(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("deleteDriver","deleted");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("UserErrror", "error on deleteDriver" + t.toString());
            }
        });
    }

    private void makeDeleteCarCall(int id){
        Log.d("deleteCar",connectedDriver.toString());
        WebService.getInstance().deleteCar(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("deleteCar","deleted");
                makeDeleteCall(connectedDriver.getUserId());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("UserErrror", "error on deleteCar" + t.toString());
            }
        });
    }

}