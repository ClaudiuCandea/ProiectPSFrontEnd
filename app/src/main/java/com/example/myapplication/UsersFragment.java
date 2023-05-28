package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Model.Driver;
import com.example.Model.User;
import com.example.Service.DriverAdapter;
import com.example.Service.UserAdapter;
import com.example.Service.WebService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsersFragment extends Fragment {

    private int userID;

    public UsersFragment() {
        // Required empty public constructor
    }


    public static UsersFragment newInstance(int userID) {
        UsersFragment fragment = new UsersFragment();
        Bundle args = new Bundle();
        args.putInt("userID",userID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle args = getArguments();
            this.userID = args.getInt("userID",0);
        }
        if(userID==0){
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
        return inflater.inflate(R.layout.fragment_users, container, false);
    }


    private void setTable(List<User> list){
        Log.d("list",list.toString());
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerViewUser);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<User> arrayList = new ArrayList<User>();
        for(User user : list){
            arrayList.add(user);
        }
        UserAdapter userAdapter = new UserAdapter(arrayList,getActivity());
        recyclerView.setAdapter(userAdapter);

    }

    private void makeTheCall() {
        Log.d("call","aici");
        WebService.getInstance().getAllUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> list  = response.body();
                Log.d("ConnectedUser","returend users");
                setTable(list);

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("getAllUsers", "error get all users" + t.toString());
            }
        });
    }

    private void makeTheCallWithID() {
        Log.d("call","aici");
        WebService.getInstance().getUserById(userID).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user  = response.body();
                ArrayList<User> list = new ArrayList<User>();
                list.add(user);
                Log.d("ConnectedUser","returend user");
                setTable(list);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("getUser", "error get  user" + t.toString());
            }
        });
    }
}