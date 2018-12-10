package edu.illinois.cs.cs125.simplereminder;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import edu.illinois.cs.cs125.lib.*;

public class AddTask extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    public final static String TAG = "AddTask";

    private Button addTask = findViewById(R.id.addTask);

    private Button setDate = findViewById(R.id.set_date);

    private EditText titleInput = findViewById(R.id.new_task_title);

    private TextView datePreview = findViewById(R.id.date_preview);

    private TextView timePreview = findViewById(R.id.time_preview);

    private Calendar c = Calendar.getInstance();


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

                //Create new task instance
                String taskTitle;
                if (titleInput.getText() == null || titleInput.getText().toString().trim().length() == 0) {
                    taskTitle = "New Task"; //input 为连续空格、空、或null时，自动命名为New Task
                    Log.d(TAG, "Invalid title");
                } else {
                    taskTitle = titleInput.getText().toString();
                    Log.d(TAG, "Valid title");
                }
                Task task = new Task(taskTitle);

                //todo set the time


                TaskStorage.getStorage().add(task);
                //Jump back to the main activity
                Intent jumpBackToMainActivity = new Intent(AddTask.this, MainActivity.class);
                startActivity(jumpBackToMainActivity);
                Log.d(TAG, "jump back to main activity");
                //todo what is context?
                //Toast.makeText(, "Task " + taskTitle + " added!");
                Log.d(TAG, "toast message sent");
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
}
