package com.example.cst_438_project_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserViewProfileActivity extends AppCompatActivity {

    Button userEditProfileBtn;
    Button cancelBtn3;

    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_profile);

        String userName = getIntent().getStringExtra("userName").toString();
        String password = getIntent().getStringExtra("password").toString();

        description = (TextView) findViewById(R.id.textView6);
        description.setText(userName);
        description.append("\n");
        description.append(password);

        userEditProfileBtn = (Button) findViewById(R.id.userEditProfileBtn);
        cancelBtn3 = (Button) findViewById(R.id.cancelBtn3);

        userEditProfileBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"User Edit profile page under construction.",Toast.LENGTH_LONG).show();
            }
        });

        cancelBtn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserViewProfileActivity.this, TempPageActivity.class);
                startActivity(intent);
            }
        });
    }
}