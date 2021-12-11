package com.example.cst_438_project_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TempPageActivity extends AppCompatActivity {

    //Button editAnsQues;
    Button viewProfile;
    Button logoutBtn;
    Button createQuiz;
    Button viewQuiz;
    TextView desc1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_page);

        // custom image for action bar end
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));

        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custuom_image, null);
        actionBar.setCustomView(view);
        // custom image for action bar end

        // User information
        String username = getIntent().getStringExtra("userName").toString();
        String password = getIntent().getStringExtra("password").toString();
        String firstName, lastName;

        if(getIntent().getStringExtra("firstName") == (null)) {
            firstName = "missing FN";
        } else {
            firstName = getIntent().getStringExtra("firstName").toString();
        }

        if (getIntent().getStringExtra("lastName") == (null)){
            lastName = "missing LN";
        } else {
            lastName = getIntent().getStringExtra("lastName").toString();
        }

        //editAnsQues = findViewById(R.id.editAnsQues);
        viewProfile = findViewById(R.id.viewProfile);
        logoutBtn = findViewById(R.id.logoutBtn);
        createQuiz = findViewById(R.id.createQuiz);
        viewQuiz = findViewById(R.id.viewQuiz);


        String desc2 = "Your thinking and learning space.     Organize your thinking. Master your craft. Grow your knowledge.";
        desc1 = (TextView) findViewById(R.id.desc1);
        desc1.setText(desc2);

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
//                intent.putExtra("userName", username);
//                intent.putExtra("password", password);
//                intent.putExtra("fistName", firstName);
//                intent.putExtra("lastName", lastName);
                startActivity(intent);
            }
        });

//        editAnsQues.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(TempPageActivity.this, EditAnswerQuestionActivity.class);
//                startActivity(intent);
//            }
//        });

        viewProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TempPageActivity.this, UserViewProfileActivity.class);
                intent.putExtra("userName", username);
                intent.putExtra("password", password);
                intent.putExtra("fistName", firstName);
                intent.putExtra("lastName", lastName);
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TempPageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
      );
    }
}