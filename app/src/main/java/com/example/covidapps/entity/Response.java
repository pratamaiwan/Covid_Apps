package com.example.covidapps.entity;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("Response")
	private List<Country> response;

	public List<Country> getResponse(){
		return response;
	}
}