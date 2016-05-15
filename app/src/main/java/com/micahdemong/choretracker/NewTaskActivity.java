package com.micahdemong.choretracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewTaskActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task_main);
    }

    public void createTask(View view) {
        EditText title = (EditText) findViewById(R.id.new_task_title_edit_text);
        EditText description = (EditText) findViewById(R.id.new_task_desc_edit_text);
        String titleText = title.getText().toString();
        String descText = description.getText().toString();
        DataSystem d = new DataSystem();
        d.createTask(titleText, descText, false, this);
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}
