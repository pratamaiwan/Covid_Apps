package com.example.covidapps.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.covidapps.model.Data;

public class SessionManager {
    private String PREF_NAME = "SAVE";
    private String KEY_ID = "id_login";
    private String FULLNAME = "fullname";
    private String EMAIL = "email";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public SessionManager(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setId(String id, String fullname, String email) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(KEY_ID, id).apply();
        mEditor.putString(FULLNAME, fullname).apply();
        mEditor.putString(EMAIL, email).apply();
    }

    public String getId() {
        return mSharedPreferences.getString(KEY_ID, null);
    }

    public String getFULLNAME() {
        return mSharedPreferences.getString(FULLNAME, null);
    }

    public String getEMAIL() {
        return mSharedPreferences.getString(EMAIL, null);
    }


    public void logout() {
        mEditor = mSharedPreferences.edit();
        mEditor.clear().commit();
    }
}