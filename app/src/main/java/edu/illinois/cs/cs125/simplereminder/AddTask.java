package edu.illinois.cs.cs125.simplereminder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import edu.illinois.cs.cs125.lib.*;

public class AddTask extends AppCompatActivity {

    Button addTask = findViewById(R.id.addTask);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
    }


    protected Task createNewTask() {
        return null;
    }
}
