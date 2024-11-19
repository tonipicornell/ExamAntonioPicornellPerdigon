package com.example.examantoniopicornell;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        final View sky = findViewById(R.id.sky);
        final ImageView sun = findViewById(R.id.sun);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageButton faceIcon = findViewById(R.id.face_icon);

        faceIcon.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, TextFragments.class);
            startActivity(intent);
        });

        sky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sunSkyAnimation(sun, sky);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void sunSkyAnimation(ImageView sun, View sky){
        // SUN ANIMATION:
        // Move the sun over 3 seconds:
        ObjectAnimator sunAnimation = ObjectAnimator.ofFloat(sun, "translationY", 0f, sky.getHeight());

        // Duration of animation is the 5500 seconds:
        sunAnimation.setDuration(4500);
        sunAnimation.setInterpolator(new AccelerateInterpolator());

        // SKY ANIMATION:
        ValueAnimator skyAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), Color.parseColor("#00B0FF"), Color.parseColor("#FF9800"));
        skyAnimation.setDuration(3000);
        skyAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
                sky.setBackgroundColor((Integer) valueAnimator.getAnimatedValue());
            }
        });

        // NIGHT ANIMATION:
        ValueAnimator nightAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), Color.parseColor("#FF9800"), Color.parseColor("#000000"));
        nightAnimation.setDuration(1500);
        nightAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
                sky.setBackgroundColor((Integer) valueAnimator.getAnimatedValue());
            }
        });

        // START ANIMATIONS:
        sunAnimation.start();
        skyAnimation.start();
        skyAnimation.addListener(new android.animation.AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(android.animation.Animator animation){
                nightAnimation.start();
            }
        });
    }
}