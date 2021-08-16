package com.datechnologies.androidtest.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.datechnologies.androidtest.MainActivity;
import com.datechnologies.androidtest.R;
import com.datechnologies.androidtest.api.LoginAPI;
import com.datechnologies.androidtest.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A screen that displays a login prompt, allowing the user to login to the D & A Technologies Web Server.
 *
 */
public class LoginActivity extends AppCompatActivity
{
    //==============================================================================================
    // Login Fields
    //==============================================================================================
    private EditText usernameView;
    private EditText passwordView;

    //==============================================================================================
    // Static Class Methods
    //==============================================================================================

    public static void start(Context context)
    {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }

    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        usernameView = findViewById(R.id.editTextTextEmailAddress);
        passwordView = findViewById(R.id.editTextTextPassword);

        // DONE: Make the UI look like it does in the mock-up. Allow for horizontal screen rotation.
        // DONE: Add a ripple effect when the buttons are clicked
        // DONE: Save screen state on screen rotation, inputted username and password should not disappear on screen rotation

        // DONE: Send 'email' and 'password' to http://dev.rapptrlabs.com/Tests/scripts/login.php
        // DONE: as FormUrlEncoded parameters.

        // DONE: When you receive a response from the login endpoint, display an AlertDialog.
        // DONE: The AlertDialog should display the 'code' and 'message' that was returned by the endpoint.
        // DONE: The AlertDialog should also display how long the API call took in milliseconds.
        // DONE: When a login is successful, tapping 'OK' on the AlertDialog should bring us back to the MainActivity

        // DONE: The only valid login credentials are:
        // DONE: email: info@rapptrlabs.com
        // DONE: password: Test123
        // DONE: so please use those to test the login.
    }

    public void onLoginButtonClick(View v)
    {
        String username = usernameView.getText().toString();
        String password = passwordView.getText().toString();

        if(!isEmptyInput(username, password))
        {
            return;
        }
        login(username, password);
    }

    private boolean isEmptyInput(String username, String password)
    {
        if(username.isEmpty() && password.isEmpty())
        {
            usernameView.setError("Enter username");
            passwordView.setError("Enter password");
            return false;
        }

        if(username.isEmpty())
        {
            usernameView.setError("Enter username");
            return false;
        }
        if(password.isEmpty())
        {
            passwordView.setError("Enter password");
            return false;
        }
        return true;
    }

    private void login(String username, String password)
    {
        // Use retrofit to take care of Java->REST API and implements the functions for our
        // login interface
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();

        LoginAPI loginAPI = retrofit.create(LoginAPI.class);

        Call<LoginResponse> call = loginAPI.login(username, password);

        call.enqueue(new Callback<LoginResponse>()
        {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response)
            {
                long duration = response.raw().receivedResponseAtMillis() - response.raw().sentRequestAtMillis();
                String message = "Code: " + response.code() +
                                "\n\nMessage: " + response.message() +
                                "\n\nDuration: " + duration + " ms";
                showAlertDialogue(message, response.isSuccessful());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable throwable)
            {
                Log.e("TAG", throwable.toString());
            }
        });

    }

    public void showAlertDialogue(String message, final boolean responseSuccessful)
    {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        View customLayout = getLayoutInflater().inflate(R.layout.alert_dialogue, null);
        alert.setView(customLayout);

        TextView title = customLayout.findViewById(R.id.login_title);
        TextView messageView = customLayout.findViewById(R.id.login_message);
        ImageView icon = customLayout.findViewById(R.id.login_icon);
        Button positiveButton = customLayout.findViewById(R.id.ok_button);

        // Customize Alert Dialogue based on HTTP code response
        if (!responseSuccessful)
        {
            title.setText(R.string.login_failed);
            icon.setImageResource(R.drawable.ic_close);
        }
        messageView.setText(message);

        final AlertDialog dialogue = alert.show();
        positiveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!responseSuccessful)
                {
                    dialogue.dismiss();
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
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
