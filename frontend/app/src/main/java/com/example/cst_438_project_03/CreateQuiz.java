package com.example.cst_438_project_03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateQuiz extends AppCompatActivity{
    EditText etName;
    EditText etDescription;
    EditText question;
    EditText answer;
    Button add;
    QuizTimeApi quizTimeApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        etName = findViewById(R.id.name);
        etDescription = findViewById(R.id.desc);
        add = findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save to quiz set database
                String name = etName.getText().toString();
                String description = etDescription.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://quiz-time438.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                quizTimeApi = retrofit.create(QuizTimeApi.class);

                createQuiz(name,description);

                Intent intent = new Intent(CreateQuiz.this, QandA.class);
                startActivity(intent);
            }
        });
    }

    private void createQuiz(String name, String description) {
        Call<Quiz> call = quizTimeApi.createQuiz(name,description,0);
        call.enqueue(new Callback<Quiz>() {
            @Override
            public void onResponse(Call<Quiz> call, Response<Quiz> response) {
                if(!response.isSuccessful()) {
                    System.out.println("Code: " + response.code());
                }
                System.out.println("New Quiz Created: " + name);
            }

            @Override
            public void onFailure(Call<Quiz> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }


}
