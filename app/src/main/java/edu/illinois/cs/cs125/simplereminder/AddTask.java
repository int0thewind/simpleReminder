package edu.illinois.cs.cs125.simplereminder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import edu.illinois.cs.cs125.lib.*;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);

        Button addTask = findViewById(R.id.addTask);
        addTask.setOnClickListener(new );

    }


    protected Task createNewTask(String taskName) {

    }
}
