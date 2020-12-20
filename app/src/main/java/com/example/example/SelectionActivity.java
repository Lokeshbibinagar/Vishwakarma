package com.example.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class SelectionActivity extends AppCompatActivity {
    Button sell,shop;
    TextView title;
    Animation top,bottom;
    ConstraintLayout _background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        sell = findViewById(R.id.sell);
        shop = findViewById(R.id.buy);
        title = findViewById(R.id.selectionTitle);
        _background = findViewById(R.id.selectionBackground);
        top = AnimationUtils.loadAnimation(this,R.anim.anim_top);
        bottom = AnimationUtils.loadAnimation(this,R.anim.anim_bottom);


        title.setAnimation(top);
        sell.setAnimation(top);
        shop.setAnimation(top);
        _background.setAnimation(bottom);


        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user = new Intent(SelectionActivity.this, UserDetails.class);
                startActivity(user);

            }

        });
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(SelectionActivity.this, MainActivity.class);
                startActivity(main);
            }
        });


    }
}