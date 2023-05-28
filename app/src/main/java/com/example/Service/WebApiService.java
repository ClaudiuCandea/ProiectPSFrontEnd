package com.example.Service;

import com.example.Model.Car;
import com.example.Model.Client;
import com.example.Model.Driver;
import com.example.Model.Order;
import com.example.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface WebApiService {
    @GET("/getUser")
    Call<User> getUserById(@Query("id")int id);

    @GET("/getUsers")
    Call<List<User>> getAllUsers();

    @GET("/getUserByEmail")
    Call<User> getUserByEmail(@Query("email") String email);

    @PUT("/update")
    Call<Void> updateUser(@Body User use);

    @POST("/post")
    Call<Void> saveUser(@Body User user);

    @DELETE("/delete")
    Call<Void>  deleteUser(@Query("id") int id);

    @GET("/getDriver")
    Call<Driver> getDriverByID(@Query("driverID") int driverID);

    @GET("/getDriverByUserID")
    Call<Driver> getDriverByUserID(@Query("userID") int userID);

    @GET("/GetAllDrivers")
    Call<List<Driver>> getAllDrivers();

    @POST("/saveDriver")
    Call<Integer> saveDriver(@Body Driver driver);

    @PUT("/updateDriver")
    Call<Integer> updateDriver(@Body Driver driver);

    @DELETE("/deleteDriver")
    Call<Void> deleteDriver(@Query("userID") int userID);

    @GET("/getClient")
    Call<Client> getClientByID(@Query("clientID") int clientID);

    @GET("/getClientByUserID")
    Call<Client> getClientByUserID(@Query("userID") int userID);

    @GET("/getAllClients")
    Call<List<Client>> getAllClients();

    @POST("/saveClient")
    Call<Void> saveClient(@Body Client client);

    @PUT("/updateClient")
    Call<Integer> updateClient(@Body Client client);

    @DELETE("/deleteClient")
    Call<Void> deleteClient(@Query("userID") int userID);

    @GET("/getCar")
    Call<Car> getCarById(@Query("carID") int carID);

    @GET("/GetAllCars")
    Call<List<Car>> getAllCars();

    @POST("/saveCar")
    Call<Integer> saveCar(@Body Car car);

    @PUT("/updateCar")
    Call<Integer> updateCar(@Body Car car);

    @DELETE("/deleteCar")
    Call<Void> deleteCar(@Query("carID")int carID);

    @GET("/getOrder")
    Call<Order> getOrderByID(@Query("orderID") int orderID);

    @POST("/saveOrder")
    Call<Integer> saveOrder(@Body Order order);

    @DELETE("/deleteOrder")
    Call<Void> deleteOrder(@Query("client_id")int clientID);
    @DELETE("/deleteOrderDriver")
    Call<Void> deleteOrderDriver(@Query("driver_id")int driverID);

    @GET("/getAllOrders")
    Call<List<Order>> getAllOrders();

    @PUT("/updateOrder")
    Call<Integer> updateOrder(@Body Order order);
}
