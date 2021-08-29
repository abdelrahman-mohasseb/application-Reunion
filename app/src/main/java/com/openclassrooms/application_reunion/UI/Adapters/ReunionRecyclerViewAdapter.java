package com.openclassrooms.application_reunion.UI.Adapters;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.application_reunion.DI.DI;
import com.openclassrooms.application_reunion.databinding.ReunionElementListBinding;
import com.openclassrooms.application_reunion.model.Reunion;
import com.openclassrooms.application_reunion.service.ReunionApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReunionRecyclerViewAdapter  extends  RecyclerView.Adapter<ReunionRecyclerViewAdapter.ViewHolder> {


    private List<Reunion> mReunions =  new ArrayList<>();


    public ReunionRecyclerViewAdapter(List<Reunion> items) {
        mReunions = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder (ReunionElementListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder( ReunionRecyclerViewAdapter.ViewHolder holder, int position) {
        Reunion reunion = mReunions.get(position);

        holder.binding.itemListDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReunionApiService mApiService = DI.getReunionApiService();
                mApiService.deleteReunion(reunion);
            }
        });
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.binding.itemListDate.setText(reunion.getHeure());
        holder.binding.itemListLieu.setText(reunion.getLieu());
        holder.binding.itemListSujet.setText(reunion.getSujet());
        holder.binding.itemListImage.setBackgroundColor(color);

    }







    @Override
    public int getItemCount() {
        return mReunions == null ? 0 :
                mReunions.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ReunionElementListBinding binding;

        public ViewHolder(ReunionElementListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

