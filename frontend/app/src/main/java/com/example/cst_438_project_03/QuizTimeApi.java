package com.example.cst_438_project_03;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuizTimeApi {

    @GET("users")
    Call<List<Users>> getUsers();
}
