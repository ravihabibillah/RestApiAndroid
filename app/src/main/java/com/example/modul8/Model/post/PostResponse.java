package com.example.modul8.Model.post;

import com.google.gson.annotations.SerializedName;

public class PostResponse{

	@SerializedName("data")
	private Data data;

	@SerializedName("errors")
	private Object errors;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setErrors(Object errors){
		this.errors = errors;
	}

	public Object getErrors(){
		return errors;
	}

	@Override
 	public String toString(){
		return 
			"PostResponse{" + 
			"data = '" + data + '\'' + 
			",errors = '" + errors + '\'' + 
			"}";
		}
}