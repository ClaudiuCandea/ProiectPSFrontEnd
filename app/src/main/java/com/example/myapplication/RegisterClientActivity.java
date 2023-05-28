package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
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

public class RegisterClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client);
    }

    public void registerClientMethod(View view){
        EditText name = (EditText)findViewById(R.id.nameText);
        String nameClient = name.getText().toString();
        EditText email = (EditText)findViewById(R.id.emailClientText);
        String emailClient = email.getText().toString();
        EditText phone = (EditText)findViewById(R.id.phoneText);
        String phoneClient = phone.getText().toString();
        EditText password = (EditText)findViewById(R.id.passwordClientText);
        String passwordClient = password.getText().toString();
        EditText card = (EditText) findViewById(R.id.cardText);
        String cardClient = card.getText().toString();
        Client client = new Client(nameClient,emailClient,phoneClient,passwordClient,"client",cardClient);
        makeTheCall(client);

    }

    private void makeTheCall(Client client) {
        Log.d("call","aici");
        WebService.getInstance().saveClient(client).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("dsdas","hello");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("UserErrror", "error on log in" + t.toString());
            }
        });
        Toast.makeText(this,"Registred",Toast.LENGTH_LONG).show();
    }


}