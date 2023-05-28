package com.example.Service;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class MyCarViewHolder extends RecyclerView.ViewHolder {

    TextView carId, driverID ,registration, model, producer, noTakenOrders;

    public MyCarViewHolder(@NonNull View itemView) {
        super(itemView);
        carId = itemView.findViewById(com.example.myapplication.R.id.carID);
        driverID = itemView.findViewById(com.example.myapplication.R.id.carDriverIDField);
        registration = itemView.findViewById(com.example.myapplication.R.id.registrationNumberField);
        model = itemView.findViewById(com.example.myapplication.R.id.modelField);
        producer = itemView.findViewById(com.example.myapplication.R.id.producerField);
        noTakenOrders = itemView.findViewById(com.example.myapplication.R.id.numberTakenOrdersCarField);

    }
}
