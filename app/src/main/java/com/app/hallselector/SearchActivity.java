package com.app.hallselector;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.app.hallselector.model.Building;
import com.app.hallselector.model.Search_Record;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String username;
    private String area;
    private DatabaseReference building_ref;
    private DatabaseReference record_ref;
    private Building sample;
    public List<Building> search_result;
    public List<String> filters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();

        this.username = intent.getStringExtra("username");
        this.building_ref = FirebaseDatabase.getInstance().getReference().child("buildings");
        this.record_ref = FirebaseDatabase.getInstance().getReference().child("search_records");
        this.sample = new Building();
        this.search_result = new LinkedList<>();
        this.filters = new LinkedList<>();

        //config dropdown menu
        Spinner spinner = findViewById(R.id.area_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(this, R.array.areas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        SharedPreferences store = getSharedPreferences("bk_color", Context.MODE_PRIVATE);
        int color = store.getInt("bk_color", Color.parseColor("#FFE4E9"));
        findViewById(R.id.search_layout).setBackgroundColor(color);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        this.area = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //no need to implement
        //ref: https://www.youtube.com/watch?v=on_OrrX7Nw4
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBox_vending:
                if (checked){
                    this.sample.setVending_machines("true");
                    this.filters.add("vending machine");
                }
                else{
                    this.sample.setVending_machines(null);
                    this.filters.remove("vending machine");
                }
                break;
            case R.id.checkBox_microwave:
                if (checked){
                    this.sample.setMicrowave_refrigerator("true");
                    this.filters.add("microwave/refrigerator");
                }
                else{
                    this.sample.setMicrowave_refrigerator(null);
                    this.filters.remove("microwave/refrigerator");
                }
                break;
            case R.id.checkBox_handicap:
                if (checked){
                    this.sample.setHandicap_access("true");
                    this.filters.add("handicap");
                }
                else{
                    this.sample.setHandicap_access(null);
                    this.filters.remove("handicap");
                }
                break;
            case R.id.checkBox_kitchen:
                if (checked){
                    this.sample.setKitchen_facilities("true");
                    this.filters.add("kitchen");
                }
                else{
                    this.sample.setKitchen_facilities(null);
                    this.filters.remove("kitchen");
                }
                break;
            case R.id.checkBox_laundry:
                if (checked){
                    this.sample.setLaundry_facilities("true");
                    this.filters.add("laundry");
                }
                else{
                    this.sample.setHandicap_access(null);
                    this.filters.remove("laundry");
                }
                break;
            case R.id.checkBox_ac:
                if (checked){
                    this.sample.setAir_conditioning("true");
                    this.filters.add("air conditioning");
                }
                else{
                    this.sample.setAir_conditioning(null);
                    this.filters.remove("air conditioning");
                }
                break;
            case R.id.checkBox_roombath:
                if (checked){
                    this.sample.setSuite_room_bath("true");
                    this.filters.add("room bath");
                }
                else{
                    this.sample.setSuite_room_bath(null);
                    this.filters.remove("room bath");
                }
                break;
            case R.id.checkBox_studyarea:
                if (checked){
                    this.sample.setStudy_areas("true");
                    this.filters.add("study area");
                }
                else{
                    this.sample.setStudy_areas(null);
                    this.filters.remove("study area");
                }
                break;
            case R.id.checkBox_academic:
                if (checked){
                    this.sample.setAcademic_year_housing("true");
                    this.filters.add("academic year housing");
                }
                else{
                    this.sample.setAcademic_year_housing(null);
                    this.filters.remove("academic year housing");
                }
                break;
            case R.id.checkBox_trashremoval:
                if (checked){
                    this.sample.setOwn_trash_removal("true");
                    this.filters.add("own trash removal");
                }
                else{
                    this.sample.setOwn_trash_removal(null);
                    this.filters.remove("own trash removal");
                }
                break;
            case R.id.checkBox_gameroom:
                if (checked){
                    this.sample.setGame_room("true");
                    this.filters.add("game room");
                }
                else{
                    this.sample.setGame_room(null);
                    this.filters.remove("game room");
                }
                break;
            case R.id.checkBox_lounge:
                if (checked){
                    this.sample.setLounge_space("true");
                    this.filters.add("lounge space");
                }
                else{
                    this.sample.setLounge_space(null);
                    this.filters.remove("lounge space");
                }
                break;
            default:
                Log.d("search", "unexpected error");
        }
    }

    //new search
    public void onClickDone(View view){
        this.search_result = new LinkedList<>();  // remove the search results of last time

        ChildEventListener e = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Log.d("search", dataSnapshot.getKey());
                Building current = dataSnapshot.getValue(Building.class);
                if (sample.meetBoolConditions(current)){
                    search_result.add(current);
                }

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

        };

        ValueEventListener listener = new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                addSearchRecords();
                Log.d("search", search_result.toString());
                Intent intent = new Intent(getApplicationContext(), SearchResultActivity.class);
                intent.putExtra("search_result", (Serializable)search_result);
                //ref: https://stackoverflow.com/questions/12092612/pass-list-of-objects-from-one-activity-to-other-activity-in-android
                building_ref.removeEventListener(e);
                startActivity(intent);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                building_ref.removeEventListener(e);
            }
        };

        this.building_ref.orderByChild("area").equalTo(this.area)
                .addChildEventListener(e);

        this.building_ref.addListenerForSingleValueEvent(listener);
        building_ref.removeEventListener(listener);




    }

    private void getBuildings(){


    }

    private void addSearchRecords(){
        this.filters.add(this.area);
        DatabaseReference id = this.record_ref.push();
        this.record_ref.child(id.getKey()).setValue(new Search_Record(this.username, new Date(), this.filters, this.search_result));
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.gc();
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