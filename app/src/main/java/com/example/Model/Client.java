package com.example.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Client extends User implements Serializable {
    @SerializedName("clientID")
    private int clientID;
    @SerializedName("cardNumber")
    private String cardNumber;

    public Client( String userName, String userEmail, String userPhone, String userPassword, String userType, String cardNumber) {
        super(1, userName, userEmail, userPhone, userPassword, userType);
        this.clientID = 1;
        this.cardNumber = cardNumber;
    }
    public Client(){
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        super.toString();
        return "Client{" +
                "clientID=" + clientID +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
