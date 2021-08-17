package com.datechnologies.androidtest.api;

import com.datechnologies.androidtest.chat.ChatData;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interface which is auto-populated by Retrofit class to send GET request to the web service.
 */
public interface ChatLogAPI
{
    @GET("chat_log.php")
    Call<ChatData> getChatLogData();
}
