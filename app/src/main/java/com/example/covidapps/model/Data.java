package com.example.covidapps.model;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Data{

	public Data(int uid, @Nullable String companyId, @Nullable String lastLogin, @Nullable String dateCreated, @Nullable String avatar, @Nullable String ipAddress, @Nullable String fullName, @Nullable String lastActivity, @Nullable String id, @Nullable String banned, @Nullable String email, @Nullable String username) {
		this.uid = uid;
		this.companyId = companyId;
		this.lastLogin = lastLogin;
		this.dateCreated = dateCreated;
		this.avatar = avatar;
		this.ipAddress = ipAddress;
		this.fullName = fullName;
		this.lastActivity = lastActivity;
		this.id = id;
		this.banned = banned;
		this.email = email;
		this.username = username;
	}

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


	public String getCompanyId(){
		return companyId;
	}

	public String getLastLogin(){
		return lastLogin;
	}

	public String getDateCreated(){
		return dateCreated;
	}

	public String getAvatar(){
		return avatar;
	}

	public String getIpAddress(){
		return ipAddress;
	}

	public String getFullName(){
		return fullName;
	}

	public String getLastActivity(){
		return lastActivity;
	}


	public String getId(){
		return id;
	}

	public String getBanned(){
		return banned;
	}


	public String getEmail(){
		return email;
	}

	public String getUsername(){
		return username;
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