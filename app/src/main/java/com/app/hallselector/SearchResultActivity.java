package com.app.hallselector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.app.hallselector.model.Building;

import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    private List<Building> results;
    BuildingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Intent intent = getIntent();
        this.results = (List<Building>)intent.getSerializableExtra("search_result");
        //ref: https://stackoverflow.com/questions/12092612/pass-list-of-objects-from-one-activity-to-other-activity-in-android

        RecyclerView recyclerView = findViewById(R.id.rec_search_result);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BuildingAdapter(this.results);
        //adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        //ref: https://stackoverflow.com/questions/40584424/simple-android-recyclerview-example

        SharedPreferences store = getSharedPreferences("bk_color", Context.MODE_PRIVATE);
        int color = store.getInt("bk_color", Color.parseColor("#FFE4E9"));
        findViewById(R.id.search_result_layout).setBackgroundColor(color);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }



}