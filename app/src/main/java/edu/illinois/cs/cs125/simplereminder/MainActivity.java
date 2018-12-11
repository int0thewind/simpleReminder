package edu.illinois.cs.cs125.simplereminder;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import edu.illinois.cs.cs125.lib.*;

public class MainActivity extends AppCompatActivity {
    /**
     * Declaring the floating action button in the layout
     */
    private FloatingActionButton fab;

    /**
     * Declaring the list view in the layout
     */
    private ListView taskList;

    /**
     * The general array adapter
     */
    private static ListAdapter taskArrayAdapter;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        refreshTaskArrayAdapter();

        fab = findViewById(R.id.changeActivity);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Clicked add-task button");
                Intent jumpToAddTaskActivity = new Intent(MainActivity.this, AddTask.class);
                startActivity(jumpToAddTaskActivity);
                Log.d(TAG, "jumped to add task activity");
                Log.d(TAG, "activity finished");
                finish();
            }
        });
    }

    protected void refreshTaskArrayAdapter() {
        this.taskList = findViewById(R.id.task_list);
        taskArrayAdapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1, TaskHelper.getAllTask());
        taskList.setAdapter(taskArrayAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
