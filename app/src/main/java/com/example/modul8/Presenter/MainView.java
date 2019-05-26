package com.example.modul8.Presenter;

import com.example.modul8.Model.getId.GetIdResponse;
import com.example.modul8.Model.get.GetResponse;

/*
Nama :  Ika Husni...
NIM  :  1231700...
*/
public interface MainView {
    void getSucces(GetResponse list);
    void setToast(String message);
    void onError(String errorMessage);
    void onFailure(String failureMessage);
    void getSuccess(GetIdResponse list);
}
