package com.example.cst_438_project_03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class CreateQuiz extends AppCompatActivity{
    EditText name;
    EditText description;
    EditText question;
    EditText answer;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        name = findViewById(R.id.name);
        description = findViewById(R.id.desc);
        add = findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save to quiz set database
                Intent intent = new Intent(CreateQuiz.this, QandA.class);
                startActivity(intent);
            }
        });
    }


}
