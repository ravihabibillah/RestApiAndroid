package com.example.modul8.Presenter;

import com.example.modul8.Model.get.GetResponse;

public interface MainView {
    void getSucces(GetResponse list);
    void setToast(String message);
    void onError(String errorMessage);
    void onFailure(String failureMessage);
}
