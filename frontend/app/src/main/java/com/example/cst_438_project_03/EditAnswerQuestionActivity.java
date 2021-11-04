package com.example.cst_438_project_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EditAnswerQuestionActivity extends AppCompatActivity {

    Button editAnsQueSave;
    Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_answer_question);

        editAnsQueSave = (Button) findViewById(R.id.editAnsQueSave);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);


        editAnsQueSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Information Updated! select Cancel to exit",Toast.LENGTH_LONG).show();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditAnswerQuestionActivity.this, TempPageActivity.class);
                startActivity(intent);
            }
        });
    }
}