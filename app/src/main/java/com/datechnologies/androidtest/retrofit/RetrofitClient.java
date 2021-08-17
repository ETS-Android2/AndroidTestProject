package com.datechnologies.androidtest.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class which creates the Retrofit API Client that helps application connect to REST web services
 * and adapts Java code to send/receive HTTP request and calls.
 */
public class RetrofitClient
{
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://dev.rapptrlabs.com/Tests/scripts/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
