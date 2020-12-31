package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
      Button b;

      RecyclerView mRecyclerView;
      ProductsAdapter mProductsAdapter;

      DatabaseReference mDataReff;
      List<Products> mProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = findViewById(R.id.button);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mProducts = new ArrayList<>();


        mDataReff = FirebaseDatabase.getInstance("https://loca-e3bf3-default-rtdb.firebaseio.com/").getReference();

        mDataReff.child("Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ElectronicsSnapshot : dataSnapshot.getChildren())
                {
                    for(DataSnapshot mobileSnapshot : ElectronicsSnapshot.getChildren())
                    {
                        for(DataSnapshot itemSnapshot : mobileSnapshot.getChildren())
                        {
                            Products _products = itemSnapshot.getValue(Products.class);
                            mProducts.add(_products);
                        }
                    }
                }
                mProductsAdapter = new ProductsAdapter(MainActivity.this,mProducts);
                mRecyclerView.setAdapter(mProductsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent product = new Intent(MainActivity.this,ProductDetails.class);
                startActivity(product);
            }
        });
    }
}