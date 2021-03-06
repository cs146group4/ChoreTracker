package com.micahdemong.choretracker;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView rv;
    private TextView emptyView;
    public static DataSystem dataSys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        emptyView = (TextView) findViewById(R.id.empty_view);

        //Initialize the Recycler View
        rv = (RecyclerView) findViewById(R.id.rv);
        registerForContextMenu(rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        initializeTaskData();
        initializeAdapter();
        setContentVisibility();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent newTaskIntent = new Intent(view.getContext(), NewTaskActivity.class);
                startActivity(newTaskIntent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initializeTaskData() {

        dataSys = new DataSystem();
        dataSys.loadTasks(getApplicationContext());

    }

    private void initializeAdapter() {
        TaskAdapter adapter = new TaskAdapter(dataSys.getTasks());
        rv.setAdapter(adapter);
    }

    private void setContentVisibility() {
        if (dataSys.getTasks().isEmpty()) {
            rv.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            rv.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete_all_tasks) {
            showDialog();
            return true;
        } else if (id == R.id.action_demo_addtasks) {
            addDemoTasks();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addDemoTasks() {
        Task t1 = new Task("Do Dishes", "Do all the dishes and put them in the dishwasher.", false, 0);
        Task t2 = new Task("Clean Bathroom", "Wipe off counters, clean shower, clean toilet bowl.", false, 2);
        Task t3 = new Task("Vacuum Floor", "Bedroom, living room, and hallway all need to be vacuumed.", false, 2);
        Task t4 = new Task("Do Laundry", "", false, 5);
        dataSys.addToList(t1);
        dataSys.addToList(t2);
        dataSys.addToList(t3);
        dataSys.addToList(t4);
        setContentVisibility();
        initializeAdapter();
    }

    void showDialog() {
        DialogFragment newFragment = deleteAllDialogFragment.newInstance
                (R.string.alert_dialog_two_buttons_title);
        newFragment.show(getSupportFragmentManager(), "dialog");
    }

    public void doDeleteAllTasks() {
        dataSys.deleteAllTasks(getApplicationContext());
        TaskAdapter t = (TaskAdapter) rv.getAdapter();
        t.deleteAllTasks();
        Log.i("FragmentAlertDialog", "Deleted all tasks.");
        setContentVisibility();
    }

    public void doNegativeClick() {
        Log.i("FragmentAlertDialog", "Negative click!");
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.user_settings) {
            //Open User Settings Screen
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
        } else if (id == R.id.about) {
            //Open About Screen
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static class deleteAllDialogFragment extends DialogFragment {

        public static deleteAllDialogFragment newInstance(int title) {
            deleteAllDialogFragment frag = new deleteAllDialogFragment();
            Bundle args = new Bundle();
            args.putInt("title", title);
            frag.setArguments(args);
            return frag;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            int title = getArguments().getInt("title");

            return new AlertDialog.Builder(getActivity())
                    .setTitle(title)
                    .setMessage("Are you sure you want to delete all tasks? This cannot be undone.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ((MainActivity) getActivity()).doDeleteAllTasks();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ((MainActivity) getActivity()).doNegativeClick();
                        }
                    })
                    .create();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        dataSys.saveTasks(getApplicationContext());
    }
}
