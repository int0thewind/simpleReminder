package edu.illinois.cs.cs125.simplereminder;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

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
     * ArrayList restore key
     */
    private final static String RESTORE_KEY = "TaskStorage";

    /**
     * General Channel ID for notification
     */
    private final static String CHANNEL_ID = "Push due task";

    /**
     * The general array adapter
     */
    private static ListAdapter taskArrayAdapter;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        restoreTaskStorage();
        Log.d(TAG, "List restored");
        refreshTaskArrayAdapter();
        Log.d(TAG, "List refreshed");
        createNotificationChannel();
        Log.d(TAG, "Notification Channel initialised");

        fab = findViewById(R.id.changeActivity);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Clicked add-task button");
                Intent jumpToAddTaskActivity = new Intent(MainActivity.this, AddTask.class);
                startActivity(jumpToAddTaskActivity);
                Log.d(TAG, "jumped to add task activity");
                Log.d(TAG, "activity finished");
            }
        });

        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TaskHelper.reverseFinish(position);
                refreshTaskArrayAdapter();
                Log.d(TAG, "A task has its finish state changed");
            }
        });

        taskList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TaskHelper.getAllTask().remove(position);
                refreshTaskArrayAdapter();
                return true;
            }
        });
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            //Initialise all the reference
            CharSequence name = "Push Due Task";
            String description = "When a task is due, push an alert";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            //put all thouse reference above to create a new channel
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    protected void saveArrayList(ArrayList<Task> list, String key){
        SharedPreferences prefs = getSharedPreferences(key, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
        Log.d(TAG, "saving helper function finished");
    }

    protected ArrayList<Task> getArrayList(String key){
        SharedPreferences prefs = getSharedPreferences(key, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<Task>>() {}.getType();
        ArrayList<Task> toReturn = gson.fromJson(json, type);
        Log.d(TAG, "getting helper function finished");

        if (toReturn == null) {
            toReturn = new ArrayList<>();
            Log.d(TAG, "saving helper function finished with null detected");
        }
        return toReturn;
    }

    protected void restoreTaskStorage() {
        if (getArrayList(RESTORE_KEY) == null) {
            TaskHelper.restoreAllTask(new ArrayList<Task>());
        }
        TaskHelper.restoreAllTask(getArrayList(RESTORE_KEY));
    }

    protected void refreshTaskArrayAdapter() {
        this.taskList = findViewById(R.id.task_list);
        taskArrayAdapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1, TaskHelper.getAllTask());
        taskList.setAdapter(taskArrayAdapter);
        Log.d(TAG, "refresh helper function finished");
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshTaskArrayAdapter();
        Log.d(TAG, "List refreshed");
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveArrayList(TaskHelper.getAllTask(), RESTORE_KEY);
        Log.d(TAG, "List saved");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveArrayList(TaskHelper.getAllTask(), RESTORE_KEY);
        Log.d(TAG, "List saved");
    }

}
