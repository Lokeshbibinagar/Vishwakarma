package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {


    TextView userName,phone,email,address,companyName,companyAddress;
    ImageView profilePic;

    RecyclerView mProfileView;
    UserProductAdapter mProfileAdapter;
    List<Products> mProfileProduct;


    DatabaseReference UserDB,ProfileDB;
    FirebaseAuth mAuth;

    SwipeRefreshLayout _profileRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        UserDB = FirebaseDatabase.getInstance("https://loca-e3bf3-default-rtdb.firebaseio.com/").getReference();
        ProfileDB = FirebaseDatabase.getInstance("https://loca-e3bf3-default-rtdb.firebaseio.com/").getReference();
        mAuth = FirebaseAuth.getInstance();

        mProfileProduct = new ArrayList<>();

        profilePic = findViewById(R.id.profilePicture);
        userName = findViewById(R.id.profileUsername);
        phone = findViewById(R.id.profilePhone);
        email = findViewById(R.id.profileEmail);
        address = findViewById(R.id.profileAddress);
        companyName = findViewById(R.id.profileCompanyName);
        companyAddress = findViewById(R.id.profileCompanyAddress);

        mProfileView = findViewById(R.id.profileRecyclerView);
        mProfileView.setHasFixedSize(false);
        mProfileView.setLayoutManager(new LinearLayoutManager(this));

        _profileRefresh = findViewById(R.id.profileRefresh);



        UserDB.child("Users").child(mAuth.getUid()).child("UserDetails").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String _firstName = dataSnapshot.child("firstName").getValue().toString();
                String _lastName = dataSnapshot.child("lastName").getValue().toString();
                String _phone = dataSnapshot.child("phoneNumber").getValue().toString();
                String _email = dataSnapshot.child("email").getValue().toString();
                String _address = dataSnapshot.child("address").getValue().toString();
                String _companyName = dataSnapshot.child("companyName").getValue().toString();
                String _companyAddress = dataSnapshot.child("companyAddress").getValue().toString();
                String _profileUrl = dataSnapshot.child("profileUrl").getValue().toString();
                userName.setText(_firstName + " " + _lastName);
                phone.setText("Phone: " + _phone);
                email.setText("E-mail: " + _email);
                address.setText("Address: "+_address);
                companyName.setText("Company Name: " + _companyName);
                companyAddress.setText("Company Address: " + _companyAddress);

                Picasso.get().load(_profileUrl).into(profilePic);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        ProfileDB.child("Users").child(mAuth.getUid()).child("ProductDetails").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot HeadSnapshot : dataSnapshot.getChildren()) {

                    if (HeadSnapshot.hasChild("Kids") || HeadSnapshot.hasChild("Women")) {
                        for (DataSnapshot grandParentSnapshot : HeadSnapshot.getChildren()) {
                            for (DataSnapshot parentSnapshot : grandParentSnapshot.getChildren()) {
                                for (DataSnapshot childSnapshot : parentSnapshot.getChildren()) {
                                    Products _products = childSnapshot.getValue(Products.class);
                                    mProfileProduct.add(_products);

                                }
                            }
                        }
                    }

                    else if(HeadSnapshot.hasChild("_productColor")){
                        Products _products = HeadSnapshot.getValue(Products.class);
                       mProfileProduct.add(_products);

                    }
                    else{
                        for (DataSnapshot mobileSnapshot : HeadSnapshot.getChildren()) {
                            for (DataSnapshot itemSnapshot : mobileSnapshot.getChildren()) {
                                Products _products = itemSnapshot.getValue(Products.class);
                               mProfileProduct.add(_products);

                            }
                        }
                    }
                }

                mProfileAdapter = new UserProductAdapter(Profile.this,mProfileProduct);
                mProfileView.setAdapter(mProfileAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        _profileRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ProfileRefresh();
                _profileRefresh.setRefreshing(false);
            }
        });

    }
    public void ProfileRefresh()
    {
        finish();
        startActivity(getIntent());
        overridePendingTransition(0, 0);
        Toast.makeText(this, "Refreshing", Toast.LENGTH_SHORT).show();
    }

}