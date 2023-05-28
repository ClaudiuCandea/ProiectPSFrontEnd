package com.example.Model;

import com.google.gson.annotations.SerializedName;

public class Car implements Observer{
    @SerializedName("id")
    private int carID;
    @SerializedName("producer")
    private String producer;
    @SerializedName("model")
    private String model;
    @SerializedName("registration_number")
    private String registrationNumber;
    @SerializedName("driverID")
    private int driverID;
    @SerializedName("noTakenOrders")
    private int noTakenOrders;

    public Car(int carID, String producer, String model, String registrationNumber, int driverID) {
        this.carID = carID;
        this.producer = producer;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.driverID = driverID;
        noTakenOrders = 0;
    }

    public Car(){

    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    @Override
    public void update(int noTakenOrders) {
        this.noTakenOrders =noTakenOrders;
    }

    public int getNoTakenOrders() {
        return noTakenOrders;
    }

    public void setNoTakenOrders(int noTakenOrders) {
        this.noTakenOrders = noTakenOrders;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carID=" + carID +
                ", producer='" + producer + '\'' +
                ", model='" + model + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", driverID=" + driverID +
                ", noTakenOrders=" + noTakenOrders +
                '}';
    }
}
