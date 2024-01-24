package com.ronen.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.ronen.login.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final Activity TAG = this;
    private boolean isUppercase = false, isMinimumChar = false,
                    isSpacialChar = false, isNumeric = false, username = false;
    String getUsername = binding.UsernameEditText.getText().toString(),
            getPassword = binding.PasswordEditText.getText().toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.finalSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                isBlank();
                changingData();
            }
        });

    }
    private void getData(){
        if(getUsername.length()==0){
            binding.UsernameWarning.setVisibility(View.VISIBLE);

        }
        if(getPassword.length()==0){
            binding.PasswordWarning.setVisibility(View.VISIBLE);
        }
    }
    private void makeToast(String string){
        Toast.makeText(TAG, string, Toast.LENGTH_SHORT).show();
    }
    private void isBlank(){
        if(getUsername.length()>0 &&
        binding.UsernameWarning.getVisibility() == View.VISIBLE){
            binding.UsernameWarning.setVisibility(View.GONE);
        }
        if (getPassword.length()>0 &&
        binding.PasswordWarning.getVisibility() == View.VISIBLE){
            binding.PasswordWarning.setVisibility(View.GONE);
        }
    }

    private void validationCheck(){
        if (getPassword.length()>=7){
            isMinimumChar = true;
            binding.firstImageView.setImageResource(R.drawable.baseline_check_circle_24_green);
        }
        if(getPassword.matches("(.*[A-Z].*)")){
            isUppercase = true;
            binding.secondImageView.setImageResource(R.drawable.baseline_check_circle_24_green);
        }
        if(getPassword.matches("(.*[0-9].*)")){
            isNumeric = true;
            binding.thirdImageView.setImageResource(R.drawable.baseline_check_circle_24_green);
        }
        if (getPassword.matches("^(?=.*[_.()]).*$")){
            isSpacialChar = true;
            binding.fourthImageView.setImageResource(R.drawable.baseline_check_circle_24_green);
        }
    }

    private void changingData(){
        binding.UsernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validationCheck();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}