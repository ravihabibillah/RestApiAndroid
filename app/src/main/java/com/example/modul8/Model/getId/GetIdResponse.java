package com.example.modul8.Model.getId;

import com.example.modul8.Model.post.Data;
import com.google.gson.annotations.SerializedName;


/*
Nama :  M.Ravi Habibillah
NIM  :  123170039
*/
public class GetIdResponse {
    @SerializedName("data")
    private DataId data;

    @SerializedName("errors")
    private Object errors;

    public void setData(DataId data) {
        this.data = data;
    }

    public DataId getData() {
        return data;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    public Object getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return
                "GetIdResponse{" +
                        "data = '" + data + '\'' +
                        ",errors = '" + errors + '\'' +
                        "}";
    }
}
