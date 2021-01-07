package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.UiAutomation;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
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
      FirebaseAuth mAuth;
      List<Products> mProducts;



      SwipeRefreshLayout _swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = findViewById(R.id.button);

        _swipeRefresh = findViewById(R.id.swipeRefresh);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mProducts = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();

        mDataReff = FirebaseDatabase.getInstance("https://loca-e3bf3-default-rtdb.firebaseio.com/").getReference();

        mDataReff.child("Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot HeadSnapshot : dataSnapshot.getChildren()) {

                    if (HeadSnapshot.hasChild("Kids") || HeadSnapshot.hasChild("Women")) {
                        for (DataSnapshot grandParentSnapshot : HeadSnapshot.getChildren()) {
                            for (DataSnapshot parentSnapshot : grandParentSnapshot.getChildren()) {
                                for (DataSnapshot childSnapshot : parentSnapshot.getChildren()) {
                                    Products _products = childSnapshot.getValue(Products.class);
                                    mProducts.add(_products);

                                }
                            }
                        }
                    }

                    else if(HeadSnapshot.hasChild("_productColor")){
                        Products _products = HeadSnapshot.getValue(Products.class);
                        mProducts.add(_products);

                    }
                    else{
                        for (DataSnapshot mobileSnapshot : HeadSnapshot.getChildren()) {
                            for (DataSnapshot itemSnapshot : mobileSnapshot.getChildren()) {
                                Products _products = itemSnapshot.getValue(Products.class);
                                mProducts.add(_products);

                            }
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


                Intent products = new Intent(MainActivity.this,ProductDetails.class);
                startActivity(products);

            }

        });


        _swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Refresh();
                _swipeRefresh.setRefreshing(false);

            }
        });

    }

    public void Refresh() {
        finish();
        startActivity(getIntent());
        overridePendingTransition(0,0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater  = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu_layout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId())
        {
            case R.id.settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.profile:
                Intent profile = new Intent(MainActivity.this,Profile.class);
                startActivity(profile);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}