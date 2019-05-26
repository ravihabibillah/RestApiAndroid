package com.example.modul8.Remote;

import com.example.modul8.Model.getId.GetIdResponse;
import com.example.modul8.Model.get.GetResponse;
import com.example.modul8.Model.post.PostResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/*
Nama :  Nadia Stef....
NIM  :  1231700...
*/


public interface ApiService {
    @POST("/items")
    Call<PostResponse> createItems(@Query("name") String name,                      // Insert Data ke database
                                   @Query("description") String description);

    @GET("/items")                                                                  // Get all Data dari database
    Call<GetResponse> getAllItems();

    @FormUrlEncoded
    @PUT("/items/{id}")                                                             // Update / Edit data di database
    Call<JsonObject> updateDataItems(@Path("id") String id,
                                     @Field("name") String name,
                                     @Field("description") String description);

    @DELETE("/items/{id}")
    Call<JsonObject> deleteDataItems(@Path("id") String id);                         //Delete data di database

    @GET("/items/{id}")
    Call<GetIdResponse> getById(@Path("id") String id);                               //Mengambil data berdasarkan id

}
