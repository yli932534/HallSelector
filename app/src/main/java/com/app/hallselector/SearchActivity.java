package com.app.hallselector;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.app.hallselector.model.Building;
import com.app.hallselector.model.Search_Record;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        this.search_result = new LinkedList<>();
        this.filters = new LinkedList<>();

        //config dropdown menu
        Spinner spinner = findViewById(R.id.area_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(this, R.array.areas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        this.area = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //no need to implement
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBox_vending:
                if (checked){

                }
                // Remove the meat
                break;
            case R.id.checkBox_microwave:

                break;

        }
    }

    //new search
    public void onClickDone(View view){
        this.search_result = new LinkedList<>();  // remove the search results of last time
        Building sample = new Building();
        sample.setResNet("true");
        sample.setAir_conditioning("true");
        sample.setStudy_areas("true");

        this.building_ref.orderByChild("area").equalTo(this.area)
                .addChildEventListener(new ChildEventListener() {
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

                });

        this.building_ref.addListenerForSingleValueEvent(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                addSearchRecords();
                Log.d("search", search_result.toString());

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }

    private void getBuildings(){


    }

    private void addSearchRecords(){
        this.filters.add("vending machine");
        this.filters.add(this.area);
        DatabaseReference id = this.record_ref.push();
        this.record_ref.child(id.getKey()).setValue(new Search_Record(this.username, new Date(), this.filters, this.search_result));

    }
}