package com.app.hallselector;

import android.content.Context;
import android.content.Intent;
import android.media.Rating;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.hallselector.model.Building;

import java.util.List;


public class BuildingAdapter extends RecyclerView.Adapter<BuildingAdapter.ViewHolder> {

    private List<Building> search_results;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView building_name;
        private TextView building_address;
        private TextView building_phone;
        private TextView building_director;
        private TextView building_description;

        public ViewHolder(View view) {
            super(view);
            this.building_name = (TextView) view.findViewById(R.id.building_name);
            this.building_address = (TextView) view.findViewById(R.id.building_address);
            this.building_phone = (TextView) view.findViewById(R.id.building_director);
            this.building_director = (TextView) view.findViewById(R.id.building_director);
            this.building_description = (TextView) view.findViewById(R.id.building_description);

            view.findViewById(R.id.see_ratings).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View viewOnClick){
                    Context context = viewOnClick.getContext();
                    Intent intent = new Intent(context, RatingActivity.class);
                    intent.putExtra("building", building_name.getText().toString());
                    context.startActivity(intent);
                }
            });
        }

        public TextView getBuilding_name() {
            return building_name;
        }

        public TextView getBuilding_address() {
            return building_address;
        }

        public TextView getBuilding_phone() {
            return building_phone;
        }

        public TextView getBuilding_director() {
            return building_director;
        }

        public TextView getBuilding_description() {
            return building_description;
        }

    }


    public BuildingAdapter(List<Building> search_results) {
        this.search_results = search_results;
    }

    @NonNull
    @Override
    public BuildingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_building_info, parent, false); //get view

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuildingAdapter.ViewHolder holder, int position) {
        holder.getBuilding_name().setText(this.search_results.get(position).getName());
        holder.getBuilding_address().setText(this.search_results.get(position).getAddress());
        holder.getBuilding_phone().setText(this.search_results.get(position).getPhone_number());
        holder.getBuilding_director().setText(this.search_results.get(position).getHall_director());
        holder.getBuilding_description().setText(this.search_results.get(position).getHall_description());
    }

    @Override
    public int getItemCount() {
        return this.search_results.size();
    }


}

