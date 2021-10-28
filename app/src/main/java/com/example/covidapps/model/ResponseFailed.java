package com.example.covidapps.model;

import com.google.gson.annotations.SerializedName;

public class ResponseFailed{

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}