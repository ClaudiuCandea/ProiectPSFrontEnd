package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Model.Car;
import com.example.Model.Order;
import com.example.Service.CarAdapter;
import com.example.Service.OrderAdapter;
import com.example.Service.WebService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderAdminFragment extends Fragment {

    int orderID;

    public OrderAdminFragment() {
        // Required empty public constructor
    }


    public static OrderAdminFragment newInstance(int orderID) {
        OrderAdminFragment fragment = new OrderAdminFragment();
        Bundle args = new Bundle();
        args.putInt("orderID",orderID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            orderID = getArguments().getInt("orderID");
        }
        if(orderID==0){
            makeTheCall();
        }
        else{
            makeTheCallWithID();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_admin, container, false);
    }

    private void setTable(List<Order> list){
        Log.d("list",list.toString());
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerViewOrderAdmin);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<Order> arrayList = new ArrayList<Order>();
        for(Order order: list) {
            arrayList.add(order);
        }
        OrderAdapter orderAdapter = new OrderAdapter(getActivity(),arrayList,0);
        recyclerView.setAdapter(orderAdapter);

    }

    private void makeTheCall() {
        Log.d("call","aici");
        WebService.getInstance().getAllOrders().enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                List<Order> list  = response.body();
                Log.d("ConnectedUser","returend orders");
                setTable(list);

            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.e("getAllOrders", "error get all orders" + t.toString());
            }
        });
    }

    private void makeTheCallWithID() {
        Log.d("call","aici");
        WebService.getInstance().getOrderByID(orderID).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                Order order  = response.body();
                ArrayList<Order> list = new ArrayList<Order>();
                list.add(order);
                Log.d("ConnectedUser","returend order");
                setTable(list);

            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.e("getOrder", "error get  order" + t.toString());
            }
        });
    }
}