package edu.illinois.cs.cs125.simplereminder;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
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
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import edu.illinois.cs.cs125.lib.*;

public class MainActivity extends AppCompatActivity {

    public static final String GROUP_ID = "Push Notification";

    /**
     * Declaring the floating action button in the layout
     */
    private FloatingActionButton fab;

    /**
     * Declaring the list view in the layout
     */
    private ListView taskList;

    private Timer timer = new Timer("Notification push");

    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            pushNotification();
        }
    };

    /**
     * ArrayList restore key
     */
    private final static String RESTORE_KEY = "TaskStorage";

    /**
     * General Channel ID for notification
     */
    //todo:  what is channel ID?
    private final static String CHANNEL_ID = "Push due task";

    //public static final String CHANNEL_ID_FOREGROUND = "simpleList Foreground";

    /**
     * The general array adapter
     */
    private static ListAdapter taskArrayAdapter;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //Intent startMyService = new Intent(MainActivity.this, MyService.class);
        //startService(startMyService);

        //startService();


        restoreTaskStorage();
        Log.d(TAG, "List restored");
        refreshTaskArrayAdapter();
        Log.d(TAG, "List refreshed");


        timer.schedule(timerTask, 10000);

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

    protected void pushNotification() {
        List<Task> toShow = TaskHelper.isAtTheTime();
        for (Task task: toShow) {
            Notification pushDueTask = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_push_notification)
                    .setContentTitle(task.getTaskName() + "is due!")
                    .setContentText(task.getNotificationToString())
                    .setGroup(GROUP_ID)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    //.setContentIntent(this.navigateToMainActivity)
                    .build();
            Log.d(TAG, "notification pushed!");
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
