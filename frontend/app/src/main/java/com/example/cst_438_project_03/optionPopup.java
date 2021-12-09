package com.example.cst_438_project_03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class optionPopup extends Activity {

    Button deleteBtn;
    Button fcBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_popup);
        deleteBtn = findViewById(R.id.deleteBtn);
        fcBtn = findViewById(R.id.fcBtn);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.6));


        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Set will be deleted soon.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(optionPopup.this, ViewQuiz.class);
                startActivity(intent);
            }
        });

        fcBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(optionPopup.this, Flashcards.class);
                startActivity(intent);
            }
        });

    }
}
