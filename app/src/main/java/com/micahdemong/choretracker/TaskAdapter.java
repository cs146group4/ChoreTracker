package com.micahdemong.choretracker;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    ArrayList<Task> taskList;
    OnClickListener listener;

    TaskAdapter(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        CardView cv;
        TextView taskName;
        TextView taskDescription;
        ImageButton deleteTaskButton;
        Button checkTaskButton;
        public IMyViewHolderClicks mListener;

        TaskViewHolder(View itemView, IMyViewHolderClicks listener) {
            super(itemView);


            cv = (CardView) itemView.findViewById(R.id.cv);
            taskName = (TextView) itemView.findViewById(R.id.task_name_text_view);
            taskDescription = (TextView) itemView.findViewById(R.id.task_description_text_view);
            mListener = listener;

            deleteTaskButton = (ImageButton) itemView.findViewById(R.id.delete_task_button);
            checkTaskButton = (Button) itemView.findViewById(R.id.task_complete_button);
            deleteTaskButton.setOnClickListener(this);
            checkTaskButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v == deleteTaskButton)
                mListener.onDelTask((ImageButton) v);
            else if (v == checkTaskButton)
                mListener.onCheckTask((Button) v);
        }

        public interface IMyViewHolderClicks {
            void onDelTask(ImageButton caller);

            void onCheckTask(Button caller);
        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        TaskViewHolder vh = new TaskViewHolder(v,
                new TaskAdapter.TaskViewHolder.IMyViewHolderClicks() {
                    @Override
                    public void onDelTask(ImageButton caller) {
                        Log.i("TaskAdapter", "Called onDelTask!");
                    }

                    @Override
                    public void onCheckTask(Button caller) {
                        Log.i("TaskAdapter", "Called onCheckTask!");
                    }
                });
        return vh;
    }

    @Override
    public void onBindViewHolder(TaskViewHolder tvh, int i) {
        Task t = taskList.get(i);
        tvh.taskName.setText(t.getName());
        tvh.taskDescription.setText(t.getDescription());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}