package edu.illinois.cs.cs125.simplereminder;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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
     * The public array adapter for the list view
     */
    private static ArrayAdapter<Task> taskArrayAdapter;

    /**
     * The debug tag
     */
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //Initialise the service - by helper function
        runService();

        //Initialising the button
        fab = findViewById(R.id.changeActivity);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Clicked add-task button");
                Intent jumpToAddTaskActivity = new Intent(MainActivity.this, AddTask.class);
                startActivity(jumpToAddTaskActivity);
                Log.d(TAG, "jumped to add task activity");
            }
        });

        //Initialising the List View
        //todo list view creation

    }

    /**
     * A helper function to start the background service
     */
    protected void runService() {
        Intent startService = new Intent(MainActivity.this, MyService.class);
        startService(startService);
        Log.d(TAG, "service initialised");
    }
}
