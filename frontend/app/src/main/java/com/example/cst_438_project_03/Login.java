package com.example.cst_438_project_03;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity implements View.OnClickListener {
    View btnLogin;
    EditText etUsername;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // custom image for action bar end
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));

        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custuom_image, null);
        actionBar.setCustomView(view);
        // custom image for action bar end

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

    }

    public void onClick(View v){
        verifyLogin(etUsername.getText().toString(), etPassword.getText().toString());
    }


    private void verifyLogin(String userName, String password){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://quiz-time438.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QuizTimeApi quizTimeApi = retrofit.create(QuizTimeApi.class);

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
                    if(user.getUsername().equals(userName)){
                        if(user.getPassword().equals(password)){
                            Intent j = new Intent(Login.this, TempPageActivity.class);
                            j.putExtra("userName", userName);
                            j.putExtra("password", password);
                            j.putExtra("firstName", user.getFirstname());
                            j.putExtra("lastName", user.getLastname());
                            startActivity(j);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}

