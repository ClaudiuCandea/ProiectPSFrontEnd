package com.example.Model;

public interface Observer {
    /**
     * Method to modify the number of take orders in the observer objects.
     * @param noTakenOrders
     */
    public void update(int noTakenOrders);
}
