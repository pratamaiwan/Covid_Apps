package com.example.covidapps.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Bookmark {

	@PrimaryKey(autoGenerate = true)
	public int uid;

	@SerializedName("continent")
	@ColumnInfo(name = "continent")
	private String continent;

	@SerializedName("country")
	@ColumnInfo(name = "country")
	private String country;

	@SerializedName("recoveredPerOneMillion")
	@ColumnInfo(name = "recoveredPerOneMillion")
	private double recoveredPerOneMillion;

	@SerializedName("cases")
	@ColumnInfo(name = "cases")
	private int cases;

	@SerializedName("critical")
	@ColumnInfo(name = "critical")
	private int critical;

	@SerializedName("oneCasePerPeople")
	@ColumnInfo(name = "oneCasesPerPeople")
	private int oneCasePerPeople;

	@SerializedName("active")
	@ColumnInfo(name = "active")
	private int active;

	@SerializedName("testsPerOneMillion")
	@ColumnInfo(name = "testPerOneMillion")
	private int testsPerOneMillion;

	@SerializedName("population")
	@ColumnInfo(name = "population")
	private int population;

	@SerializedName("oneDeathPerPeople")
	@ColumnInfo(name = "oneDeathPerPeople")
	private int oneDeathPerPeople;

	@SerializedName("recovered")
	@ColumnInfo(name = "recovered")
	private int recovered;

	@SerializedName("oneTestPerPeople")
	@ColumnInfo(name = "oneTestPerPeople")
	private int oneTestPerPeople;

	@SerializedName("tests")
	@ColumnInfo(name = "tests")
	private int tests;

	@SerializedName("criticalPerOneMillion")
	@ColumnInfo(name = "criticalPerOneMillion")
	private double criticalPerOneMillion;

	@SerializedName("deathsPerOneMillion")
	@ColumnInfo(name = "deathsPerOneMillion")
	private int deathsPerOneMillion;

	@SerializedName("todayRecovered")
	@ColumnInfo(name = "todayRecovered")
	private int todayRecovered;

	@SerializedName("casesPerOneMillion")
	@ColumnInfo(name = "casesPerOneMillion")
	private int casesPerOneMillion;

	@SerializedName("updated")
	@ColumnInfo(name = "updated")
	private long updated;

	@SerializedName("deaths")
	@ColumnInfo(name = "deaths")
	private int deaths;

	@SerializedName("activePerOneMillion")
	@ColumnInfo(name = "activePerOneMillion")
	private double activePerOneMillion;

	@SerializedName("todayCases")
	@ColumnInfo(name = "todayCases")
	private int todayCases;

	@SerializedName("todayDeaths")
	@ColumnInfo(name = "todayDeaths")
	private int todayDeaths;
	public void setContinent(String continent){
		this.continent = continent;
	}

	public String getContinent(){
		return continent;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setRecoveredPerOneMillion(double recoveredPerOneMillion){
		this.recoveredPerOneMillion = recoveredPerOneMillion;
	}

	public double getRecoveredPerOneMillion(){
		return recoveredPerOneMillion;
	}

	public void setCases(int cases){
		this.cases = cases;
	}

	public int getCases(){
		return cases;
	}

	public void setCritical(int critical){
		this.critical = critical;
	}

	public int getCritical(){
		return critical;
	}

	public void setOneCasePerPeople(int oneCasePerPeople){
		this.oneCasePerPeople = oneCasePerPeople;
	}

	public int getOneCasePerPeople(){
		return oneCasePerPeople;
	}

	public void setActive(int active){
		this.active = active;
	}

	public int getActive(){
		return active;
	}

	public void setTestsPerOneMillion(int testsPerOneMillion){
		this.testsPerOneMillion = testsPerOneMillion;
	}

	public int getTestsPerOneMillion(){
		return testsPerOneMillion;
	}

	public void setPopulation(int population){
		this.population = population;
	}

	public int getPopulation(){
		return population;
	}

	public void setOneDeathPerPeople(int oneDeathPerPeople){
		this.oneDeathPerPeople = oneDeathPerPeople;
	}

	public int getOneDeathPerPeople(){
		return oneDeathPerPeople;
	}

	public void setRecovered(int recovered){
		this.recovered = recovered;
	}

	public int getRecovered(){
		return recovered;
	}

	public void setOneTestPerPeople(int oneTestPerPeople){
		this.oneTestPerPeople = oneTestPerPeople;
	}

	public int getOneTestPerPeople(){
		return oneTestPerPeople;
	}

	public void setTests(int tests){
		this.tests = tests;
	}

	public int getTests(){
		return tests;
	}

	public void setCriticalPerOneMillion(double criticalPerOneMillion){
		this.criticalPerOneMillion = criticalPerOneMillion;
	}

	public double getCriticalPerOneMillion(){
		return criticalPerOneMillion;
	}

	public void setDeathsPerOneMillion(int deathsPerOneMillion){
		this.deathsPerOneMillion = deathsPerOneMillion;
	}

	public int getDeathsPerOneMillion(){
		return deathsPerOneMillion;
	}

	public void setTodayRecovered(int todayRecovered){
		this.todayRecovered = todayRecovered;
	}

	public int getTodayRecovered(){
		return todayRecovered;
	}

	public void setCasesPerOneMillion(int casesPerOneMillion){
		this.casesPerOneMillion = casesPerOneMillion;
	}

	public int getCasesPerOneMillion(){
		return casesPerOneMillion;
	}

	public void setUpdated(long updated){
		this.updated = updated;
	}

	public long getUpdated(){
		return updated;
	}

	public void setDeaths(int deaths){
		this.deaths = deaths;
	}

	public int getDeaths(){
		return deaths;
	}

	public void setActivePerOneMillion(double activePerOneMillion){
		this.activePerOneMillion = activePerOneMillion;
	}

	public double getActivePerOneMillion(){
		return activePerOneMillion;
	}

	public void setTodayCases(int todayCases){
		this.todayCases = todayCases;
	}

	public int getTodayCases(){
		return todayCases;
	}

	public void setTodayDeaths(int todayDeaths){
		this.todayDeaths = todayDeaths;
	}

	public int getTodayDeaths(){
		return todayDeaths;
	}

	@Override
 	public String toString(){
		return 
			"CountryItem{" + 
			"continent = '" + continent + '\'' + 
			",country = '" + country + '\'' + 
			",recoveredPerOneMillion = '" + recoveredPerOneMillion + '\'' + 
			",cases = '" + cases + '\'' + 
			",critical = '" + critical + '\'' + 
			",oneCasePerPeople = '" + oneCasePerPeople + '\'' + 
			",active = '" + active + '\'' + 
			",testsPerOneMillion = '" + testsPerOneMillion + '\'' + 
			",population = '" + population + '\'' + 
			",oneDeathPerPeople = '" + oneDeathPerPeople + '\'' + 
			",recovered = '" + recovered + '\'' + 
			",oneTestPerPeople = '" + oneTestPerPeople + '\'' + 
			",tests = '" + tests + '\'' + 
			",criticalPerOneMillion = '" + criticalPerOneMillion + '\'' + 
			",deathsPerOneMillion = '" + deathsPerOneMillion + '\'' + 
			",todayRecovered = '" + todayRecovered + '\'' + 
			",casesPerOneMillion = '" + casesPerOneMillion + '\'' +
			",updated = '" + updated + '\'' + 
			",deaths = '" + deaths + '\'' + 
			",activePerOneMillion = '" + activePerOneMillion + '\'' + 
			",todayCases = '" + todayCases + '\'' + 
			",todayDeaths = '" + todayDeaths + '\'' + 
			"}";
		}
}