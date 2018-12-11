
package edu.illinois.cs.cs125.simplereminder;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.util.List;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import edu.illinois.cs.cs125.lib.*;

public class MyService extends Service {

    /**
     * Default constructor for the activity.
     */
    public MyService() { }

    /**
     * A reference to this service itself.
     * This prevent that when the service is inside the timer task,
     * it cannot read the service itself
     */
    Service myService = this;

    /**
     * Notification component;
     */
    private Notification pushDueTask;

    /**
     * General Channel ID for notification
     */
    //todo:  what is channel ID?
    private final static String CHANNEL_ID = "Push due task";

    /**
     * Debug tag
     */
    private final static String TAG = "MyService";

    /**
     * A helper function to initialise the notification components
     */
    public void initialiseNotification() {
        pushDueTask = new NotificationCompat.Builder(myService, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_push_notification)
                .setContentTitle()
                .setContentText()
                .setGroup()
                .build();
    }

    public void generateStrings

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        //Initialise the notification push component

    }

    /**
     * A helper function to show whether there are Tasks overdue
     * @return true if there are tasks overdue, false otherwise
     */

    protected boolean hasTaskAtTheTime() {
        List<Task> toShow = TaskHelper.isAtTheTime();
        Log.d(TAG, "get a list of due task");
        if (toShow == null || toShow.size() == 0) {
            Log.d(TAG, "no item due");
            return false;
        }
        Log.d(TAG, "have item due");
        return true;
    }

    /**
     * A helper function to generate the notification text about the task overdue in text;
     * @return the string to show in notification text bar
     */
    protected String getDueTaskText() {
        if (hasTaskAtTheTime()) {
            List<Task> toShow = TaskHelper.isAtTheTime();
            Log.d(TAG, "get a list of due task");
            String dueTaskText = "";
            if (toShow == null) {
                return dueTaskText;
            }
            for (Task task : toShow) {
                dueTaskText += task.toString() + "; ";
            }
            Log.d(TAG, "notification text returned");
            return dueTaskText;
        }
        Log.d(TAG, "notification error!!!");
        return "";
    }
}

