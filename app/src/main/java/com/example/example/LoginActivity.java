package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    EditText _phone,_code;
    Button _submit,_verify;

    FirebaseAuth mAuth;
     PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
     String mVerificationId;
     PhoneAuthProvider.ForceResendingToken mResendToken;

   private ProgressDialog _loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _phone = findViewById(R.id.Phone);
        _code = findViewById(R.id.Verification);
        _submit = findViewById(R.id.submit);
        _verify = findViewById(R.id.verify);

        mAuth = FirebaseAuth.getInstance();

        getSupportActionBar().setTitle("Login");

        _loadingBar = new ProgressDialog(this);

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

                signInWithPhoneAuthCredential(credential);
                Toast.makeText(LoginActivity.this, "You have been Successfully registered", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                _phone.setVisibility(View.VISIBLE);
                _submit.setVisibility(View.VISIBLE);
                _code.setVisibility(View.INVISIBLE);
                _verify.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {

                mVerificationId = verificationId;
                mResendToken = token;

                _phone.setVisibility(View.INVISIBLE);
                _submit.setVisibility(View.INVISIBLE);
                _verify.setVisibility(View.VISIBLE);
                _code.setVisibility(View.VISIBLE);

                Toast.makeText(LoginActivity.this, "Code has been sent to" + _phone.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        };

        _submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _number = _phone.getText().toString();

                if(TextUtils.isEmpty(_number))
                {
                    Toast.makeText(LoginActivity.this, "Please enter your phone Number Correctly", Toast.LENGTH_SHORT).show();
                }
                else {

                    _loadingBar.setTitle("Phone Authentication");
                    _loadingBar.setMessage("Please wait, we are authenticating your phone number");
                    _loadingBar.setCanceledOnTouchOutside(false);
                    _loadingBar.show();

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91" + _number,
                            60,
                            TimeUnit.SECONDS,
                            LoginActivity.this,
                            mCallbacks);
                    _loadingBar.dismiss();

                }
            }
        });

        _verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _verificationCode = _code.getText().toString();

                if(TextUtils.isEmpty(_verificationCode))
                {
                    Toast.makeText(LoginActivity.this, "code cannot be empty! Please enter your code", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    _loadingBar.setTitle("Verifying");
                    _loadingBar.setMessage("Please wait, while we are verifying");
                    _loadingBar.setCanceledOnTouchOutside(false);
                    _loadingBar.show();

                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, _verificationCode);
                    signInWithPhoneAuthCredential(credential);
                }

            }
        });

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            _loadingBar.dismiss();
                            Intent main = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(main);

                            Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            _loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Error! Unable to login", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}