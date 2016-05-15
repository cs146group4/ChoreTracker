package com.micahdemong.choretracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class NewTaskActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private int daysRemaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task_main);

        Spinner dateSpinner = (Spinner) findViewById(R.id.date_choose_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, Task.dueDateArray());
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        dateSpinner.setAdapter(adapter);
        dateSpinner.setOnItemSelectedListener(this);
    }

    public void createTask(View view) {
        EditText title = (EditText) findViewById(R.id.new_task_title_edit_text);
        EditText description = (EditText) findViewById(R.id.new_task_desc_edit_text);
        String titleText = title.getText().toString();
        String descText = description.getText().toString();

        DataSystem d = new DataSystem();
        Task t = new Task(titleText,descText,false,daysRemaining);
        d.createTask(titleText, descText, false, getApplicationContext());

        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        daysRemaining = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //do nothing
    }
}
