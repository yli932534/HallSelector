package com.app.hallselector;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.hallselector.model.Search_Record;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;


public class RecordActivity extends AppCompatActivity {

    private String name;
    private DatabaseReference record_ref;
    RecyclerView recyclerView;
    DatabaseReference database;
    CustomAdapter customAdapter;
    public ArrayList<Search_Record> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w("RecordActivity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        Intent intent = getIntent();
        this.name = intent.getStringExtra("username");
        recyclerView = findViewById(R.id.recordActivity);
        this.record_ref = FirebaseDatabase.getInstance().getReference("search_records");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        customAdapter = new CustomAdapter(this, list);
        recyclerView.setAdapter(customAdapter);

        this.record_ref.orderByChild("username").equalTo(this.name)
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                        Log.d("search", dataSnapshot.getKey());
                        Search_Record current = dataSnapshot.getValue(Search_Record.class);
                            list.add(current);
                    }
                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Log.d("search", "onChildChanged");
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                        Log.d("search", "onChildRemoved");
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Log.d("search", "onChildMoved");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("search", "onCancelled");
                    }

                });

        this.record_ref.addListenerForSingleValueEvent(new ValueEventListener(){


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Search_Record temp = dataSnapshot.getValue(Search_Record.class);
                    //list.add(temp);


                }
                customAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        SharedPreferences store = getSharedPreferences("bk_color", Context.MODE_PRIVATE);
        int color = store.getInt("bk_color", Color.parseColor("#FFE4E9"));
        findViewById(R.id.record_layout).setBackgroundColor(color);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.w("RecordActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("RecordActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("RecordActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w("RecordActivity", "onStop");
    }

    @Override
    protected  void onRestart() {
        super.onRestart();
        Log.w("RecordActivity", "onRestart");
    }

    @Override
    protected  void onDestroy() {
        super.onDestroy();
        Log.w("RecordActivity", "onDestroy");
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

