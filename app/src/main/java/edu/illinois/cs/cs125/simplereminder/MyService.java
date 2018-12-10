package edu.illinois.cs.cs125.simplereminder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.List;
import java.util.ArrayList;
import java.util.Timer;

import edu.illinois.cs.cs125.lib.*;

public class MyService extends Service {

    /**
     * Default constructor that takes no arguments
     */
    public MyService() { }

    /**
     * Declaring the notification push component
     */
    private NotificationCompat.Builder taskDue;

    private Timer pushNotificatoin = new Timer("Push Notification", );

    private final static String TAG = "MyService";

    private final static String CHANNEL_ID = "Push due task";

    private final static String NOTIFICATION_TITLE = "You have a new task due!";

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        //Initialise the notification push component
        taskDue = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_push_notification)
                .setContentTitle(NOTIFICATION_TITLE)
                .setContentText(getDueTaskText())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(getDueTaskText()))
                .setAutoCancel(true);
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
