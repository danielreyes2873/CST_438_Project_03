package com.example.cst_438_project_03;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewQuiz extends  AppCompatActivity{
    private TextView quizlist;
    private Button cardBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quiz);

        cardBtn = findViewById(R.id.cardBtn);

        quizlist = (TextView) findViewById(R.id.quizlist);
        quizlist.setText("All Quizzes:");
        quizlist.setBackgroundColor(Color.parseColor("#bacfbf"));

        //display all quizzes
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://quiz-time438.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QuizTimeApi quizTimeApi = retrofit.create(QuizTimeApi.class);
        getQuizzes(quizTimeApi);

    }

    private void getQuizzes(QuizTimeApi quizTimeApi) {
        Call<List<Quiz>> call = quizTimeApi.getQuizzes();

        call.enqueue(new Callback<List<Quiz>>() {
            @Override
            public void onResponse(Call<List<Quiz>> call, Response<List<Quiz>> response) {
                if(!response.isSuccessful()){
                    quizlist.setText("Code: " + response.code());
                    return;
                }

                List<Quiz> Quizzes = response.body();

                for(Quiz quiz: Quizzes){
                    String content = "";
                    content += "Name: " + quiz.getName() + "\n";
                    content += "Description: " + quiz.getDescription() + "\n";
                    content += "ID: " + quiz.getUserID() + "\n\n";
                    quizlist.append(content);
                }

                cardBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ViewQuiz.this, Flashcards.class);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Quiz>> call, Throwable t) {
                quizlist.setText(t.getMessage());
            }
        });
    }
}
