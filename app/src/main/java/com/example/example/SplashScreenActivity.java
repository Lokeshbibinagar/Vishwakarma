package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {
TextView title,subtitle;
Animation topanim,bottomanim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        title=findViewById(R.id.splashtitle);
        subtitle=findViewById(R.id.splashsubtitle);
        topanim= AnimationUtils.loadAnimation(this,R.anim.anim_top);
        bottomanim=AnimationUtils.loadAnimation(this,R.anim.anim_bottom);
        title.setAnimation(topanim);
        subtitle.setAnimation(bottomanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent login=new Intent(SplashScreenActivity.this,LoginActivity.class);
                startActivity(login);
                finish();

            }
        },2000);
    }
}