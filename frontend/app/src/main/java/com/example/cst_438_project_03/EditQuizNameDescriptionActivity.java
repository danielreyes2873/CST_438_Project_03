package com.example.cst_438_project_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EditQuizNameDescriptionActivity extends AppCompatActivity {

    Button saveQuizNameDesc;
    Button cancelBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_quiz_name_description);

        saveQuizNameDesc = (Button) findViewById(R.id.saveQuizNameDesc);
        cancelBtn2 = (Button) findViewById(R.id.cancelBtn2);


        saveQuizNameDesc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Information Will be Updated Soon! select Cancel to exit...",Toast.LENGTH_LONG).show();
            }
        });

        cancelBtn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditQuizNameDescriptionActivity.this, ViewQuiz.class);
                startActivity(intent);
            }
        });
    }


}