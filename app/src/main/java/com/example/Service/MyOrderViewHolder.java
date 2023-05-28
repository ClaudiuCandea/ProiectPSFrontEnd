package com.example.Service;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.R;

import org.jetbrains.annotations.NotNull;

public class MyOrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView driverID, clientID, orderID, startLocation, destination, date;
    public MyOrderViewHolder(@NotNull View itemView){
        super(itemView);
        driverID = itemView.findViewById(R.id.driverIDField);
        clientID = itemView.findViewById(R.id.clientIDField);
        orderID = itemView.findViewById(R.id.orderIDField);
        startLocation = itemView.findViewById(R.id.startLocationField);
        destination = itemView.findViewById(R.id.destinationField);
        date = itemView.findViewById(R.id.dateField);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
