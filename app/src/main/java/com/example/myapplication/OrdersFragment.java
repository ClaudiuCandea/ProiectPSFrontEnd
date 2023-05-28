package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.Model.Driver;
import com.example.Model.Order;
import com.example.Model.User;
import com.example.Service.OnClickListener;
import com.example.Service.OrderAdapter;
import com.example.Service.WebService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrdersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrdersFragment extends Fragment {


    private int driverID;

    public OrdersFragment() {
        // Required empty public constructor
    }

    public static OrdersFragment newInstance(int driverID) {
        OrdersFragment fragment = new OrdersFragment();
        Bundle args = new Bundle();
        args.putInt("driverID",driverID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle args = getArguments();
            this.driverID = args.getInt("driverID",0);
        }
        makeTheCall();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_orders, container, false);
    }

    private void setTable(List<Order> list){
        Log.d("list",list.toString());
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<Order> arrayList = new ArrayList<Order>();
        for(Order order: list){
            if(order.getDriverID()==0){
                arrayList.add(order);
            }
        }
        OrderAdapter orderAdapter = new OrderAdapter(getActivity(),arrayList,driverID);
        recyclerView.setAdapter(orderAdapter);
        orderAdapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(int position, Order model, int driverID) {
                Log.d("call","aici");
                model.setDriverID(driverID);
                WebService.getInstance().updateOrder(model).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        int returned  = response.body();
                        Log.d("AfterUpdateOrder","returned key"+returned);
                        makeTheCallWithID();

                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Log.e("updapteOrder", "error updateOrder" + t.toString());
                    }
                });
            }
        });

    }

    private void makeTheCallWithID() {
        Log.d("call","aici");
        WebService.getInstance().getDriverByID(driverID).enqueue(new Callback<Driver>() {
            @Override
            public void onResponse(Call<Driver> call, Response<Driver> response) {
                Driver driver  = response.body();
                driver.setNoTakenOrders(driver.getNoTakenOrders()+1);
                makeTheDriverCall(driver);
            }

            @Override
            public void onFailure(Call<Driver> call, Throwable t) {
                Log.e("getDriver", "error get  driver" + t.toString());
            }
        });
    }

    private void makeTheDriverCall(Driver driver) {
        Log.d("call","aici");
        WebService.getInstance().updateDriver(driver).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.d("UpdateDriver","returned "+ response.body());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e("UpdateError", "error on driverUpdate" + t.toString());
            }
        });
        Toast.makeText(getContext(),"Accepted order",Toast.LENGTH_LONG).show();
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
}