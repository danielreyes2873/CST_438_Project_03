package com.example.cst_438_project_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class user_edit_profile extends AppCompatActivity {

    EditText userEditUserNameInput;
    EditText userEditProfileNewPassword;
    EditText userEditProfileNewPasswordRetype;
    EditText userEditProfileNewFirstName;
    EditText userEditProfileNewLastName;
    Button updateProfileBtn;
    Button cancelBtn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit_profile);

        // custom image for action bar end
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));

        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custuom_image, null);
        actionBar.setCustomView(view);
        // custom image for action bar end

        // Get user information
        String userName = getIntent().getStringExtra("userName").toString();
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

        // variables
        userEditUserNameInput = (EditText) findViewById(R.id.userEditUserNameInput);
        userEditProfileNewPassword = (EditText) findViewById(R.id.userEditProfileNewPassword);
        userEditProfileNewPasswordRetype = (EditText) findViewById(R.id.userEditProfileNewPasswordRetype);
        updateProfileBtn = (Button) findViewById(R.id.updateProfileBtn);
        cancelBtn6 = (Button) findViewById(R.id.cancelBtn6);

        // Hints
        userEditUserNameInput.setHint(userName);
        //userEditProfileNewFirstName.setText(firstName);
        //userEditProfileNewLastName.setText(lastName);

        // User no long wanted to update information
        cancelBtn6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user_edit_profile.this, UserViewProfileActivity.class);
                intent.putExtra("userName", userName);
                intent.putExtra("password", password);
                intent.putExtra("fistName", firstName);
                intent.putExtra("lastName", lastName);
                startActivity(intent);
            }
        });

        // NEEDS TO BE UPDATED!!! - User wanted to update their information
        updateProfileBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                int changesMade = 0;

                // check if username has been changed
                if(userEditUserNameInput.getText().toString().trim().length() != 0){
                    if((userEditUserNameInput.getText().toString()).equals(userName)){
                        // username is the same (no change)
                        Toast.makeText(getApplicationContext(),"Same Username Entered - NO CHANGES MADE",Toast.LENGTH_LONG).show();
                    } else {
                        // check for username requirements & display that changes have been made
                        changesMade =+ 1;
//                      Toast.makeText(getApplicationContext(),"New Username Entered - CHANGES WILL BE MADE SOON",Toast.LENGTH_LONG).show();
                    }
                }

                // check if new passwords match
                if(userEditProfileNewPassword.getText().toString().trim().length() != 0){
                    //if same password is being passed in
                    if((userEditProfileNewPassword.getText().toString()).equals(password)){
                        Toast.makeText(getApplicationContext(),"Same Password Entered - NO CHANGES MADE",Toast.LENGTH_LONG).show();
                    } else if ((userEditProfileNewPassword.getText().toString()).equals(userEditProfileNewPasswordRetype.getText().toString())) {
                        // check if new password and retyped passwords match
                        changesMade += 1;
//                      Toast.makeText(getApplicationContext(),"Passwords match! - CHANGES WILL BE MADE SOON",Toast.LENGTH_LONG).show();
                    } else {
                        // passwords do not match or incomplete
                        Toast.makeText(getApplicationContext(),"Passwords entered do not match!",Toast.LENGTH_LONG).show();
                    }
                }

                if(changesMade > 0){
                    Toast.makeText(getApplicationContext(),"CHANGES WILL BE MADE SOON",Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                            public void run() {
                                Intent intent = new Intent(user_edit_profile.this, UserViewProfileActivity.class);
                                intent.putExtra("userName", userName);
                                intent.putExtra("password", password);
                                intent.putExtra("fistName", firstName);
                                intent.putExtra("lastName", lastName);
                                startActivity(intent);
                            }
                        }, 5000);   //5 seconds
                }

            }
        });
    }
}