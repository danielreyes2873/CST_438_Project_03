package com.example.cst_438_project_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button testAPIButton;
    Button btnCreateAcc;
    Button tempBtn;
    Button btnLogin;
    TextView mainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testAPIButton = (Button) findViewById(R.id.apiButton);
        btnCreateAcc = (Button) findViewById(R.id.btnCreateAcc);
        tempBtn = (Button) findViewById(R.id.tempBtn);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        String mainText2 = "Welcome to Quiz Time! We aim to allow users to create custom quizzes to make studying easier. " +
                "Quizzes, once made, can be viewed by anyone but only edited by the user who made it. Create an account today and start learning!";

        mainText = (TextView) findViewById(R.id.mainText);
        mainText.setText(mainText2);

        tempBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TempPageActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });

        btnCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateAcc.class);
                startActivity(intent);
            }
        });

        testAPIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, usersTest.class);
                startActivity(intent);
            }
        });
    }

//    public void onClick(View v) {
//        if(v.getId() == R.id.btnCreateAcc){
//            Intent i = new Intent(this, CreateAcc.class);
//            startActivity(i);
//
//        }
//    }


}