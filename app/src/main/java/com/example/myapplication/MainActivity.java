package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Model.Client;
import com.example.Model.Driver;
import com.example.Model.User;
import com.example.Service.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    User returnedUser;
    Client connectedClient;
    Driver connectedDriver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void logInMethod(View button){
        EditText emailText = (EditText) findViewById(R.id.emailText);
        String email = emailText.getText().toString();
       TextView textView = (TextView) findViewById(R.id.signIn);
        //textView.set
        Log.d("email",email);

        EditText passText = (EditText) findViewById(R.id.passwordText);
        String password = passText.getText().toString();
        Log.d("email",password);
        makeTheCall(email,password);
        /*
        Log.d("aftermakecall","aici");
        if (returnedUser != null){
            Log.d("insideif","aici");
            if(password.equals(returnedUser.getUserPassword())){
                Log.d("name",returnedUser.getUserName());
                //if(returnedUser.getUserType().equals("client")) {
                    makeTheCallGetClient(returnedUser.getUserId());
                    Intent i = new Intent(this, ClientActivity.class);
                    i.putExtra("clientID",connectedClient.getClientID());
                    i.putExtra("userID",connectedClient.getUserId());
                    i.putExtra("password",connectedClient.getUserPassword());
                    i.putExtra("email",connectedClient.getUserEmail());
                    i.putExtra("phone",connectedClient.getUserPhone());
                    i.putExtra("type","client");
                    i.putExtra("name",connectedClient.getUserName());
                    i.putExtra("card",connectedClient.getCardNumber());
                    startActivity(i);
                //}
            }
            else{
                Toast.makeText(this,"Wrong password",Toast.LENGTH_LONG);
            }
        }
        else{
            Toast.makeText(this,"Wrong email",Toast.LENGTH_LONG);
        }
        */


    }

    public void RegisterClientButton(View button){
        Intent i = new Intent(this,RegisterClientActivity.class);
        startActivity(i);
    }
    public void RegisterDriverButton(View button){
        Intent i = new Intent(this,RegisterDriverActivity.class);
        startActivity(i);
    }

    public void logIn2(String password){
        Log.d("aftermakecall","aici");
        if (returnedUser != null){
            Log.d("insideif","aici");
            if(password.equals(returnedUser.getUserPassword())){
                Log.d("name",returnedUser.getUserName());
                if(returnedUser.getUserType().equals("client")) {
                    makeTheCallGetClient(returnedUser.getUserId());
                }
                else if(returnedUser.getUserType().equals("driver")){
                    makeTheCallGetDriver(returnedUser.getUserId());
                }
                else if(returnedUser.getUserType().equals("admin")){
                    Intent i = new Intent(this,AdminActivity.class);
                    startActivity(i);
                }
            }
            else{
                Toast.makeText(this,"Inexistent user",Toast.LENGTH_LONG).show();
            }
        }

    }
    public void openNewIntent(){
        Intent i = new Intent(this, ClientActivity.class);
        i.putExtra("clientID", connectedClient.getClientID());
        i.putExtra("userID", connectedClient.getUserId());
        i.putExtra("password", connectedClient.getUserPassword());
        i.putExtra("email", connectedClient.getUserEmail());
        i.putExtra("phone", connectedClient.getUserPhone());
        i.putExtra("type", "client");
        i.putExtra("name", connectedClient.getUserName());
        i.putExtra("card", connectedClient.getCardNumber());
        startActivity(i);
    }

    public void openNewIntentDriver(){
        Intent i = new Intent(this, DriverActivity.class);
        i.putExtra("driverID", connectedDriver.getDriverID());
        i.putExtra("userID", connectedDriver.getUserId());
        i.putExtra("password", connectedDriver.getUserPassword());
        i.putExtra("email", connectedDriver.getUserEmail());
        i.putExtra("phone", connectedDriver.getUserPhone());
        i.putExtra("type", "driver");
        i.putExtra("name", connectedDriver.getUserName());
        i.putExtra("noTakenOrders", connectedDriver.getNoTakenOrders());
        startActivity(i);
    }

    private void makeTheCall(String email,String password) {
        Log.d("call","aici");
        WebService.getInstance().getUserByEmail(email).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
               returnedUser = response.body();
               Log.d("ConnectedUser",returnedUser.toString());
               logIn2(password);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("UserErrror", "error on log in" + t.toString());
            }
        });
    }


    private void makeTheCallGetClient(int userID) {
        Log.d("call","aici");
        WebService.getInstance().getClientByUserID(userID).enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                connectedClient = response.body();
                Log.d("connectedClient",connectedClient.toString());
                openNewIntent();
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Log.e("UserErrror", "error on log in" + t.toString());
            }
        });
    }
    private void makeTheCallGetDriver(int userID) {
        Log.d("call","aici");
        WebService.getInstance().getDriverByUserID(userID).enqueue(new Callback<Driver>() {
            @Override
            public void onResponse(Call<Driver> call, Response<Driver> response) {
                connectedDriver = response.body();
                Log.d("connectedClient",connectedDriver.toString());
                openNewIntentDriver();
            }

            @Override
            public void onFailure(Call<Driver> call, Throwable t) {
                Log.e("DriverGet", "error on log in" + t.toString());
            }
        });
    }
}