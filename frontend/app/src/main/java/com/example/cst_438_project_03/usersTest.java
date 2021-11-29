package com.example.cst_438_project_03;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.usb.UsbRequest;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class usersTest extends AppCompatActivity {

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_test);

        textViewResult = findViewById(R.id.textResult);

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
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Users> users = response.body();

                for(Users user: users){
                    String content = "";
                    content += "Username: " + user.getUsername() + "\n";
                    content += "Password: " + user.getPassword() + "\n\n";
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}