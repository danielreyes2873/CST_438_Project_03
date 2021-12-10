package com.example.cst_438_project_03;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

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
            @Field("password") String password,
            @Field("firstname") String firstname,
            @Field("lastname") String lastname
    );

    @FormUrlEncoded
    @POST("quizzes/?format=json")
    Call<Quiz> createQuiz(
            @Field("name") String name,
            @Field("description") String description,
            @Field("username") String username
    );

    @FormUrlEncoded
    @POST("questions/?format=json")
    Call<Question> addQuestion(
            @Field("question") String question,
            @Field("answer") String answer,
            @Field("quizname") String quizName
    );

    @DELETE("quizzes/{id}/?format=json")
    Call<Void> deleteQuiz(@Path("id") int id);

    @PUT("users/{id}/?format=json")
    Call<Users> putUser(@Path("id") int id, @Body Users  user);

    @PATCH("users/{id}/?format=json")
    Call<Users> patchUser(@Path("id") int id, @Body Users  user);

    @PUT("quizzes/{id}/?format=json")
    Call<Quiz> putQuiz(@Path("id") int id, @Body Quiz  quiz);

    @PATCH("quizzes/{id}/?format=json")
    Call<Quiz> patchQuiz(@Path("id") int id, @Body Quiz  quiz);

    @PUT("questions/{id}/?format=json")
    Call<Question> putQuestion(@Path("id") int id, @Body Question  question);

    @PATCH("questions/{id}/?format=json")
    Call<Question> patchQuestion(@Path("id") int id, @Body Question  question);
}
