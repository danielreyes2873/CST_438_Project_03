package com.example.cst_438_project_03;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Flashcards extends AppCompatActivity {
    TextView front;
    TextView hi;
    boolean isFront = true;
    Button back, nextButton, resetBtn;
    String quizName;
    String userName;
    int currentCard = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_flashcard);

        // custom image for action bar end
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));

        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custuom_image, null);
        actionBar.setCustomView(view);
        // custom image for action bar end

        front = findViewById(R.id.frontCard);
        back = findViewById(R.id.backBtn);
        nextButton = findViewById(R.id.nextCardBtn);
        resetBtn = findViewById(R.id.resetBtn);

        resetBtn.setVisibility(View.GONE);

        quizName = getIntent().getStringExtra("quizName").toString();
        userName = getIntent().getStringExtra("userName").toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://quiz-time438.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QuizTimeApi quizTimeApi = retrofit.create(QuizTimeApi.class);

        ObjectAnimator anime_1 = ObjectAnimator.ofFloat(front, "scaleX", 1f, 0f);
        ObjectAnimator anime_2 = ObjectAnimator.ofFloat(front, "scaleX", 0f, 1f);

        //anime_1 = Question Card
        anime_1.setInterpolator(new DecelerateInterpolator());

        //anime_2 = Answer Card
        anime_2.setInterpolator(new AccelerateInterpolator());

        //This is gonna hold all the corresponding questions with the quiz.

        Call<List<Question>> call = quizTimeApi.getQuestions();
        List<Question> target = new ArrayList<>();

        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if(!response.isSuccessful()) {
                    return;
                }

                List<Question> questions = response.body();

                for(Question q : questions) {
                    if(q.getQuizName() != null) {
                        if(q.getQuizName().equals(quizName)) {
                            target.add(q);
                        }
                    }
                }

                if(currentCard < target.size()){
                    front.setText(target.get(currentCard).getQuestion()); //Change this line to QUESTION from database
                    front.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (isFront){
                                anime_1.start();
                                anime_1.addListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
//                            front.setText(correctQuestions.get(1).getAnswer()); //Change this line to ANSWER from database
                                        front.setText(target.get(currentCard).getAnswer()); //Change this line to ANSWER from database
                                        anime_2.start();
                                        isFront = false;

                                        if(!isFront){
                                            anime_2.addListener(new AnimatorListenerAdapter() {
                                                @Override
                                                public void onAnimationStart(Animator animation) {
                                                    super.onAnimationStart(animation);
                                                    front.setText(target.get(currentCard).getQuestion()); //Change this line to QUESTION from database
//                                        front.setText(correctQuestions.get(1).getQuestion()); //Change this line to QUESTION from database
                                                    isFront = true;
                                                }
                                            });
                                        }
                                    }
                                });
                            } else {
                                anime_2.start();
                            }
                        }
                    });
                }
                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        currentCard += 1;
                        if(currentCard < target.size()) {
                            front.setText(target.get(currentCard).getQuestion());
                        } else if(currentCard == target.size() - 1) {
                            resetBtn.setVisibility(View.VISIBLE);
                            Toast.makeText(getApplicationContext(), "Quiz Complete", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "No More questions, Press reset or go back", Toast.LENGTH_LONG).show();
                            resetBtn.setVisibility(View.VISIBLE);
                        }
                    }
                });

                resetBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        currentCard = 0;
                        if(currentCard < target.size()) {
                            front.setText(target.get(currentCard).getQuestion());
                        } else {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                        }
                        resetBtn.setVisibility(View.GONE);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                return;
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Flashcards.this, ViewQuiz.class);
                intent.putExtra("userName", userName);
                startActivity(intent);
            }
        });
    }
}
