package com.example.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebService {
    private final static String API_URL = "http://10.0.2.2:8080";

    private static WebApiService webApiService;
    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create();

    public static WebApiService getInstance() {
        if (webApiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(provideOkHttp())
                    .build();

            webApiService = retrofit.create(WebApiService.class);
        }
        return webApiService;
    }

    private static OkHttpClient provideOkHttp() {
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        httpBuilder.connectTimeout(30, TimeUnit.SECONDS);

        return httpBuilder.build();
    }
}
