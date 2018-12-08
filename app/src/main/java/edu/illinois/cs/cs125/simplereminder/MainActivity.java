package edu.illinois.cs.cs125.simplereminder;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    public final static String DEBUG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        final FloatingActionButton addTask = findViewById(R.id.addTask);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jumpToAddTaskActivity = new Intent(MainActivity.this, AddTask.class);
                startActivity(jumpToAddTaskActivity);
                Log.d(DEBUG_TAG, "float button on the Main Activity clicked");
            }
        });

    }
}
