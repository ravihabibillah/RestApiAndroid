package com.example.modul8.Remote;

import android.app.Application;

import com.example.modul8.BuildConfig;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
Nama :  M.Ravi Habibillah
NIM  :  123170039
*/

// class yang berguna untuk menyambung kan API

public class BaseApp extends Application {
    public static ApiService service;
    private String url = "https://api-barang.herokuapp.com";
    @Override
    public void onCreate(){
        super.onCreate();
        service =getRetrofit().create(ApiService.class);
    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getHttpClient())
                .build();
    }

    private OkHttpClient getHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor (getHttpLogInterceptor())
                .build();
    }

    private Interceptor getHttpLogInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

        HttpLoggingInterceptor.Level level;
        if(BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.BODY;
        } else {
            level = HttpLoggingInterceptor.Level.NONE;
        }
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }
}
