package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserDetails extends AppCompatActivity
{
     EditText firstName,lastName,dateOfBirth,gender,Email,phoneNumber,address,companyname,companyaddress;
    Button Submit;
    Users _users;


    DatabaseReference userDB;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);


        userDB = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
       // userDB = FirebaseDatabase.getInstance().getReference();


        firstName=findViewById(R.id.FirstName);
        lastName=findViewById(R.id.LastName);
        dateOfBirth=findViewById(R.id.DOB);
        gender=findViewById(R.id.Gender);
        Email=findViewById(R.id.Emailaddress);
        phoneNumber=findViewById(R.id.Phonenumber);
        address=findViewById(R.id.Address);
        companyname=findViewById(R.id.Companyname);
        companyaddress=findViewById(R.id.CompanyAddress);
        Submit=findViewById(R.id.Usersubmit);



        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                String _firstName=firstName.getText().toString();
                String _lastName=lastName.getText().toString();
                String _dob=dateOfBirth.getText().toString();
                String _email=Email.getText().toString();
                String _gender=gender.getText().toString();
                String _phoneNumber=phoneNumber.getText().toString();
                String _address=address.getText().toString();
                String _companyName=companyname.getText().toString();
                String _companyAddress=companyaddress.getText().toString();
                _users = new Users(_firstName,_lastName,_dob,_gender,_email,_phoneNumber,_address, _companyName,_companyAddress);
                userDB.child("Users").child(mAuth.getUid()).setValue(_users);

                Intent main=new Intent(UserDetails.this,MainActivity.class);
                startActivity(main);
            }
        });


    }
}