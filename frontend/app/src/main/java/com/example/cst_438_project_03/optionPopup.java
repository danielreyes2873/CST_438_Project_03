package com.example.cst_438_project_03;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class optionPopup extends Activity {

    Button tBtn;
    Button fcBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_popup);
        tBtn = findViewById(R.id.tBtn);
        fcBtn = findViewById(R.id.fcBtn);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.6));


        tBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(optionPopup.this, EditQuizNameDescriptionActivity.class);
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
