package com.example.remindtodo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView tasks;
    ArrayList<MyTask> list;
    TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //working with data
        tasks = findViewById(R.id.tasks);
        tasks.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<MyTask>();

        //get data from firebase
        reference = FirebaseDatabase.getInstance().getReference().child("ToDoApp");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //set code to receive data and replace layout
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    MyTask p =dataSnapshot.getValue(MyTask.class);
                    list.add(p);
                }
                taskAdapter = new TaskAdapter(MainActivity.this,list);
                tasks.setAdapter(taskAdapter);
                taskAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //set code to show error
                Toast.makeText(getApplicationContext(),"NoData",Toast.LENGTH_SHORT).show();
            }
        });
    }
}