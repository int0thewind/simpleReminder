package edu.illinois.cs.cs125.simplereminder;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import edu.illinois.cs.cs125.lib.Task;
import edu.illinois.cs.cs125.lib.TaskHelper;
import edu.illinois.cs.cs125.lib.TaskStorage;

public class AddTask extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private Button addTask;

    private Button setDate;

    private EditText titleInput;

    private TextView datePreview;

    private TextView timePreview;

    private java.util.Calendar c = java.util.Calendar.getInstance();

    public final static String TAG = "AddTask";

    /**
     * OnCreate method initialised when this activity started.
     * @param savedInstanceState the last state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);

        addTask = findViewById(R.id.addTask);

        setDate = findViewById(R.id.set_date);

        titleInput = findViewById(R.id.new_task_title);

        datePreview = findViewById(R.id.date_preview);

        timePreview = findViewById(R.id.time_preview);

        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "set date clicked");
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "add task pressed");

                String taskTitle;
                if (titleInput.getText() == null || titleInput.getText().toString().trim().length() == 0) {
                    taskTitle = "New Task";
                    Log.d(TAG, "Invalid title put in");
                } else {
                    taskTitle = titleInput.getText().toString();
                    Log.d(TAG, "Valid title put in");
                }
                Task task = new Task(taskTitle);
                Log.d(TAG, "new task added into storage");

                task.setNotification(c);
                Log.d(TAG, "notification set");

                TaskStorage.getStorage().add(task);
                Log.d(TAG, "task added to the storage");

                Intent jumpBackToMainActivity = new Intent(AddTask.this, MainActivity.class);
                jumpBackToMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(jumpBackToMainActivity, 0);
                Log.d(TAG, "jump back to main activity");

                Log.d(TAG, "activity finished");
                finish();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = java.text.DateFormat.getDateInstance(java.text.DateFormat.FULL).format(c.getTime());
        datePreview.setText(currentDateString);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE,minute);
        timePreview.setText(hourOfDay + " : " + minute);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
