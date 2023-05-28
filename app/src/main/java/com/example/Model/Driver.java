package com.example.Model;

import com.google.gson.annotations.SerializedName;

public class Driver extends User{
    @SerializedName("driverID")
    private int driverID;
    @SerializedName("observer")
    private Observer observer;
    @SerializedName("noTakenOrders")
    private int noTakenOrders;

    public Driver(int userId, String userName, String userEmail, String userPhone, String userPassword, String userType, int driverID) {
        super(userId, userName, userEmail, userPhone, userPassword, userType);
        this.driverID = driverID;
        noTakenOrders = 0;
    }
    public Driver(){
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public Observer getObserver() {
        return observer;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public int getNoTakenOrders() {
        return noTakenOrders;
    }

    public void setNoTakenOrders(int noTakenOrders) {
        this.noTakenOrders = noTakenOrders;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverID=" + driverID +
                ", observer=" + observer +
                ", noTakenOrders=" + noTakenOrders +
                '}';
    }
}
