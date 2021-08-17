package com.datechnologies.androidtest.chat;

import com.datechnologies.androidtest.api.ChatLogMessageModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Data Model used to store data obtained from REST API POST call
 */
public class ChatData
{
    @SerializedName("data")
    private final List<ChatLogMessageModel> chatLogMessages;

    public ChatData(List<ChatLogMessageModel> chatLogMessages)
    {
        this.chatLogMessages = chatLogMessages;
    }

    public List<ChatLogMessageModel> getChatLogMessages()
    {
        return chatLogMessages;
    }

}
