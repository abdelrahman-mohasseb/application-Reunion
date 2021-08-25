package com.openclassrooms.application_reunion.UI.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.application_reunion.model.Reunion;

import java.util.ArrayList;
import java.util.List;

public class ReunionReceyclerViewAdapter extends RecyclerView.Adapter<ReunionReceyclerViewAdapter.ViewHolder> {


        private List<Reunion> mReunions =  new ArrayList<>();

        public ReunionReceyclerViewAdapter(List<Reunion> items) {
            mReunions = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.reunion, parent, false);
            return new ViewHolder(view);
        }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ReunionReceyclerViewAdapter.ReunionRecyclerViewAdapter.ViewHolder holder, int position) {

    }

    @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            Neighbour neighbour = mNeighbours.get(position);
            holder.mNeighbourName.setText(neighbour.getName());
            Glide.with(holder.mNeighbourAvatar.getContext())
                    .load(neighbour.getAvatarUrl())
                    .apply(RequestOptions.circleCropTransform())
                    .into(holder.mNeighbourAvatar);

            holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //go to details activity
                    NeighbourApiService mApiService = DI.getNeighbourApiService();
                    Intent intent = new Intent().setClass(holder.itemView.getContext(),NeighbourDetailsActivity.class);
                    intent.putExtra(EXTRA_ID, neighbour.getId());
                    holder.itemView.getContext().startActivity(intent);

                }
            });

        }

        @Override
        public int getItemCount() {
            return mNeighbours.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.item_list_avatar)
            public ImageView mNeighbourAvatar;
            @BindView(R.id.item_list_name)
            public TextView mNeighbourName;
            @BindView(R.id.item_list_delete_button)
            public ImageButton mDeleteButton;

            public ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}
