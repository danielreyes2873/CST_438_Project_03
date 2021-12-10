package com.example.cst_438_project_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TempPageActivity extends AppCompatActivity {

    Button editAnsQues;
    Button viewProfile;
    Button logoutBtn;
    Button createQuiz;
    Button viewQuiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_page);
        String username = getIntent().getStringExtra("userName").toString();
        editAnsQues = findViewById(R.id.editAnsQues);
        viewProfile = findViewById(R.id.viewProfile);
        logoutBtn = findViewById(R.id.logoutBtn);
        createQuiz = findViewById(R.id.createQuiz);
        viewQuiz = findViewById(R.id.viewQuiz);

        createQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TempPageActivity.this, CreateQuiz.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        viewQuiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(TempPageActivity.this, ViewQuiz.class);
                intent.putExtra("userName", username);
                startActivity(intent);
            }
        });

        editAnsQues.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TempPageActivity.this, EditAnswerQuestionActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });


//        viewProfile.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(TempPageActivity.this, UserViewProfileActivity.class);
//                startActivity(intent);
//            }
//        });

//        logoutBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(TempPageActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        }
//      );
    }
}