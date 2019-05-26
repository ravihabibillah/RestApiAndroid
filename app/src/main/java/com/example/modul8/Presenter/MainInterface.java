package com.example.modul8.Presenter;

public interface MainInterface {
    void getAllItems();
    void updateItems(String id, String name, String description);
    void deleteItems(String id);
    void createItems(String name, String description);
    void getByID(String id);
}
