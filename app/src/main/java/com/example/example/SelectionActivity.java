package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectionActivity extends AppCompatActivity {
    Button sell,shop;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_selection);
        sell=findViewById(R.id.sell);
        shop=findViewById(R.id.buy);
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user= new Intent(SelectionActivity.this,UserDetails.class);
                startActivity(user);

            }

        });
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main=new Intent(SelectionActivity.this,MainActivity.class);
                startActivity(main);
            }
        });


    }
}