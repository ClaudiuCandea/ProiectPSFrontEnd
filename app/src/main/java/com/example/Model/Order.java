package com.example.Model;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Order {
    @SerializedName("id")
    private int orderID;
    @SerializedName("clientID")
    private int clientID;
    @SerializedName("driverID")
    private int driverID;
    @SerializedName("startLocation")
    private String startLocation;
    @SerializedName("destinationLocation")
    private String destinationLocation;
    @SerializedName("orderDate")
    private Date orderDate;

    public Order(int clientID, String startLocation, String destinationLocation) {
        this.clientID = clientID;
        this.startLocation = startLocation;
        this.destinationLocation = destinationLocation;
        java.util.Date utilDate = new java.util.Date();
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", clientID=" + clientID +
                ", driverID=" + driverID +
                ", startLocation='" + startLocation + '\'' +
                ", destinationLocation='" + destinationLocation + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }
}
