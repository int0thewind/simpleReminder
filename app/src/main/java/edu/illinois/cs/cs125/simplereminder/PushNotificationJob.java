package edu.illinois.cs.cs125.simplereminder;

import android.app.Notification;
import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.List;

import edu.illinois.cs.cs125.lib.*;

public class PushNotificationJob extends JobService {

    private static final String TAG = "PushNotificationJob";

    private Service myService = this;

    /**
     * General Channel ID for notification
     */
    //todo:  what is channel ID?
    private final static String CHANNEL_ID = "Push due task";

    private final static String GROUP_ID = "PushNotificationJob";

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "JobStarted");

        startNotificationPush(params);

        //TODO: Do I need to return true?
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    protected void startNotificationPush(JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
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
        }).start();
    }
}
