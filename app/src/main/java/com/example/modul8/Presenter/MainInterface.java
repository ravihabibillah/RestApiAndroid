package com.example.modul8.Presenter;

/*
Nama :  Ramanda Walbari...
NIM  :  1231700...
*/
public interface MainInterface {
    void getAllItems();
    void updateItems(String id, String name, String description);
    void deleteItems(String id);
    void createItems(String name, String description);
    void getByID(String id);
}
