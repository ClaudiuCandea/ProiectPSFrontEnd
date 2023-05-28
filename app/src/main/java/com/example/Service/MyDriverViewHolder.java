package com.example.Service;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class MyDriverViewHolder extends RecyclerView.ViewHolder {

    TextView driverID, userID, password, phone, email,name ,noTakenOrders;
    public MyDriverViewHolder(@NonNull View itemView) {
        super(itemView);
        driverID = itemView.findViewById(R.id.driverIDField);
        userID = itemView.findViewById(R.id.userIDriverField);
        password = itemView.findViewById(R.id.passwordDriverField);
        phone = itemView.findViewById(R.id.phoneDriverField);
        email = itemView.findViewById(R.id.emailDriverField);
        name = itemView.findViewById(R.id.nameDriver);
        noTakenOrders = itemView.findViewById(R.id.noTakenOrdersDriverField);
    }
}
