package com.datechnologies.androidtest.api;

import com.datechnologies.androidtest.chat.ChatData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ChatLogAPI
{
    @GET("chat_log.php")
    Call<ChatData> getChatLogData();
}
