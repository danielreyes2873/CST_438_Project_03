package com.example.cst_438_project_03;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface QuizTimeApi {

    @GET("users")
    Call<List<Users>> getUsers();
    @GET("quizzes")
    Call<List<Quiz>> getQuizzes();
    @GET("questions")
    Call<List<Question>> getQuestions();

    @POST("users")
    Call<Users> createAccount(@Body Users user);

    @FormUrlEncoded
    @POST("users/?format=json")
    Call<Users> createAccount(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("quizzes/?format=json")
    Call<Quiz> createQuiz(
            @Field("name") String name,
            @Field("description") String description,
            @Field("userID") int userID
    );
}
