package edu.illinois.cs.cs125.simplereminder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import edu.illinois.cs.cs125.lib.*;

public class MyService extends Service {

    private static final String CHANNEL_ID = "simpleReminderNotification";

    public MyService() {
        NotificationCompat.Builder = new NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle("You have a")
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
