package com.example.Service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Model.Car;
import com.example.myapplication.R;

import java.util.ArrayList;


public class CarAdapter extends RecyclerView.Adapter<MyCarViewHolder> {

    ArrayList<Car> list;
    Context context;

    public CarAdapter(ArrayList<Car> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyCarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.car_item_layout,parent,false);
        return new MyCarViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCarViewHolder holder, int position) {
            Car car = list.get(position);
            holder.carId.setText(String.valueOf(car.getCarID()));
            holder.driverID.setText(String.valueOf(car.getDriverID()));
            holder.noTakenOrders.setText(String.valueOf(car.getNoTakenOrders()));
            holder.registration.setText(car.getRegistrationNumber());
            holder.producer.setText(car.getProducer());
            holder.model.setText(car.getModel());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
