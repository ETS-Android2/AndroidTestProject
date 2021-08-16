package com.datechnologies.androidtest.chat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.datechnologies.androidtest.MainActivity;
import com.datechnologies.androidtest.R;
import com.datechnologies.androidtest.api.ChatLogAPI;
import com.datechnologies.androidtest.api.ChatLogMessageModel;
import com.datechnologies.androidtest.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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

        recyclerView = findViewById(R.id.recyclerView);

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

        Retrofit retrofit = RetrofitClient.getRetrofitInstance();

        ChatLogAPI chatLogAPI = retrofit.create(ChatLogAPI.class);

        Call<ChatData> call = chatLogAPI.getChatLogData();

        call.enqueue(new Callback<ChatData>()
        {
            @Override
            public void onResponse(Call<ChatData> call, Response<ChatData> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(
                            getApplicationContext(),
                            "Could not retrieve data",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                List<ChatLogMessageModel> chatLogMessageList = response.body().getChatLogMessages();
                chatAdapter.setChatLogMessageModelList(chatLogMessageList);
            }

            @Override
            public void onFailure(Call<ChatData> call, Throwable throwable)
            {
                Log.e("TAG", throwable.toString());
                Toast.makeText(
                        getApplicationContext(),
                        "Error: " + throwable.toString(),
                        Toast.LENGTH_LONG).show();
            }
        });

        // TODO: Make the UI look like it does in the mock-up. Allow for horizontal screen rotation.

        // DONE: Retrieve the chat data from http://dev.rapptrlabs.com/Tests/scripts/chat_log.php
        // DONE: Parse this chat data from JSON into ChatLogMessageModel and display it.

    }

    // Button animations to move to previous (back) activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.coming_in, R.anim.coming_out);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
