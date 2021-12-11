package com.example.cst_438_project_03;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.usb.UsbRequest;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class usersTest extends AppCompatActivity {

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_test);

        textViewResult = findViewById(R.id.textResult);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://quiz-time438.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QuizTimeApi quizTimeApi = retrofit.create(QuizTimeApi.class);

//        getUsers(quizTimeApi);
//        textViewResult.append("\n");
//        getQuizzes(quizTimeApi);
//        textViewResult.append("\n");
//        getQuestions(quizTimeApi);
        updateUser(quizTimeApi);
    }

    private void getUsers(QuizTimeApi quizTimeApi) {
        Call<List<Users>> call = quizTimeApi.getUsers();

        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Users> users = response.body();

                for(Users user: users){
                    String content = "";
                    content += "Username: " + user.getUsername() + "\n";
                    content += "Password: " + user.getPassword() + "\n\n";
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getQuizzes(QuizTimeApi quizTimeApi) {
        Call<List<Quiz>> call = quizTimeApi.getQuizzes();

        call.enqueue(new Callback<List<Quiz>>() {
            @Override
            public void onResponse(Call<List<Quiz>> call, Response<List<Quiz>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Quiz> Quizzes = response.body();

            for(Quiz quiz: Quizzes){
                    String content = "";
                    content += "Name: " + quiz.getName() + "\n";
                    content += "Description: " + quiz.getDescription() + "\n";
                    content += "ID: " + quiz.getUserID() + "\n\n";
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Quiz>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getQuestions(QuizTimeApi quizTimeApi) {
        Call<List<Question>> call = quizTimeApi.getQuestions();

        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Question> Questions = response.body();

                for(Question question: Questions){
                    String content = "";
                    content += "Question: " + question.getQuestion() + "\n";
                    content += "Answer: " + question.getAnswer() + "\n";
                    content += "ID: " + question.getQuizName() + "\n\n";
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void updateUser(QuizTimeApi quizTimeApi) {
        Users user = new Users("updated", "123", "up", "date");

        Call<Users> call = quizTimeApi.putUser(1, user);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                Users users = response.body();

                    String content = "";
                    content += "Code: " + response.code() + "\n";
                    content += "Username: " + user.getUsername() + "\n";
                    content += "Password: " + user.getPassword() + "\n\n";
                    textViewResult.append(content);

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}