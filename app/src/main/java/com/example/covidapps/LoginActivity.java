package com.example.covidapps;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.covidapps.api.RetroServerLogin;
import com.example.covidapps.databinding.FragmentLoginBinding;
import com.example.covidapps.model.ResponseUsers;
import com.example.covidapps.session.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private FragmentLoginBinding binding;
    ResponseUsers ru = new ResponseUsers();
    SessionManager sm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sm = new SessionManager(this);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), ru.toString(), Toast.LENGTH_SHORT).show();
                if (TextUtils.isEmpty(binding.edUname.getText().toString()) ||
                        TextUtils.isEmpty(binding.edPass.getText().toString())) {
                    String message = "Please input your username and password";
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(binding.edUname.getText().toString(), binding.edPass.getText().toString());
                }
            }
        });
    }

    public void loginUser(String username, String password) {
        RetroServerLogin.getInstance().login(username, password).enqueue(new Callback<ResponseUsers>() {
            @Override
            public void onResponse(@NonNull Call<ResponseUsers> call, @NonNull Response<ResponseUsers> response) {
                ResponseUsers us = response.body();
                //TODO IsStatus if using wrong user id and password crashed with Null Pointer
                try {
                    if (us.isStatus()) {
                        sm.setId(us.getData().getId().toString(), us.getData().getFullName().toString(), us.getData().getEmail().toString());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(LoginActivity.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseUsers> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}


