package edu.illinois.cs.cs125.simplereminder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

public class MyService extends Service {

    /**
     * Default constructor that takes no arguments
     */
    public MyService() { }

    /**
     * Declaring the notification push component
     */
    private NotificationCompat.Builder taskDue;

    private final static String CHANNEL_ID = "888";

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
                .setContentTitle()
    }

    public
}
