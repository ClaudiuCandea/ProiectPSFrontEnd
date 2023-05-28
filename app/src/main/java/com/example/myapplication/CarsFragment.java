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
import com.example.Model.User;
import com.example.Service.CarAdapter;
import com.example.Service.UserAdapter;
import com.example.Service.WebService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarsFragment extends Fragment {

    private int carID;

    public CarsFragment() {
        // Required empty public constructor
    }


    public static CarsFragment newInstance(int carID) {
        CarsFragment fragment = new CarsFragment();
        Bundle args = new Bundle();
        args.putInt("carID",carID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           carID = getArguments().getInt("userID",0);
        }
        if(carID==0){
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
        return inflater.inflate(R.layout.fragment_cars, container, false);
    }

    private void setTable(List<Car> list){
        Log.d("list",list.toString());
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerViewCar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<Car> arrayList = new ArrayList<Car>();
        for(Car car: list){
            arrayList.add(car);
        }
        CarAdapter carAdapter = new CarAdapter(arrayList,getActivity());
        recyclerView.setAdapter(carAdapter);

    }

    private void makeTheCall() {
        Log.d("call","aici");
        WebService.getInstance().getAllCars().enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                List<Car> list  = response.body();
                Log.d("ConnectedUser","returend cars");
                setTable(list);

            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
                Log.e("getAllCars", "error get all cars" + t.toString());
            }
        });
    }

    private void makeTheCallWithID() {
        Log.d("call","aici");
        WebService.getInstance().getCarById(carID).enqueue(new Callback<Car>() {
            @Override
            public void onResponse(Call<Car> call, Response<Car> response) {
                Car car  = response.body();
                ArrayList<Car> list = new ArrayList<Car>();
                list.add(car);
                Log.d("ConnectedUser","returend car");
                setTable(list);

            }

            @Override
            public void onFailure(Call<Car> call, Throwable t) {
                Log.e("getUser", "error get  user" + t.toString());
            }
        });
    }
}