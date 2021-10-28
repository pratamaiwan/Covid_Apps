package com.example.covidapps.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private String PREF_NAME = "SAVE";
    private String KEY_ID = "id_login";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public SessionManager(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setId(String id) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(KEY_ID, id).apply();
    }

    public String getId() {
        return mSharedPreferences.getString(KEY_ID, null);
    }

    public void logout() {
        mEditor = mSharedPreferences.edit();
        mEditor.clear().commit();
    }
}