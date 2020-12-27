package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserDetails extends AppCompatActivity
{
     EditText firstName,lastName,dateOfBirth,gender,Email,phoneNumber,address,companyname,companyaddress;
    Button Submit;
    Users _users;
    TextView title;

    Animation top,left,right;

    DatabaseReference userData;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        title = findViewById(R.id.usertitle);
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

        top = AnimationUtils.loadAnimation(this,R.anim.anim_top);
        right = AnimationUtils.loadAnimation(this,R.anim.anim_right);
        left = AnimationUtils.loadAnimation(this,R.anim.anim_left);

        title.setAnimation(top);

        firstName.setAnimation(left);
        dateOfBirth.setAnimation(left);
        phoneNumber.setAnimation(left);
        address.setAnimation(left);
        companyaddress.setAnimation(left);

        lastName.setAnimation(right);
        gender.setAnimation(right);
        Email.setAnimation(right);
        companyname.setAnimation(right);




      userData = FirebaseDatabase.getInstance("https://loca-e3bf3-default-rtdb.firebaseio.com/").getReference();
      mAuth = FirebaseAuth.getInstance();


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


                if (TextUtils.isEmpty(_firstName) || TextUtils.isEmpty(_lastName))
                {
                    Toast.makeText(UserDetails.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                }
                else {
                    _users = new Users(_firstName, _lastName, _dob, _gender, _email, _phoneNumber, _address, _companyName, _companyAddress);
                    userData.child("Users").child(mAuth.getUid()).child("UserDetails").setValue(_users);


                    Intent main = new Intent(UserDetails.this, MainActivity.class);
                    startActivity(main);
                }
            }
        });


    }
}