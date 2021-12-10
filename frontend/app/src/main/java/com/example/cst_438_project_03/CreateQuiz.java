package com.example.cst_438_project_03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

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
        String username = getIntent().getStringExtra("username").toString();

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

                Call<List<Quiz>> call = quizTimeApi.getQuizzes();

                call.enqueue(new Callback<List<Quiz>>() {
                    @Override
                    public void onResponse(Call<List<Quiz>> call, Response<List<Quiz>> response) {
                        if(!response.isSuccessful()){
                            System.out.println("Code: " + response.code());
                            return;
                        }

                        List<Quiz> quizzes = response.body();
                        for(Quiz quiz: quizzes){
                            if(quiz.getName().equals(name)){
                                dupError();
                                return;
                            }
                        }

                        createQuiz(name,description,username);

                        Intent intent = new Intent(CreateQuiz.this, QandA.class);
                        intent.putExtra("username",username);
                        intent.putExtra("quizName", name);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<List<Quiz>> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });

            }
        });
    }

    private void createQuiz(String name, String description, String username) {
        Call<Quiz> call = quizTimeApi.createQuiz(name,description,username);
        call.enqueue(new Callback<Quiz>() {
            @Override
            public void onResponse(Call<Quiz> call, Response<Quiz> response) {
                if(!response.isSuccessful()) {
                    System.out.println("Code: " + response.code());
                }
                System.out.println("New Quiz Created: " + name);
                confirmation();
            }

            @Override
            public void onFailure(Call<Quiz> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    private void confirmation() {
        Toast.makeText(this, "Quiz Created!", Toast.LENGTH_SHORT).show();
    }

    private void dupError() {
        System.out.println("dup error");
        Toast.makeText(this,"Quiz Name already taken",Toast.LENGTH_SHORT).show();
    }
}
