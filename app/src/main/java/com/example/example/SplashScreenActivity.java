package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreenActivity extends AppCompatActivity {
TextView title,subtitle;
Animation topanim,bottomanim;

FirebaseUser currentUser;

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

        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

               if (currentUser == null)
               {
                   Intent login = new Intent(SplashScreenActivity.this,LoginActivity.class);
                   startActivity(login);
                   finish();
               }
               else {
                   Intent main = new Intent(SplashScreenActivity.this,MainActivity.class);
                   startActivity(main);
                   finish();
               }

            }
        },2000);
    }
}