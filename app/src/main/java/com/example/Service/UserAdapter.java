package com.example.Service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Model.Driver;
import com.example.Model.User;
import com.example.myapplication.R;
import com.example.myapplication.UsersFragment;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<MyUserViewHolder>{
    ArrayList<User> list;
    Context context;

    public UserAdapter(ArrayList<User> list,Context context){
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public MyUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.user_item_layout,parent,false);
        return new MyUserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyUserViewHolder holder, int position) {
        User user = list.get(position);
        holder.userID.setText(String.valueOf(user.getUserId()));
        holder.phone.setText(user.getUserPhone());
        holder.password.setText(user.getUserPassword());
        holder.name.setText(user.getUserName());
        holder.email.setText(user.getUserEmail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
