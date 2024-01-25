package com.ronen.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ronen.login.Farebase.FirebaseAuthHelper;
import com.ronen.login.Farebase.FirebaseHelper;
import com.ronen.login.databinding.ActivitySigninBinding;

public class Signin extends AppCompatActivity {

    ActivitySigninBinding binding;
    private final Activity TAG = this;
    private FirebaseAuthHelper firebaseAuthHelper;
    private FirebaseHelper firebaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        

        firebaseHelper = new FirebaseHelper();
        
        firebaseAuthHelper = new FirebaseAuthHelper(this);
        binding.finalSubmitButton.setOnClickListener(v ->{
            if(binding.UsernameEditText.getText().toString().trim().isEmpty()){
                binding.UsernameWarning.setVisibility(View.VISIBLE);
            }
            if (binding.PasswordEditText.getText().toString().trim().isEmpty()){
                binding.PasswordWarning.setVisibility(View.VISIBLE);
            }
            isBlank();
            firebaseAuthHelper.Login(binding.UsernameEditText.getText().toString().trim(), 
                    binding.PasswordEditText.getText().toString().trim(), new FirebaseAuthHelper.fCallback() {
                @Override
                public void onComplete(FirebaseUser user) {
                    startActivity(new Intent(TAG, Dashboard.class));
                    Toast.makeText(TAG, "User Logged in", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onFailed(String errorMessage) {

                }
            });
        });


        binding.signUpButton.setOnClickListener(v ->{
            startActivity(new Intent(TAG, MainActivity.class));
        });
    }
    private void isBlank(){
        if(binding.UsernameEditText.getText().toString().trim().length()>0 && binding.UsernameWarning.getVisibility()== View.VISIBLE){
            binding.UsernameWarning.setVisibility(View.GONE);
        }
        if(binding.PasswordEditText.getText().toString().trim().length()>0 && binding.PasswordWarning.getVisibility() == View.VISIBLE){
            binding.PasswordWarning.setVisibility(View.GONE);
        }
    }
}