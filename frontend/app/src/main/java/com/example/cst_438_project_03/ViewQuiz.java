package com.example.cst_438_project_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
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
    private Button backBtn;
    String userName;
    String quizName;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quiz);
        userName = getIntent().getStringExtra("userName").toString();

        // custom image for action bar end
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));

        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custuom_image, null);
        actionBar.setCustomView(view);
        // custom image for action bar end

        // User Information
//        if(getIntent().getStringExtra("firstName") == (null)) {
//            firstName = "missing FN";
//        } else {
//            firstName = getIntent().getStringExtra("firstName").toString();
//        }
//
//        if (getIntent().getStringExtra("lastName") == (null)){
//            lastName = "missing LN";
//        } else {
//            lastName = getIntent().getStringExtra("lastName").toString();
//        }

        // Variables
//        backBtn = findViewById(R.id.backBtn);
        quizlist = (TextView) findViewById(R.id.quizlist);
        quizlist.setText("Click any quiz:\n\n");

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
                    content += "Created By: " + quiz.getUserID() + "\n\n";
                    quizlist.append(content);

                    quizlist.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(ViewQuiz.this, optionPopup.class);
                            intent.putExtra("quizName", quiz.getName());
                            intent.putExtra("userName", userName);
                            startActivity(intent);
                        }
                    });
                }

//                backBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(ViewQuiz.this, TempPageActivity.class);
////                        intent.putExtra("userName", username);
////                        intent.putExtra("password", password);
////                        intent.putExtra("fistName", firstName);
////                        intent.putExtra("lastName", lastName);
//                        startActivity(intent);
//                    }
//                });

            }

            @Override
            public void onFailure(Call<List<Quiz>> call, Throwable t) {
                quizlist.setText(t.getMessage());
            }
        });
    }
}
