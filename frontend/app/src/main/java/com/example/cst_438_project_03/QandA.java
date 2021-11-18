package com.example.cst_438_project_03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class QandA extends AppCompatActivity{
    EditText question;
    EditText answer;
    Button add;
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa);
        question = findViewById(R.id.question);
        answer = findViewById(R.id.answer);
        add = findViewById(R.id.add);
        home = findViewById(R.id.home);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save to question and and answer database
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QandA.this, TempPageActivity.class);
                startActivity(intent);
            }
        });
    }
}
