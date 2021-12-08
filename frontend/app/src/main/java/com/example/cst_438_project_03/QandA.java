package com.example.cst_438_project_03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QandA extends AppCompatActivity{
    EditText question;
    EditText answer;
    Button add;
    Button home;
    QuizTimeApi quizTimeApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa);
        question = findViewById(R.id.question);
        answer = findViewById(R.id.answer);
        add = findViewById(R.id.add);
        home = findViewById(R.id.home);
        String quizName = getIntent().getStringExtra("quizName").toString();
        String username = getIntent().getStringExtra("username").toString();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save to question and and answer database
                String ques = question.getText().toString();
                String ans = answer.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://quiz-time438.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                quizTimeApi = retrofit.create(QuizTimeApi.class);

                addQuestion(ques,ans,quizName);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QandA.this, TempPageActivity.class);
                intent.putExtra("userName",username);
                startActivity(intent);
            }
        });
    }

    private void addQuestion(String question, String answer, String quizName) {
        Call<Question> call = quizTimeApi.addQuestion(question, answer, quizName);
        call.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                if(!response.isSuccessful()) {
                    System.out.println("Code: " + response.code());
                }
                System.out.println("New Question added: " + question);
                confirmation();
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    private void confirmation() {
        Toast.makeText(this, "Question Added!", Toast.LENGTH_SHORT).show();
    }
}
