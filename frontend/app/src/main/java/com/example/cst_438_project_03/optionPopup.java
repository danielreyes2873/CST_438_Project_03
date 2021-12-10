package com.example.cst_438_project_03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class optionPopup extends Activity {

    Button deleteBtn;
    Button fcBtn;
    String userName;
    String quizName;
    QuizTimeApi quizTimeApi;
    int quizID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_popup);
        deleteBtn = findViewById(R.id.deleteBtn);
        fcBtn = findViewById(R.id.fcBtn);
        quizName = getIntent().getStringExtra("quizName").toString();
        System.out.println(quizName);
        userName = getIntent().getStringExtra("userName").toString();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.6));

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
                int i = 0;
                for(Quiz quiz: quizzes){
                    i++;
                    if(quiz.getName().equals(quizName)){
                        quizID = i;
                        System.out.println(quizID);
                        return;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Quiz>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });


        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                deleteQuiz(quizID);
                Toast.makeText(getApplicationContext(),"Quiz Deleted!",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(optionPopup.this, ViewQuiz.class);
                intent.putExtra("userName", userName);
                startActivity(intent);
            }
        });

        fcBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(optionPopup.this, Flashcards.class);
                intent.putExtra("quizName", quizName);
                intent.putExtra("userName", userName);
                startActivity(intent);
            }
        });

    }

    private void deleteQuiz(int quizID) {
        Call<Void> call = quizTimeApi.deleteQuiz(quizID);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("Code: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}
