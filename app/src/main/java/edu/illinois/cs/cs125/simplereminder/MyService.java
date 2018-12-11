
package edu.illinois.cs.cs125.simplereminder;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.ViewCompat;
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
     * General Channel ID for notification
     */
    //todo:  what is channel ID?
    private final static String CHANNEL_ID = "Push due task";

    /**
     * The group notification ID, used for grouping a set of notification.
     */
    private final static String GROUP_ID = "Push due task";

    /**
     * Debug tag
     */
    private final static String TAG = "MyService";

    private Timer timer = new Timer("Push Notification");

    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            if (hasTaskAtTheTime()) {
                createNotification();
                Log.d(TAG, "timer run");
            }
        }
    };

    private Notification foregroundNotification;

    Intent foreGroundIntent = new Intent(this, MainActivity.class);

    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, foreGroundIntent, 0);

    //private Intent intent = new Intent(myService, MainActivity.class);

    //private PendingIntent navigateToMainActivity = PendingIntent.getActivity(this, 0, intent, 0);


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        this.timer.scheduleAtFixedRate(this.timerTask, 60000, 60000);
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

    protected void createNotification() {
        List<Task> toShow = TaskHelper.isAtTheTime();
        for (Task task: toShow) {
            Notification pushDueTask = new NotificationCompat.Builder(myService, CHANNEL_ID)
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
}

