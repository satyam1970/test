package com.ronen.login.Farebase;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ronen.login.Dashboard;

public class FirebaseAuthHelper {

    private Activity activity;
    private FirebaseAuth firebaseAuth;
    private Context mContext;
    public FirebaseAuthHelper(Activity activity1){
        firebaseAuth = FirebaseAuth.getInstance();
        this.activity = activity1;
    }

    public void SignUp (String email, String password, fCallback callback){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, task -> {
                   if (task.isSuccessful()){
                       FirebaseUser user = firebaseAuth.getCurrentUser();
                       if (user!=null){
                           callback.onComplete(user);
                       }else{
                           callback.onFailed("User Failed"+task.getException().getMessage());
                       }
                   }

                });
    }

    public void Login(String username, String password, fCallback callback){
        firebaseAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(activity, task -> {
                   if (task.isSuccessful()){
                       FirebaseUser regUser = firebaseAuth.getCurrentUser();
                       if(regUser!=null){
                           callback.onComplete(regUser);
//                           Intent intent = new Intent(mContext.getApplicationContext(), Dashboard.class);
//                           mContext.startActivity(intent);
                           
                       }
                   }else{
                       Toast.makeText(activity, "Wrong PAssword", Toast.LENGTH_SHORT).show();
                   }
                });

    }
    public interface fCallback{
        void onComplete(FirebaseUser user);

        void onFailed(String errorMessage);
    }
}
