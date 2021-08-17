package com.datechnologies.androidtest.api;

import com.datechnologies.androidtest.login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Interface that is auto-populated by Retrofit class to send POST request using FormURLEncoded
 */
public interface LoginAPI
{
    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(
            @Field("email") String username,
            @Field("password") String password
    );
}
