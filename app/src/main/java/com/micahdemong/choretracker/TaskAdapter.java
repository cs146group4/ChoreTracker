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

/*


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>
{
    private String[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ViewHolder(TextView v){
            super(v);
            mTextView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TaskAdapter(String[] myDataSet) {
        mDataset = myDataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
*/

