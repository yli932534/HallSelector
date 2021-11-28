package com.app.hallselector;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference db;
    private String username;  //TODO: needs to save the data before enter next activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w("MainActivity", "onCreate");
        Intent intent = getIntent();
        this.username = intent.getStringExtra("username");
        this.db = FirebaseDatabase.getInstance().getReference(); //get a reference of database

        if (savedInstanceState != null){
            this.username = savedInstanceState.getString("username");
            Log.d("save_username", "onRestoreCreate");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w("MainActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("MainActivity", "onResume");
    }

    @Override
    protected  void onPause() {

        super.onPause();
        Log.w("MainActivity", "onPause");
    }

    @Override
    protected  void onStop() {
        super.onStop();
        Log.w("MainActivity", "onStop");
    }

    @Override
    protected  void onRestart() {
        super.onRestart();
        Log.w("MainActivity", "onRestart");
    }

    @Override
    protected  void onDestroy() {
        super.onDestroy();
        Log.w("MainActivity", "onDestroy");
    }

    public void onClickDeleteAccount(View view){
        Log.d("save_username", Boolean.toString(this.username == null));
        this.db.child("users")
                .child(this.username)
                .removeValue();
        this.finish();
    }

    public void onClickChangePswd(View view){
        Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
        intent.putExtra("username", this.username);
        startActivity(intent);
    }


    public void onClickNewSearch(View view){
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        intent.putExtra("username", this.username);
        startActivity(intent);
    }

    //Yi's implementation -- building intent to connect search activity and record activity
    public void onClickNewRecord(View view){
        Intent intent = new Intent(MainActivity.this, RecordActivity.class);
        intent.putExtra("username", this.username);
        startActivity(intent);
    }

    public void onClickNewVideo(View view){
        Intent intent = new Intent(MainActivity.this, VideoActivity.class);
        startActivity(intent);
    }


    public void onClickNewMap(View view){
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        Log.w("MainActivity", "onSave");
        savedInstanceState.putString("username", this.username);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.w("MainActivity", "onRestore");
        this.username = savedInstanceState.getString("username");
    }
}