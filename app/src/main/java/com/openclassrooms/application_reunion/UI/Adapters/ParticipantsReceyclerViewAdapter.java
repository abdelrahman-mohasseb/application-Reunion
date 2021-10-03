package com.openclassrooms.application_reunion.UI.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.application_reunion.databinding.ParticipantElementBinding;
import com.openclassrooms.application_reunion.databinding.ReunionElementListBinding;
import com.openclassrooms.application_reunion.model.Reunion;

import java.util.ArrayList;
import java.util.List;

public class ParticipantsReceyclerViewAdapter  extends  RecyclerView.Adapter<ParticipantsReceyclerViewAdapter.ViewHolder> {
    private List<String> mParticipants =  new ArrayList<>();


    public ParticipantsReceyclerViewAdapter(List<String> items) {
        mParticipants = items;
    }
    @NonNull
    @Override
    public ParticipantsReceyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new  ParticipantsReceyclerViewAdapter.ViewHolder(ParticipantElementBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipantsReceyclerViewAdapter.ViewHolder holder, int position) {
        String participant = mParticipants.get(position);
        if(position==(getItemCount()-1)){
            holder.participantElementBinding.participantName.setText(participant);
        } else {
            holder.participantElementBinding.participantName.setText(participant + ",");
        }
    }



    @Override
    public int getItemCount() {
        return mParticipants == null ? 0 :
                mParticipants.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ParticipantElementBinding participantElementBinding;

        public ViewHolder(ParticipantElementBinding binding) {
            super(binding.getRoot());
            this.participantElementBinding = binding;
        }
    }
}
