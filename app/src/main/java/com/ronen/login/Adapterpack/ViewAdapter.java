package com.ronen.login.Adapterpack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ronen.login.DataModels.FirebaseDataModel;
import com.ronen.login.databinding.CustomFirebaseDataLayoutBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.viewHolder>{

    private CustomFirebaseDataLayoutBinding binding;
    private List<FirebaseDataModel> firebaseDataModel = new ArrayList<>();
    private Context mContext;

    public ViewAdapter(ArrayList<FirebaseDataModel> es) {

    }
    public ViewAdapter(){

    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = CustomFirebaseDataLayoutBinding.inflate(inflater, parent, false );
        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
    FirebaseDataModel firebaseDataModels = firebaseDataModel.get(position);
    holder.binding.firebaseNameText.setText(firebaseDataModels.getName());
    holder.binding.firebaseCityText.setText(firebaseDataModels.getCity());
    holder.binding.firebaseAgeText.setText(String.valueOf(firebaseDataModels.getAge()));
    }

    @Override
    public int getItemCount() {
        return firebaseDataModel.size();
    }

    public void sortByName(){
        Collections.sort(firebaseDataModel, Comparator.comparing(FirebaseDataModel::getName));
        notifyDataSetChanged();
    }
    public void sortByAge(){
        Collections.sort(firebaseDataModel, Comparator.comparing(FirebaseDataModel::getAge));
        notifyDataSetChanged();
    }
    public void sortByCity(){
        Collections.sort(firebaseDataModel, Comparator.comparing(FirebaseDataModel::getCity));
        notifyDataSetChanged();
    }

    public void setUserList(List<FirebaseDataModel> firebaseDataModelList) {
        this.firebaseDataModel= firebaseDataModelList;
    }

    static class viewHolder extends RecyclerView.ViewHolder{
        CustomFirebaseDataLayoutBinding binding;
        public viewHolder(@NonNull CustomFirebaseDataLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
