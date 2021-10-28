package com.example.covidapps.model;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Data{

	@PrimaryKey(autoGenerate = true)
	public int uid;

	@SerializedName("company_id")
	@Nullable
	@ColumnInfo(name = "company_id")
	private String companyId;

	@SerializedName("last_login")
	@Nullable
	@ColumnInfo(name = "last_login")
	private String lastLogin;

	@SerializedName("date_created")
	@Nullable
	@ColumnInfo(name = "date_created")
	private String dateCreated;

	@SerializedName("avatar")
	@Nullable
	@ColumnInfo(name = "avatar")
	private String avatar;

	@SerializedName("ip_address")
	@Nullable
	@ColumnInfo(name = "ip_address")
	private String ipAddress;


	@SerializedName("full_name")
	@Nullable
	@ColumnInfo(name = "full_name")
	private String fullName;

	@SerializedName("last_activity")
	@Nullable
	@ColumnInfo(name = "last_activity")
	private String lastActivity;


	@SerializedName("id")
	@Nullable
	@ColumnInfo(name = "id")
	private String id;

	@SerializedName("banned")
	@Nullable
	@ColumnInfo(name = "banned")
	private String banned;

	@SerializedName("email")
	@Nullable
	@ColumnInfo(name = "email")
	private String email;

	@SerializedName("username")
	@Nullable
	@ColumnInfo(name = "username")
	private String username;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	@Nullable
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(@Nullable String companyId) {
		this.companyId = companyId;
	}

	@Nullable
	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(@Nullable String lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Nullable
	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(@Nullable String dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Nullable
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(@Nullable String avatar) {
		this.avatar = avatar;
	}

	@Nullable
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(@Nullable String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Nullable
	public String getFullName() {
		return fullName;
	}

	public void setFullName(@Nullable String fullName) {
		this.fullName = fullName;
	}

	@Nullable
	public String getLastActivity() {
		return lastActivity;
	}

	public void setLastActivity(@Nullable String lastActivity) {
		this.lastActivity = lastActivity;
	}

	@Nullable
	public String getId() {
		return id;
	}

	public void setId(@Nullable String id) {
		this.id = id;
	}

	@Nullable
	public String getBanned() {
		return banned;
	}

	public void setBanned(@Nullable String banned) {
		this.banned = banned;
	}

	@Nullable
	public String getEmail() {
		return email;
	}

	public void setEmail(@Nullable String email) {
		this.email = email;
	}

	@Nullable
	public String getUsername() {
		return username;
	}

	public void setUsername(@Nullable String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Data{" +
				", companyId='" + companyId + '\'' +
				", lastLogin='" + lastLogin + '\'' +
				", dateCreated='" + dateCreated + '\'' +
				", avatar='" + avatar + '\'' +
				", ipAddress='" + ipAddress + '\'' +
				", fullName='" + fullName + '\'' +
				", lastActivity='" + lastActivity + '\'' +
				", id='" + id + '\'' +
				", banned='" + banned + '\'' +
				", email='" + email + '\'' +
				", username='" + username + '\'' +
				'}';
	}
}