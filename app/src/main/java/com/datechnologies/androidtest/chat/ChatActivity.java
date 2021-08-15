package com.datechnologies.androidtest.chat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.datechnologies.androidtest.MainActivity;
import com.datechnologies.androidtest.R;
import com.datechnologies.androidtest.api.ChatLogMessageModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Screen that displays a list of chats from a chat log.
 */
public class ChatActivity extends AppCompatActivity {

    //==============================================================================================
    // Class Properties
    //==============================================================================================

    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;

    //==============================================================================================
    // Static Class Methods
    //==============================================================================================

    public static void start(Context context)
    {
        Intent starter = new Intent(context, ChatActivity.class);
        context.startActivity(starter);
    }

    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        chatAdapter = new ChatAdapter();

        recyclerView.setAdapter(chatAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false));

        List<ChatLogMessageModel> tempList = new ArrayList<>();

        ChatLogMessageModel chatLogMessageModel = new ChatLogMessageModel();
        chatLogMessageModel.message = "This is test data. Please retrieve real data.";

        tempList.add(chatLogMessageModel);
        tempList.add(chatLogMessageModel);
        tempList.add(chatLogMessageModel);
        tempList.add(chatLogMessageModel);
        tempList.add(chatLogMessageModel);
        tempList.add(chatLogMessageModel);
        tempList.add(chatLogMessageModel);
        tempList.add(chatLogMessageModel);

        String json = "{ \"data\" : [ { \"user_id\" : \"1\", \"name\" : \"Drew\", \"avatar_url\" : \"http://dev.rapptrlabs.com/Tests/images/drew_avatar.png\", \"message\" : \"Team, can we give job applicants taking this test some examples of the types of apps they’d be working on if they joined our team?\" }, { \"user_id\" : \"2\", \"name\" : \"Abby\", \"avatar_url\" : \"http://dev.rapptrlabs.com/Tests/images/abby_avatar.png\", \"message\" : \"We work on ecommerce apps for brands like PromGirl & Simply Dresses.\" }, { \"user_id\" : \"3\", \"name\" : \"Taylor\", \"avatar_url\" : \"http://dev.rapptrlabs.com/Tests/images/taylor_avatar.png\", \"message\" : \"You know those scooter sharing services that have been popping up? We developed and support one of those: Movo.\" }, { \"user_id\" : \"2\", \"name\" : \"Abby\", \"avatar_url\" : \"http://dev.rapptrlabs.com/Tests/images/abby_avatar.png\", \"message\" : \"We do a lot of hardware-pairing apps as well like Conair’s Smart WeightWatchers Scale and Phlex’s new smart goggles.\" } ] }";
        Gson gson = new Gson();
        ChatData data = gson.fromJson(json, ChatData.class);
        List<ChatLogMessageModel> chatLogMessageList = data.getChatLogMessages();
        chatAdapter.setChatLogMessageModelList(chatLogMessageList);

        //chatAdapter.setChatLogMessageModelList(tempList);

        // TODO: Make the UI look like it does in the mock-up. Allow for horizontal screen rotation.

        // TODO: Retrieve the chat data from http://dev.rapptrlabs.com/Tests/scripts/chat_log.php
        // TODO: Parse this chat data from JSON into ChatLogMessageModel and display it.


    }

    public void setChatLogMessage(List<ChatLogMessageModel> chatLogMessage)
    {

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
