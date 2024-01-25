package com.ronen.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.ronen.login.Adapterpack.ViewAdapter;
import com.ronen.login.DataModels.FirebaseDataModel;
import com.ronen.login.Farebase.FirebaseHelper;
import com.ronen.login.databinding.ActivityDashboardBinding;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity implements FirebaseHelper.RetreiveDataSet{
    private ActivityDashboardBinding binding;
    private final Activity TAG = this;
    private ViewAdapter viewAdapter;
    private FirebaseHelper firebaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ItemsRecyclerView.setLayoutManager(new LinearLayoutManager(TAG));

        viewAdapter = new ViewAdapter(new ArrayList<>());
        binding.ItemsRecyclerView.setAdapter(viewAdapter);

        firebaseHelper = new FirebaseHelper(this);
        firebaseHelper.retriveDataSet();
    }

    @Override
    public void onDataRetrieved(List<FirebaseDataModel> firebaseDataModelList) {
        viewAdapter.setUserList(firebaseDataModelList);
        viewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(String ErrorMsg) {

    }
}