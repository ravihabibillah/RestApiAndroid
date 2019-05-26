package com.example.modul8.Presenter;

import com.example.modul8.Model.getId.GetIdResponse;
import com.example.modul8.Model.get.GetResponse;
import com.example.modul8.Model.post.PostResponse;
import com.example.modul8.Remote.BaseApp;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
Nama :  M.Ravi Habibillah
NIM  :  123170039
*/
public class MainPresenter implements MainInterface {

    private MainView mainView;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    //Mengambil Seluruh Data melalui API
    @Override
    public void getAllItems() {
        BaseApp.service.getAllItems().enqueue(new Callback<GetResponse>() {
            @Override
            public void onResponse(Call<GetResponse> call, Response<GetResponse> response) {
                if (response.isSuccessful())
                    mainView.getSucces(response.body());
                else
                    mainView.onError(response.message());

            }

            @Override
            public void onFailure(Call<GetResponse> call, Throwable t) {
                mainView.onFailure(t.getMessage());
            }
        });
    }

    //Mengambil Data berdasarkan ID melalui API
    @Override
    public void getByID(String id) {
        BaseApp.service.getById(id).enqueue(new Callback<GetIdResponse>() {
            @Override
            public void onResponse(Call<GetIdResponse> call, Response<GetIdResponse> response2) {
                if (response2.isSuccessful())
                    mainView.getSuccess(response2.body());
                else
                    mainView.onError(response2.message());

            }

            @Override
            public void onFailure(Call<GetIdResponse> call, Throwable t) {
                mainView.onFailure(t.getMessage());
            }
        });
    }

    //Mengupdate Data berdasarkan ID melalui API
    @Override
    public void updateItems(String id, String name, String description) {
        BaseApp.service.updateDataItems(id, name, description).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    mainView.setToast(response.message());
                } else {
                    mainView.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                mainView.onFailure(t.getMessage());
            }
        });
    }

    //Menghapus Data berdasarkan ID melalui API
    @Override
    public void deleteItems(String id) {
        BaseApp.service.deleteDataItems(id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    mainView.setToast(response.message());
                } else {
                    mainView.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                mainView.onFailure(t.getMessage());
            }
        });
    }


    //Menambahkan Data melalui API
    @Override
    public void createItems(String name, String description) {
        BaseApp.service.createItems(name, description).enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                if (response.isSuccessful())
                    mainView.setToast(response.message());
                else
                    mainView.onError(response.message());

            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                mainView.onFailure(t.getMessage());
            }
        });
    }
}