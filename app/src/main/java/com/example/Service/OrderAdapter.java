package com.example.Service;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Model.Order;
import com.example.myapplication.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<MyOrderViewHolder> {
    Context context;
    ArrayList<Order> list;
    private int driverIDSent;

    private OnClickListener onClickListener;


    public OrderAdapter(Context context, ArrayList<Order> list,int driverIDSent) {
        this.context = context;
        this.list = list;
        this.driverIDSent = driverIDSent;
    }

    @NonNull
    @Override
    public MyOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        Log.d("in order adapter","aici");
        return new MyOrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderViewHolder holder, int position) {
        Order order  = list.get(position);
        Log.d("in adapter","aici");
        holder.clientID.setText(String.valueOf(order.getClientID()));
        holder.orderID.setText(String.valueOf(order.getOrderID()));
        holder.driverID.setText(String.valueOf(order.getDriverID()));
        holder.destination.setText(order.getDestinationLocation());
        holder.startLocation.setText(order.getStartLocation());
        holder.date.setText(order.getOrderDate().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(onClickListener != null){
                    onClickListener.onClick(holder.getAdapterPosition(),order,driverIDSent);
                }
            }
        });
    }

    // method to return the
    // position of the item
    @Override
    public int getItemCount() {
        return list.size();
    }

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
