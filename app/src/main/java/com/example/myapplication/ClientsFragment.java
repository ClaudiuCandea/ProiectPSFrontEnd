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
import com.example.Service.ClientAdapter;
import com.example.Service.WebService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClientsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClientsFragment extends Fragment {

    private int clientID;

    public ClientsFragment() {
        // Required empty public constructor
    }

    public static ClientsFragment newInstance(int clientID) {
        ClientsFragment fragment = new ClientsFragment();
        Bundle args = new Bundle();
        args.putInt("clientID",clientID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle args = getArguments();
            this.clientID = args.getInt("clientID",0);
        }
        if(clientID==0){
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
        return inflater.inflate(R.layout.fragment_clients, container, false);
    }

    private void setTable(List<Client> list){
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerViewClient);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<Client> arrayList = new ArrayList<Client>();
        for(Client client : list){
            arrayList.add(client);
        }
        Log.d("list",arrayList.toString());
        ClientAdapter clientAdapter = new ClientAdapter(arrayList,getActivity());
        Log.d("list",arrayList.toString());
        recyclerView.setAdapter(clientAdapter);

    }

    private void makeTheCall() {
        Log.d("call","aici");
        WebService.getInstance().getAllClients().enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                List<Client> list  = response.body();
                Log.d("ConnectedUser","returend clients");
                setTable(list);

            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Log.e("getAllClients", "error get all clients" + t.toString());
            }
        });
    }

    private void makeTheCallWithID() {
        Log.d("call","aici");
        WebService.getInstance().getClientByID(clientID).enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
              Client client  = response.body();
              ArrayList<Client> list = new ArrayList<Client>();
              list.add(client);
              Log.d("ConnectedUser","returend client");
              setTable(list);

            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Log.e("getClient", "error get  client" + t.toString());
            }
        });
    }
}