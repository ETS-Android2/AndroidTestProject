package com.datechnologies.androidtest.animation;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.datechnologies.androidtest.MainActivity;
import com.datechnologies.androidtest.R;

/**
 * Screen that displays the D & A Technologies logo.
 * The icon can be moved around on the screen as well as animated.
 * */

public class AnimationActivity extends AppCompatActivity {
    AnimatedLogoView logoImageView;

    //==============================================================================================
    // Class Properties
    //==============================================================================================

    //==============================================================================================
    // Static Class Methods
    //==============================================================================================

    public static void start(Context context)
    {
        Intent starter = new Intent(context, AnimationActivity.class);
        context.startActivity(starter);
    }

    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        logoImageView = findViewById(R.id.animation_logo);

        // DONE: Make the UI look like it does in the mock-up. Allow for horizontal screen rotation.
        // DONE: Add a ripple effect when the buttons are clicked

        // DONE: When the fade button is clicked, you must animate the D & A Technologies logo.
        // DONE: It should fade from 100% alpha to 0% alpha, and then from 0% alpha to 100% alpha

        // DONE: The user should be able to touch and drag the D & A Technologies logo around the screen.

        // TODO: Add a bonus to make yourself stick out. Music, color, fireworks, explosions!!!
    }

    public void onFadeInButtonClicked(View v) {
        ObjectAnimator animation = ObjectAnimator.ofFloat(logoImageView, "alpha", 0f);
        ObjectAnimator animation2 = ObjectAnimator.ofFloat(logoImageView, "alpha", 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animation, animation2);
        animatorSet.setDuration(2000);
        animatorSet.start();
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
