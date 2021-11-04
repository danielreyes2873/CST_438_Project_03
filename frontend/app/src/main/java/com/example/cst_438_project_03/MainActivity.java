package com.example.cst_438_project_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button testAPIButton;
    Button btnCreateAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testAPIButton = (Button) findViewById(R.id.apiButton);
        btnCreateAcc = (Button) findViewById(R.id.btnCreateAcc);

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
                Intent intent = new Intent(MainActivity.this, apiTest.class);
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