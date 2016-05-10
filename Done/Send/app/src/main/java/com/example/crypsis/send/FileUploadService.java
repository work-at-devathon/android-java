package com.example.crypsis.send;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FileUploadService {
    @POST("/test")
    Call<RegisterUser> createTask(@Body RegisterUser registerUser);
}
