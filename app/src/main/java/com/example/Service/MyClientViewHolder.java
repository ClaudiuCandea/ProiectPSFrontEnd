package com.example.Service;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class MyClientViewHolder extends RecyclerView.ViewHolder {

    TextView clientID, userID, password, phone, email,name ,card;
    public MyClientViewHolder(@NonNull View itemView) {
        super(itemView);
        Log.d("in holder","aici");
        clientID = itemView.findViewById(R.id.clientIDField);
        userID = itemView.findViewById(R.id.userIDField);
        password = itemView.findViewById(R.id.passwordClientField);
        phone = itemView.findViewById(R.id.phoneClientField);
        email = itemView.findViewById(R.id.emailClientField);
        name = itemView.findViewById(R.id.nameClientField);
        card = itemView.findViewById(R.id.cardField);
    }
}
