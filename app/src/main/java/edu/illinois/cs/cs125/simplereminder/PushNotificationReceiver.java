package edu.illinois.cs.cs125.simplereminder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import edu.illinois.cs.cs125.lib.*;

public class PushNotificationReceiver extends BroadcastReceiver {

    private static final String TAG = "PushNotificationJob";

    /**
     * General Channel ID for notification
     */
    //todo:  what is channel ID?
    private final static String CHANNEL_ID = "Push due task";

    private final static String GROUP_ID = "PushNotificationReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder pushDueTask = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_push_notification)
                .setContentTitle("You have a task due!")
                .setGroup(GROUP_ID)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(1, pushDueTask.build());


        Log.d(TAG, "notification pushed!");
    }
}
