package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

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
    Animation top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        sell = findViewById(R.id.sell);
        shop = findViewById(R.id.buy);
        title = findViewById(R.id.selectionTitle);
        top = AnimationUtils.loadAnimation(this,R.anim.anim_top);

        title.setAnimation(top);
        sell.setAnimation(top);
        shop.setAnimation(top);

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