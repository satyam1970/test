package com.ronen.login.Farebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ronen.login.Adapterpack.ViewAdapter;
import com.ronen.login.DataModels.FirebaseDataModel;

public class FirebaseHelper {
    private DatabaseReference databaseReference;
    private ViewAdapter viewAdapter;
    public FirebaseHelper(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        viewAdapter = new ViewAdapter();
    }
    public void recieveData(){
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                FirebaseDataModel dataModel = snapshot.getValue(FirebaseDataModel.class);
                viewAdapter.attachDataModels(dataModel);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
