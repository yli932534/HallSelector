package com.app.hallselector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.hallselector.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.usernameInput = (EditText)(findViewById(R.id.username_input));
        this.passwordInput = (EditText)(findViewById(R.id.password_input));
        this.db = FirebaseDatabase.getInstance().getReference(); //get a reference of database

        SharedPreferences store = getSharedPreferences("bk_color", Context.MODE_PRIVATE);
        int color = store.getInt("bk_color", Color.parseColor("#FFE4E9"));
        findViewById(R.id.login_layout).setBackgroundColor(color);
    }

    public void openMain(View view){
        boolean toMainPage = false;
        String username = usernameInput.getText().toString(); //get username & password
        String password = passwordInput.getText().toString();

        if (username.isEmpty()){
            Toast.makeText(getApplicationContext(), "username is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (password.isEmpty()){
            Toast.makeText(getApplicationContext(), "password is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        //DatabaseReference db = FirebaseDatabase.getInstance().getReference(); //get a reference of database
        this.db.child("users").child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    //error, we might want to prompt a toast
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    boolean toMainPage = false;
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    Map<String, User> map = (Map<String, User>) task.getResult().getValue();
                    //Log.d("Login", map.toString());
                    //Log.d("Login", Integer.toString(map.size()));

                    User user = task.getResult().getValue(User.class);
                    //Log.d("Login", user.toString());
                    if (user == null){
                        // the username doesn't exist in db
                        // need to register
                        register(username, password);
                        toMainPage = true;
                        Toast.makeText(getApplicationContext(), "successfully registered", Toast.LENGTH_SHORT).show();
                    }
                    else
                    { //the username exists in db
                        //need to login
                        boolean verify = user.getPassword().equals(password);
                        //check if the entered password == the password in db
                        toMainPage = verify;
                        Log.d("Login", user.getPassword());
                        Log.d("Login", password);
                        Log.d("Login", Boolean.toString(verify));
                        Log.d("Login", Boolean.toString(toMainPage));

                    }


                    if (toMainPage){
                        Toast.makeText(getApplicationContext(), "successfully log in", Toast.LENGTH_SHORT).show();
                        SharedPreferences store = getSharedPreferences("username", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = store.edit();
                        editor.putString("username", username);
                        editor.commit();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);  //go to main page (activity)
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Please check your username/password", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

//        ValueEventListener userListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                //User user = dataSnapshot.getValue(User.class);
//                Map<String, User> map = (Map<String, User>) dataSnapshot.getValue();
//                //Log.w("firebase", user.toString());
//                Log.w("firebase", map.toString());
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//                Log.w("firebase", "loadPost:onCancelled", databaseError.toException());
//            }
//        };
//        db.child("users").child(username).addValueEventListener(userListener);


        //db.child("users").child("user3").setValue(new User("name", "pswd", "id1"));


    }

    private void register(String username, String password){
        this.db.child("users").child(username).setValue(new User(username, password, "1"));
    }



    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected  void onPause() {

        super.onPause();
    }

    @Override
    protected  void onStop() {
        super.onStop();
    }

    @Override
    protected  void onRestart() {
        super.onRestart();
    }

    @Override
    protected  void onDestroy() {
        super.onDestroy();
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