package com.example.cst_438_project_03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Login extends AppCompatActivity implements View.OnClickListener {
    View btnLogin;
    EditText etUsername;
    EditText etPassword;

    String[] tempUsernames = new String[]{
            "Admin",
            "Test",
            "Test2"
    };
    String[] tempPasswords = new String[]{
            "123",
            "test",
            "test2"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    public void onClick(View v){
        verifyLogin(etUsername.getText().toString(), etPassword.getText().toString());
    }

    private void verifyLogin(String userName, String password){
        for(int i = 0; i < tempUsernames.length; i++){
            if(userName.equals(tempUsernames[i])){
                if(password.equals((tempPasswords[i]))){
                    Intent j = new Intent(this, UserViewProfileActivity.class);
                    j.putExtra("userName", userName);
                    j.putExtra("password", password);
                    startActivity(j);
                }
            }
        }
    }
}

