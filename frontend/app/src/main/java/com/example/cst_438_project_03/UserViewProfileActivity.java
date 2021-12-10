package com.example.cst_438_project_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserViewProfileActivity extends AppCompatActivity {

    Button userEditProfileBtn;
    Button cancelBtn3;

    TextView description;
    TextView pwdDescription;
    TextView userFirstName;
    TextView userLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_profile);

        String firstName, lastName;

        String userName = getIntent().getStringExtra("userName").toString();
        String password = getIntent().getStringExtra("password").toString();

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


        description = (TextView) findViewById(R.id.userUserName);
        description.setText(userName);

        // Display password length in asterisks
        int pwdLen = password.length();
        String pwdDisplay = "";

        for(int i=0; i < pwdLen; i++){
            pwdDisplay = pwdDisplay + "*";
        }

        pwdDescription = (TextView) findViewById(R.id.userPassword);
        pwdDescription.setText(pwdDisplay);

        // Display first and last name
        userFirstName = (TextView) findViewById(R.id.userFirstName);
        userLastName = (TextView) findViewById(R.id.userLastName);
        userFirstName.setText(firstName);
        userLastName.setText(lastName);



        userEditProfileBtn = (Button) findViewById(R.id.userEditProfileBtn);
        cancelBtn3 = (Button) findViewById(R.id.cancelBtn3);

        userEditProfileBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"User Edit profile page under construction.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UserViewProfileActivity.this, user_edit_profile.class);
                intent.putExtra("userName", userName);
                intent.putExtra("password", password);
                intent.putExtra("fistName", firstName);
                intent.putExtra("lastName", lastName);
                startActivity(intent);
            }
        });

        cancelBtn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserViewProfileActivity.this, TempPageActivity.class);
                intent.putExtra("userName", userName);
                startActivity(intent);
            }
        });
    }
}