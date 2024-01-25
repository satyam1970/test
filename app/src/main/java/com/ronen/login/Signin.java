package com.ronen.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ronen.login.databinding.ActivitySigninBinding;

public class Signin extends AppCompatActivity {

    ActivitySigninBinding binding;
    String uName, pWord;
    private final Activity TAG = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        uName = binding.UsernameEditText.getText().toString().trim();
        pWord = binding.PasswordEditText.getText().toString().trim();

        binding.finalSubmitButton.setOnClickListener(v ->{
            if(uName.isEmpty()){
                binding.UsernameWarning.setVisibility(View.VISIBLE);
            }
            if (pWord.isEmpty()){
                binding.PasswordWarning.setVisibility(View.VISIBLE);
            }
        });


        binding.signUpButton.setOnClickListener(v ->{
            startActivity(new Intent(TAG, MainActivity.class));
        });
    }
    private void isBlank(){
        if(uName.length()>0 && binding.UsernameWarning.getVisibility()== View.VISIBLE){
            binding.UsernameWarning.setVisibility(View.GONE);
        }
        if(pWord.length()>0 && binding.PasswordWarning.getVisibility() == View.VISIBLE){
            binding.PasswordWarning.setVisibility(View.GONE);
        }
    }
}