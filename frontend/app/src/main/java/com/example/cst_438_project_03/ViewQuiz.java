package com.example.cst_438_project_03;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewQuiz extends  AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quiz);

        TextView quizlist = (TextView) findViewById(R.id.quizlist);
        quizlist.setText("All Quizzes:");
        quizlist.setBackgroundColor(Color.parseColor("#bacfbf"));

        //display all quizzes

    }

}
