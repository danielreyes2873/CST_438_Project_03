package com.example.cst_438_project_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TempPageActivity extends AppCompatActivity {

    Button editAnsQues;
    Button editQuizNameDesc;
    Button viewProfile;
    Button logoutBtn;
    Button createQuiz;
    Button viewQuiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_page);

        editAnsQues = findViewById(R.id.editAnsQues);
        editQuizNameDesc = findViewById(R.id.editQuizNameDesc);
        viewProfile = findViewById(R.id.viewProfile);
        logoutBtn = findViewById(R.id.logoutBtn);
        createQuiz = findViewById(R.id.createQuiz);
        viewQuiz = findViewById(R.id.viewQuiz);

        createQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TempPageActivity.this, CreateQuiz.class);
                startActivity(intent);
            }
        });

        viewQuiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(TempPageActivity.this, ViewQuiz.class);
                startActivity(intent);
            }
        });

        editAnsQues.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TempPageActivity.this, EditAnswerQuestionActivity.class);
                startActivity(intent);
            }
        });

        editQuizNameDesc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TempPageActivity.this, EditQuizNameDescriptionActivity.class);
                startActivity(intent);
            }
        });

        viewProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TempPageActivity.this, UserViewProfileActivity.class);
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TempPageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}