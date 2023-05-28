package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Model.Client;
import com.example.Model.Driver;
import com.example.Service.ClientAdapter;
import com.example.Service.DriverAdapter;
import com.example.Service.WebService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DriversFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DriversFragment extends Fragment {




    private int driverID;

    public DriversFragment() {
        // Required empty public constructor
    }
    public static DriversFragment newInstance(int driverID) {
        DriversFragment fragment = new DriversFragment();
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
        if(driverID==0){
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
        return inflater.inflate(R.layout.fragment_drivers, container, false);
    }

    private void setTable(List<Driver> list){
        Log.d("list",list.toString());
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerViewDriver);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<Driver> arrayList = new ArrayList<Driver>();
        for(Driver driver: list){
            arrayList.add(driver);
        }
        DriverAdapter driverAdapter = new DriverAdapter(arrayList,getActivity());
        recyclerView.setAdapter(driverAdapter);

    }

    private void makeTheCall() {
        Log.d("call","aici");
        WebService.getInstance().getAllDrivers().enqueue(new Callback<List<Driver>>() {
            @Override
            public void onResponse(Call<List<Driver>> call, Response<List<Driver>> response) {
                List<Driver> list  = response.body();
                Log.d("ConnectedUser","returend drivers");
                setTable(list);

            }

            @Override
            public void onFailure(Call<List<Driver>> call, Throwable t) {
                Log.e("getAllClients", "error get all clients" + t.toString());
            }
        });
    }

    private void makeTheCallWithID() {
        Log.d("call","aici");
        WebService.getInstance().getDriverByID(driverID).enqueue(new Callback<Driver>() {
            @Override
            public void onResponse(Call<Driver> call, Response<Driver> response) {
                Driver driver  = response.body();
                ArrayList<Driver> list = new ArrayList<Driver>();
                list.add(driver);
                Log.d("ConnectedUser","returend client");
                setTable(list);

            }

            @Override
            public void onFailure(Call<Driver> call, Throwable t) {
                Log.e("getDriver", "error get  driver" + t.toString());
            }
        });
    }
}