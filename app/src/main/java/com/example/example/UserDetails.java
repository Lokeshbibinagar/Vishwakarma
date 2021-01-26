package com.example.example;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UserDetails extends AppCompatActivity
{
     EditText firstName,lastName,dateOfBirth,gender,Email,phoneNumber,address,companyname,companyaddress;
    Button Submit;
    Users _users;
    TextView title;
    ImageView _profilePic;

    Animation top,left,right;

    DatabaseReference userData;
    StorageReference userRef;
    FirebaseAuth mAuth;

    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        _profilePic = findViewById(R.id.profilePic);
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
        
     

        _profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,1);
            }
        });


      userData = FirebaseDatabase.getInstance("https://loca-e3bf3-default-rtdb.firebaseio.com/").getReference();
      userRef =  FirebaseStorage.getInstance("gs://loca-e3bf3.appspot.com").getReference();
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

                    StorageReference _profileRef = userRef.child("Profile Pictures").child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
                    _profileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                _profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        _users = new Users(_firstName, _lastName, _dob, _gender, _email, _phoneNumber, _address, _companyName, _companyAddress, uri.toString());
                                        userData.child("Users").child(mAuth.getUid()).child("UserDetails").setValue(_users);
                                        Intent main = new Intent(UserDetails.this, MainActivity.class);
                                        startActivity(main);
                                    }
                                });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UserDetails.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            }
        });


    }
    private String getFileExtension(Uri uri)
    {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            imageUri = data.getData();

            _profilePic.setImageURI(imageUri);
        }
    }
}