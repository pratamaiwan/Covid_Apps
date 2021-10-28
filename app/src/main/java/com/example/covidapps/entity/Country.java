package com.example.covidapps.entity;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Country{

	@SerializedName("Country")
	private List<CountryItem> country;

	public void setCountry(List<CountryItem> country){
		this.country = country;
	}

	public List<CountryItem> getCountry(){
		return country;
	}

	@Override
 	public String toString(){
		return 
			"Country{" + 
			"country = '" + country + '\'' + 
			"}";
		}
}