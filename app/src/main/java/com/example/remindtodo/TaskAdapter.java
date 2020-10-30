package com.example.remindtodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    Context context;
    ArrayList<MyTask> myTasks;

    public TaskAdapter(Context c,ArrayList<MyTask> p){
        context = c;
        myTasks = p;
    }

   class MyViewHolder extends RecyclerView.ViewHolder {
         TextView title,desc,date;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.text);
            desc = (TextView) itemView.findViewById(R.id.desc);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.task,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(myTasks.get(position).getTitle());
        holder.desc.setText(myTasks.get(position).getDesc());
        holder.date.setText(myTasks.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return myTasks.size();
    }
}
