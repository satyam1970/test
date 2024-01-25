package com.ronen.login.Farebase;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ronen.login.Adapterpack.ViewAdapter;
import com.ronen.login.DataModels.FirebaseDataModel;

import java.util.ArrayList;
import java.util.List;

public class FirebaseHelper {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private ViewAdapter viewAdapter;
    private RetreiveDataSet retreiveDataSet;
    private List<FirebaseDataModel> firebaseDataModelList = new ArrayList<>();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    public FirebaseHelper(Activity activity){
        viewAdapter = new ViewAdapter();
    }
    public  FirebaseHelper(){
    }
    public void createDatabase(){
        if (firebaseUser!=null){
            String uid = firebaseUser.getUid();
            String currentMillis = String.valueOf(System.currentTimeMillis());
            FirebaseDataModel dataModel = new FirebaseDataModel();
            dataModel.setName("name");
            dataModel.setAge(12);
            dataModel.setCity("Hyderabad");
            databaseReference.child("Users").child(uid).child(currentMillis).setValue(dataModel);
        }
    }
    public void setRetreiveDataSet(RetreiveDataSet retreiveDataSet){
        this.retreiveDataSet = retreiveDataSet;
    }

    public void retriveDataSet(){
        if (firebaseUser!=null){
            String uid = firebaseUser.getUid();
                databaseReference.child("Users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                FirebaseDataModel dataModel = dataSnapshot.getValue(FirebaseDataModel.class);
                                    if(dataModel!=null){
                                            firebaseDataModelList.add(dataModel);
                                    }
                            }
                            retreiveDataSet.onDataRetrieved(firebaseDataModelList);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("Error", "error"+error.getMessage());
                    }
                });
        }
    }
    public interface RetreiveDataSet{
        void onDataRetrieved(List<FirebaseDataModel> firebaseDataModelList);

        void onFailed(String ErrorMsg);
    }
}
