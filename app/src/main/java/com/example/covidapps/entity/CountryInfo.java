package com.example.covidapps.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class CountryInfo{

	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "uid")
	public int uid;

	@SerializedName("country")
	@ColumnInfo(name = "country")
	private String country;

	@SerializedName("flag")
	@ColumnInfo(name = "flag")
	private String flag;

	@SerializedName("_id")
	@ColumnInfo(name = "id")
	private int id;

	@SerializedName("iso2")
	@ColumnInfo(name = "iso2")
	private String iso2;

	@SerializedName("lat")
	@ColumnInfo(name = "lat")
	private Double lat;

	@SerializedName("long")
	@ColumnInfo(name = "long")
	private Double jsonMemberLong;

	@SerializedName("iso3")
	@ColumnInfo(name = "iso3")
	private String iso3;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

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

	public void setLat(Double lat){
		this.lat = lat;
	}

	public Double getLat(){
		return lat;
	}

	public void setJsonMemberLong(Double jsonMemberLong){
		this.jsonMemberLong = jsonMemberLong;
	}

	public Double getJsonMemberLong(){
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