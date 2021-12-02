package com.example.cst_438_project_03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CreateAcc extends AppCompatActivity implements View.OnClickListener {
    View btnCreateAcc;
    EditText etUsername;
    EditText etPassword;
    String username;
    String password;
    QuizTimeApi quizTimeApi;
    String dupError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        btnCreateAcc = findViewById(R.id.btnCreateAcc);
        btnCreateAcc.setOnClickListener(this);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

    }

    public void onClick(View v) {
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://quiz-time438.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        quizTimeApi = retrofit.create(QuizTimeApi.class);

        Call<List<Users>> call = quizTimeApi.getUsers();

        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if(!response.isSuccessful()){
                    System.out.println("Code: " + response.code());
                    return;
                }

                List<Users> users = response.body();
                for(Users user: users){
                    if(user.getUsername().equals(username)){
                        dupError();
                        return;
                    }
                }

                cAcc();

            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public static boolean isUsernameEmpty(String u) {
        return u.length()==0;
    }
    public static boolean isPasswordEmpty(String p) {
        return p.length()==0;
    }

    public static boolean hasInput(String u, String p) {
        if (isUsernameEmpty(u) && isPasswordEmpty(p)){
            return false;
        } else if (isUsernameEmpty(u) || isPasswordEmpty(p)) {
            return true;
        } else if (isUsernameEmpty(u)) {
            return false;
        } else if (isPasswordEmpty(p)) {
            return false;
        } else {
            return true;
        }
    }

    private void dupError() {
        System.out.println("dup error");
        Toast.makeText(this,"Username already taken",Toast.LENGTH_SHORT).show();
    }

    private void createAccount(String username, String password) {
        Call<Users> call = quizTimeApi.createAccount(username, password);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (!response.isSuccessful()) {
                    etUsername.setText("Code: " + response.code());
                    return;
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                etUsername.setText(t.getMessage());
            }
        });
    }

    public void cAcc() { // name is shortened for "create account"
        if (hasInput(username, password)) {
            if (isUsernameEmpty(username) && !isPasswordEmpty(password)) {
                Toast.makeText(this, "Username empty", Toast.LENGTH_SHORT).show();
            } else if (!isUsernameEmpty(username) && isPasswordEmpty(password)) {
                Toast.makeText(this, "Password empty", Toast.LENGTH_SHORT).show();
            } else {
                System.out.println("account created");
                createAccount(username, password);
                Toast.makeText(this, "New Account Created!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }
        } else {
            Toast.makeText(this, "Username and password empty", Toast.LENGTH_SHORT).show();
        }
    }

}
