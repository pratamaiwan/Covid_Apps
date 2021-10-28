package com.example.covidapps.model;

import com.google.gson.annotations.SerializedName;

public class ResponseUsers{

	@SerializedName("data")
	private Data data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private Boolean status;

	@SerializedName("token")
	private String token;

	public Data getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public Boolean isStatus(){
		return status;
	}

	public String getToken(){
		return token;
	}

	@Override
	public String toString() {
		return "ResponseUsers{" +
				"data=" + data +
				", message='" + message + '\'' +
				", status=" + status +
				", token='" + token + '\'' +
				'}';
	}
}