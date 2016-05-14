package com.micahdemong.choretracker;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{

    ArrayList<Task> taskList;

    TaskAdapter(ArrayList<Task> taskList){
        this.taskList = taskList;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView taskName;
        TextView taskDescription;

        TaskViewHolder(View itemView){
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            taskName = (TextView)itemView.findViewById(R.id.task_name_text_view);
            taskDescription = (TextView)itemView.findViewById(R.id.task_description_text_view);
        }
    }

    @Override
    public int getItemCount() { return taskList.size(); }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new TaskViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder tvh, int i) {
        Task t = taskList.get(i);
        tvh.taskName.setText(t.getName());
        tvh.taskDescription.setText(t.getDescription());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) { super.onAttachedToRecyclerView(recyclerView); }
}