package com.example.cst_438_project_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    Button testAPIButton;
    Button btnCreateAcc;
    Button btnLogin;
    TextView mainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // custom image for action bar end
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));

        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custuom_image, null);
        actionBar.setCustomView(view);
        // custom image for action bar end

        // Variables
        testAPIButton = (Button) findViewById(R.id.apiButton);
        btnCreateAcc = (Button) findViewById(R.id.btnCreateAcc);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        String mainText2 = "Welcome to Quiz Time! We aim to allow users to create custom quizzes to make studying easier. " +
                "Quizzes, once made, can be viewed by anyone but only edited by the user who made it. Create an account today and start learning!";
        mainText = (TextView) findViewById(R.id.mainText);
        mainText.setText(mainText2);

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
}