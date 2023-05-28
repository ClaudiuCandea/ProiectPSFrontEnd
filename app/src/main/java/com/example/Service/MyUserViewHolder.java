package com.example.Service;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class MyUserViewHolder extends RecyclerView.ViewHolder {
    TextView userID, password, phone, email,name;
    public MyUserViewHolder(@NonNull View itemView) {
        super(itemView);
        userID = itemView.findViewById(R.id.userIDField);
        password = itemView.findViewById(R.id.passwordUserField);
        phone = itemView.findViewById(R.id.phoneUserField);
        email = itemView.findViewById(R.id.emailUserField);
        name = itemView.findViewById(R.id.nameUser);
    }
}
