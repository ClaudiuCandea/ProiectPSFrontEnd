package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameAdmin,fragment);
        fragmentTransaction.commit();
    }

    public void showClients(View view){
        Fragment fragment = ClientsFragment.newInstance(0);
        replaceFragment(fragment);
    }

    public void showDrivers(View view){
        Fragment fragment = DriversFragment.newInstance(0);
        replaceFragment(fragment);
    }

    public void showUsers(View view){
        Fragment fragment = UsersFragment.newInstance(0);
        replaceFragment(fragment);
    }
    public void showCars(View view){
        Fragment fragment = CarsFragment.newInstance(0);
        replaceFragment(fragment);
    }
    public void showOrders(View view){
        Fragment fragment = OrderAdminFragment.newInstance(0);
        replaceFragment(fragment);
    }
    public void showClientByID(View view){
        EditText editText = (EditText) findViewById(R.id.fieldForID);
        String idS = editText.getText().toString();
        int id = Integer.parseInt(idS);
        Fragment fragment = ClientsFragment.newInstance(id);
        replaceFragment(fragment);
    }
    public void showDriverByID(View view){
        EditText editText = (EditText) findViewById(R.id.fieldForID);
        String idS = editText.getText().toString();
        int id = Integer.parseInt(idS);
        Fragment fragment = DriversFragment.newInstance(id);
        replaceFragment(fragment);
    }
    public void showUserByID(View view){
        EditText editText = (EditText) findViewById(R.id.fieldForID);
        String idS = editText.getText().toString();
        int id = Integer.parseInt(idS);
        Fragment fragment = UsersFragment.newInstance(id);
        replaceFragment(fragment);
    }
    public void showCarByID(View view){
        EditText editText = (EditText) findViewById(R.id.fieldForID);
        String idS = editText.getText().toString();
        int id = Integer.parseInt(idS);
        Fragment fragment = CarsFragment.newInstance(id);
        replaceFragment(fragment);
    }
    public void showOrderByID(View view){
        EditText editText = (EditText) findViewById(R.id.fieldForID);
        String idS = editText.getText().toString();
        int id = Integer.parseInt(idS);
        Fragment fragment = OrderAdminFragment.newInstance(id);
        replaceFragment(fragment);
    }
}