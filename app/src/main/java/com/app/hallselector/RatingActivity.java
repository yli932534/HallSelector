package com.app.hallselector;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.app.hallselector.model.Building;
import com.app.hallselector.model.Rating_Record;
import com.app.hallselector.model.Search_Record;
import com.app.hallselector.model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class RatingActivity extends AppCompatActivity {

    SeekBar bar;
    int user_rating = 0;
    TextView score;
    TextView comment;
    String building_name;
    DatabaseReference rating_ref;
    List<Rating_Record> display;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        this.score = findViewById(R.id.rating_num);
        this.comment = findViewById(R.id.comment);
        Intent intent = getIntent();
        this.building_name = intent.getStringExtra("building");

        this.bar = (SeekBar) findViewById(R.id.rating);
        bar.setMax(5);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int value = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                value = i;
                score.setText(Integer.toString(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                value = user_rating;
                score.setText(Integer.toString(value));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                user_rating = value;
                score.setText(Integer.toString(user_rating));
            }
        });

        this.rating_ref = FirebaseDatabase.getInstance().getReference().child("rating_records");
        loadRatingsAndComments();
    }

    public void onClickSendRatingAndComment(View view){
        SharedPreferences store = getSharedPreferences("username", Context.MODE_PRIVATE);
        String username = store.getString("username", "unknown user");
        String user_comment = comment.getText().toString();

        Rating_Record record = new Rating_Record(username, this.user_rating, user_comment, this.building_name);
        DatabaseReference id = this.rating_ref.push();
        synchronized (this){
            this.rating_ref.child(id.getKey()).setValue(record);
            loadRatingsAndComments(); //reload
        }

    }

    private void loadRatingsAndComments() {
        List<Rating_Record> re = new LinkedList<>();
        this.rating_ref.orderByChild("building").equalTo(this.building_name)
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Rating_Record temp = snapshot.getValue(Rating_Record.class);
                        re.add(temp);
                        Log.d("rating", temp.toString());
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        this.rating_ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                display = re;
                recyclerView = findViewById(R.id.ratings_and_comments);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                RatingAdapter adapter = new RatingAdapter(re);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    private static class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.ViewHolder> {

        private static class ViewHolder extends RecyclerView.ViewHolder {

            private TextView username;
            private TextView date;
            private TextView rating_number;
            private TextView comment;

            public ViewHolder(View view){
                super(view);
                this.username = (TextView)view.findViewById(R.id.user_name_rating);
                this.date = (TextView)view.findViewById(R.id.date_rating);
                this.rating_number = (TextView)view.findViewById(R.id.rating_number);
                this.comment = (TextView)view.findViewById(R.id.comment_rating);
            }

            public TextView getUsername() {
                return username;
            }

            public TextView getDate() {
                return date;
            }

            public TextView getRating_number() {
                return rating_number;
            }

            public TextView getComment() {
                return comment;
            }


        }

        private List<Rating_Record> ratings_and_comments;

        public RatingAdapter (List<Rating_Record> ratings_and_comments){
            this.ratings_and_comments = ratings_and_comments;
        }

        @NonNull
        @Override
        public RatingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_ratings_comment, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.getUsername().setText("Post by: " + this.ratings_and_comments.get(position).getUsername());
            holder.getRating_number().setText("Rating: " + Integer.toString(this.ratings_and_comments.get(position).getScore()));
            holder.getDate().setText(this.ratings_and_comments.get(position).getDate().toString());
            holder.getComment().setText(this.ratings_and_comments.get(position).getComment());
        }


        @Override
        public int getItemCount() {
            return this.ratings_and_comments.size();
        }
    }
}