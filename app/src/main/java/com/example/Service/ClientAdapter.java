package com.example.Service;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Model.Client;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ClientAdapter extends RecyclerView.Adapter<MyClientViewHolder> {

    ArrayList<Client> list;
    Context context;

    public ClientAdapter(ArrayList<Client> list, Context context) {
        Log.d("constrc",list.toString());
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.client_item_layout,parent,false);
        Log.d("in adapter","aici");
        return new MyClientViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyClientViewHolder holder, int position) {
        Client client = list.get(position);
        Log.d("in adapter","aici");
        holder.userID.setText(String.valueOf(client.getUserId()));
        holder.clientID.setText(String.valueOf(client.getClientID()));
        holder.phone.setText(client.getUserPhone());
        holder.password.setText(client.getUserPassword());
        holder.name.setText(client.getUserName());
        holder.email.setText(client.getUserEmail());
        holder.card.setText(client.getCardNumber());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
