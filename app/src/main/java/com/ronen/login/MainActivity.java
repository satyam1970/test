package com.ronen.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ronen.login.Farebase.FirebaseAuthHelper;
import com.ronen.login.Farebase.FirebaseHelper;
import com.ronen.login.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final Activity TAG = this;
    private boolean isUppercase = false, isMinimumChar = false,
                    isSpacialChar = false, isNumeric = false, username = false;

    private FirebaseAuthHelper firebaseAuthHelper;
    private FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        firebaseAuthHelper = new FirebaseAuthHelper(this);
        firebaseHelper = new FirebaseHelper();
        binding.finalSubmitButton.setOnClickListener(v -> {
            getData();
            isBlank();
            changingData();
            firebaseAuthHelper.SignUp(binding.EmailEditText.getText().toString().trim(), binding.PasswordEditText.getText().toString().trim(), new FirebaseAuthHelper.fCallback() {
                @Override
                public void onComplete(FirebaseUser user) {
                    makeToast("User Created");
                    initIntent(TAG, Signin.class);
                    firebaseHelper.createDatabase();
                }
                @Override
                public void onFailed(String errorMessage) {

                }
            });
        });
        binding.loginButton.setOnClickListener(v -> {
            initIntent(TAG, Signin.class);
        });
    }
    private void getData(){
        if(binding.UsernameEditText.getText().toString().trim().length()==0){
            binding.UsernameWarning.setVisibility(View.VISIBLE);
        }
        if(binding.PasswordEditText.getText().toString().trim().length()==0){
            binding.PasswordWarning.setVisibility(View.VISIBLE);
        }
    }
    private void makeToast(String string){
        Toast.makeText(TAG, string, Toast.LENGTH_SHORT).show();
    }
    private void isBlank(){
        if(binding.UsernameEditText.getText().toString().trim().length()>0 &&
        binding.UsernameWarning.getVisibility() == View.VISIBLE){
            binding.UsernameWarning.setVisibility(View.GONE);
        }
        if (binding.PasswordEditText.getText().toString().trim().length()>0 &&
        binding.PasswordWarning.getVisibility() == View.VISIBLE){
            binding.PasswordWarning.setVisibility(View.GONE);
        }
    }

    private void validationCheck(String password){
        if (password.length()>=7){
            isMinimumChar = true;
            binding.firstImageView.setImageResource(R.drawable.baseline_check_circle_24_green);
        }
        if(password.matches("(.*[A-Z].*)")){
            isUppercase = true;
            binding.secondImageView.setImageResource(R.drawable.baseline_check_circle_24_green);
        }
        if(password.matches("(.*[0-9].*)")){
            isNumeric = true;
            binding.thirdImageView.setImageResource(R.drawable.baseline_check_circle_24_green);
        }
        if (password.matches("^(?=.*[_.()]).*$")){
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
                validationCheck(binding.PasswordEditText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
    private void initIntent(Context context, Class c){
        Intent intent = new Intent(context, c);
        startActivity(intent);
    }
}