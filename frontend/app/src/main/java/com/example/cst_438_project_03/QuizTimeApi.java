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

    @POST("users")
    Call<Users> createAccount(@Body Users user);

    @FormUrlEncoded
    @POST("users/?format=json")
    Call<Users> createAccount(
            @Field("username") String username,
            @Field("password") String password
    );
}
