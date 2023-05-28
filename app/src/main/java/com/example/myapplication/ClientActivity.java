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
import com.example.Model.Order;
import com.example.Service.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientActivity extends AppCompatActivity {

    Client connectedClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        Intent i = getIntent();
        connectedClient = new Client();
        connectedClient.setClientID(i.getIntExtra("clientID",0));
        connectedClient.setUserId(i.getIntExtra("userID",0));
        connectedClient.setCardNumber(i.getStringExtra("card"));
        connectedClient.setUserEmail(i.getStringExtra("email"));
        connectedClient.setUserPassword(i.getStringExtra("password"));
        connectedClient.setUserName(i.getStringExtra("name"));
        connectedClient.setUserPhone(i.getStringExtra("phone"));
        connectedClient.setUserType("client");
        Log.d("clientinActivity",connectedClient.toString());
    }

    public void createOrderMethod(View view){
        EditText destinationInput = (EditText) findViewById(R.id.destinationText);
        String destination = destinationInput.getText().toString();
        EditText startLocactionInput = (EditText) findViewById(R.id.startText);
        String startLocation =startLocactionInput.getText().toString();
        Order order  = new Order(connectedClient.getClientID(),startLocation,destination);
        makeTheCall(order);

    }

    public void deleteAccountMethod(View view){
        makeDeleteOrderCall(connectedClient.getClientID());
    }

    public void logOutMethod(View view){
        finish();
    }

    public void updateClientMethod(View view){
        Intent i = new Intent(this,UpdateClientActivity.class);
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

    private void makeDeleteOrderCall(int id){
        Log.d("deleteClient",connectedClient.toString());
        WebService.getInstance().deleteOrder(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("deleteOrder","deleted");
                makeDeleteCall(connectedClient.getUserId());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("UserErrror", "error on delete order" + t.toString());
            }
        });
    }

    private void makeDeleteCall(int id){
        Log.d("deleteClient",connectedClient.toString());
        WebService.getInstance().deleteClient(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("delete","deleted");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("UserErrror", "error on register order" + t.toString());
            }
        });
    }
    private void makeTheCall(Order order) {
        Log.d("order",order.toString());
        WebService.getInstance().saveOrder(order).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.d("order",response.body().toString());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e("UserErrror", "error on register order" + t.toString());
            }
        });
        Toast.makeText(this,"Order created",Toast.LENGTH_LONG).show();
    }
}