package com.example.Service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.Model.Driver;
import com.example.myapplication.R;

import java.util.ArrayList;

public class DriverAdapter extends RecyclerView.Adapter<MyDriverViewHolder> {
    ArrayList<Driver> list;
    Context context;

    public DriverAdapter(ArrayList<Driver> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyDriverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.driver_item_layout,parent,false);
        return new MyDriverViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyDriverViewHolder holder, int position) {
       Driver driver = list.get(position);
        holder.userID.setText(String.valueOf(driver.getUserId()));
        holder.driverID.setText(String.valueOf(driver.getDriverID()));
        holder.phone.setText(driver.getUserPhone());
        holder.password.setText(driver.getUserPassword());
        holder.name.setText(driver.getUserName());
        holder.email.setText(driver.getUserEmail());
        holder.noTakenOrders.setText(String.valueOf(driver.getNoTakenOrders()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
