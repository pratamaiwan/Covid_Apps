package com.example.covidapps.entity;

import com.google.gson.annotations.SerializedName;

public class CountryInfo{

	@SerializedName("flag")
	private String flag;

	@SerializedName("_id")
	private int id;

	@SerializedName("iso2")
	private String iso2;

	@SerializedName("lat")
	private int lat;

	@SerializedName("long")
	private int jsonMemberLong;

	@SerializedName("iso3")
	private String iso3;

	public void setFlag(String flag){
		this.flag = flag;
	}

	public String getFlag(){
		return flag;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setIso2(String iso2){
		this.iso2 = iso2;
	}

	public String getIso2(){
		return iso2;
	}

	public void setLat(int lat){
		this.lat = lat;
	}

	public int getLat(){
		return lat;
	}

	public void setJsonMemberLong(int jsonMemberLong){
		this.jsonMemberLong = jsonMemberLong;
	}

	public int getJsonMemberLong(){
		return jsonMemberLong;
	}

	public void setIso3(String iso3){
		this.iso3 = iso3;
	}

	public String getIso3(){
		return iso3;
	}

	@Override
 	public String toString(){
		return 
			"CountryInfo{" + 
			"flag = '" + flag + '\'' + 
			",_id = '" + id + '\'' + 
			",iso2 = '" + iso2 + '\'' + 
			",lat = '" + lat + '\'' + 
			",long = '" + jsonMemberLong + '\'' + 
			",iso3 = '" + iso3 + '\'' + 
			"}";
		}
}