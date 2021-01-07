package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {


    TextView userName,phone,email,address,companyName,companyAddress;

    DatabaseReference UserDB;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        UserDB = FirebaseDatabase.getInstance("https://loca-e3bf3-default-rtdb.firebaseio.com/").getReference();
        mAuth = FirebaseAuth.getInstance();


        userName = findViewById(R.id.profileUsername);
        phone = findViewById(R.id.profilePhone);
        email = findViewById(R.id.profileEmail);
        address = findViewById(R.id.profileAddress);
        companyName = findViewById(R.id.profileCompanyName);
        companyAddress = findViewById(R.id.profileCompanyAddress);

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
                userName.setText(_firstName + " " + _lastName);
                phone.setText("Phone: " + _phone);
                email.setText("E-mail: " + _email);
                address.setText("Address: "+_address);
                companyName.setText("Company Name: " + _companyName);
                companyAddress.setText("Company Address: " + _companyAddress);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}