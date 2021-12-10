package com.example.cst_438_project_03;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class Flashcards extends AppCompatActivity {
    TextView front;
    TextView hi;
    boolean isFront = true;
    Button back;
    String quizName;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
        front = findViewById(R.id.frontCard);
        back = findViewById(R.id.backBtn);

        quizName = getIntent().getStringExtra("quizName").toString();
        userName = getIntent().getStringExtra("userName").toString();

        ObjectAnimator anime_1 = ObjectAnimator.ofFloat(front, "scaleX", 1f, 0f);
        ObjectAnimator anime_2 = ObjectAnimator.ofFloat(front, "scaleX", 0f, 1f);

        //anime_1 = Question Card
        anime_1.setInterpolator(new DecelerateInterpolator());

        //anime_2 = Answer Card
        anime_2.setInterpolator(new AccelerateInterpolator());

        front.setText("Question"); //Change this line to QUESTION from database

        front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFront){
                    anime_1.start();
                    anime_1.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            front.setText("Answer"); //Change this line to ANSWER from database
                            anime_2.start();
                            isFront = false;

                            if(!isFront){
                                anime_2.addListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {
                                        super.onAnimationStart(animation);
                                        front.setText("Question"); //Change this line to QUESTION from database
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
