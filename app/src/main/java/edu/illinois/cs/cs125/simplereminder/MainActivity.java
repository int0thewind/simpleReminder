package edu.illinois.cs.cs125.simplereminder;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.provider.SyncStateContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

    public static final String CHANNEL_ID_FOREGROUND = "simpleList Foreground";

    /**
     * The general array adapter
     */
    private static ListAdapter taskArrayAdapter;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Intent startMyService = new Intent(MainActivity.this, MyService.class);
        startService(startMyService);

        startService();
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
            }
        });

        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TaskHelper.setFinish(position);
                refreshTaskArrayAdapter();
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

    protected void startService() {
        Intent startForegroundService = new Intent(this, MyService.class);
        PendingIntent pendingIntent =PendingIntent.getActivity(this, 0,
                startForegroundService, 0);

        Notification foregroundNotification = new Notification.Builder(this, CHANNEL_ID_FOREGROUND)
                .setContentTitle("simpleList is running")
                .setContentTitle("Press to start simpleList")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                //.setTicker()
                .build();


            startForegroundService(foregroundNotification);

    }

    protected void refreshTaskArrayAdapter() {
        this.taskList = findViewById(R.id.task_list);
        taskArrayAdapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1, TaskHelper.getAllTask());
        taskList.setAdapter(taskArrayAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshTaskArrayAdapter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent startMyService = new Intent(MainActivity.this, MyService.class);
        startService(startMyService);
    }

}
