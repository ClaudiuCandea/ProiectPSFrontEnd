package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Model.Client;
import com.example.Model.User;
import com.example.Service.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateClientActivity extends AppCompatActivity {
    Client connectedClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_client);
        connectedClient = new Client();
        Intent i = getIntent();
        connectedClient.setClientID(i.getIntExtra("clientID",0));
        connectedClient.setUserId(i.getIntExtra("userID",0));
        connectedClient.setCardNumber(i.getStringExtra("card"));
        connectedClient.setUserEmail(i.getStringExtra("email"));
        connectedClient.setUserPassword(i.getStringExtra("password"));
        connectedClient.setUserName(i.getStringExtra("name"));
        connectedClient.setUserPhone(i.getStringExtra("phone"));
        connectedClient.setUserType("client");
        EditText passwordText = (EditText) findViewById(R.id.passwordClientTextUpdate);
        EditText nameText = (EditText) findViewById(R.id.nameTextUpdate);
        EditText phoneText = (EditText) findViewById(R.id.phoneTextUpdate);
        EditText card = (EditText) findViewById(R.id.cardTextUpdate);
        EditText email = (EditText) findViewById(R.id.emailClientTextUpdate);
        passwordText.setText(connectedClient.getUserPassword());
        nameText.setText(connectedClient.getUserName());
        phoneText.setText(connectedClient.getUserPhone());
        card.setText(connectedClient.getCardNumber());
        email.setText(connectedClient.getUserEmail());
    }

    public void updateClientMethod(View view){
        EditText passwordText = (EditText) findViewById(R.id.passwordClientTextUpdate);
        connectedClient.setUserPassword(passwordText.getText().toString());
        EditText nameText = (EditText) findViewById(R.id.nameTextUpdate);
        connectedClient.setUserName(nameText.getText().toString());
        EditText phoneText = (EditText) findViewById(R.id.phoneTextUpdate);
        connectedClient.setUserPhone(phoneText.getText().toString());
        EditText card = (EditText) findViewById(R.id.cardTextUpdate);
        connectedClient.setCardNumber(card.getText().toString());
        EditText email = (EditText) findViewById(R.id.emailClientTextUpdate);
        connectedClient.setUserEmail(email.getText().toString());
        makeTheCall(connectedClient);

    }

    private void makeTheCall(Client client) {
        Log.d("call","aici");
        WebService.getInstance().updateClient(client).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.d("UpdateClient","returned "+ response.body());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e("UserErrror", "error on log in" + t.toString());
            }
        });
        Toast.makeText(this,"Updated",Toast.LENGTH_LONG).show();
    }
}