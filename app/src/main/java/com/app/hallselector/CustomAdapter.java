package com.app.hallselector;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.hallselector.model.Search_Record;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

  Context context;
  ArrayList<Search_Record> list;

 public CustomAdapter(Context context, ArrayList<Search_Record> list) {
  this.context = context;
  this.list = list;
 }

  @NonNull
  @Override
  public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
     return new CustomViewHolder(v);
  }

 @Override
 public void onBindViewHolder(CustomViewHolder holder, int position) {

     Log.w("MainActivity", "SSSSSSSSSSSSSSSSS");
     Search_Record r = list.get(position);
     holder.username.setText(r.getUsername());
     holder.date.setText(r.getDate().toString());
     holder.filter.setText(r.getFilters().toString());
     holder.building.setText(r.getBuildingName().toString());
 }

 @Override
 public int getItemCount() {
  return list.size();
 }

 public static class CustomViewHolder extends RecyclerView.ViewHolder{

   TextView username, date, filter, building;
   public CustomViewHolder(@NonNull View itemView) {
       super(itemView);

       this.username = itemView.findViewById(R.id.username_input);
       this.date = itemView.findViewById(R.id.date_input);
       this.building = itemView.findViewById(R.id.buildings_input);
       this.filter = itemView.findViewById(R.id.filter_input);

   }
  }

 }