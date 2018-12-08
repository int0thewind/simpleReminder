package edu.illinois.cs.cs125.simplereminder;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import edu.illinois.cs.cs125.lib.*;

public class AddTask extends AppCompatActivity {

    public final static String TAG = "AddTask";

    private Button addTask = findViewById(R.id.addTask);
    private Button setDate = findViewById(R.id.set_date);

    private EditText titleInput = findViewById(R.id.new_task_title);

    private TextView datePreview = findViewById(R.id.date_preview);

    /**
     * OnCreate method initialised when this activity started
     * @param savedInstanceState the last state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);


        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo time and date picker

                //todo datePreview preview

                //todo debug log
            }
        });

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "add task pressed");

                //Create new task instance
                String taskTitle = titleInput.getText().toString();
                //todo 连续空格或者没有输入的正则表达式
                if (taskTitle.equals("\\ ") || taskTitle == null) {
                    taskTitle = "New Task";
                }
                Task task = new Task(taskTitle);
                //todo set the time

                //Jump back to the main activity
                Intent jumpBackToMainActivity = new Intent(AddTask.this, MainActivity.class);
                startActivity(jumpBackToMainActivity);
                Log.d(TAG, "jump back to main activity");
                //todo what is context?
                Toast.makeText(, "Task " + taskTitle + " added!");
                Log.d(TAG, "toast message sent");
            }
        });

    }

    /*@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
    }*/


}
