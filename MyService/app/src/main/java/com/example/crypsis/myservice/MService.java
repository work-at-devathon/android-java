package com.example.crypsis.myservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class MService extends Service {
    PendingIntent pIntent;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        createNotification(1,R.drawable.ic_launcher,"Service Notification","Service Started");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_SHORT).show();
        createNotification(2, R.drawable.ic_launcher, "Service Notification", "Service Destroyed");
    }
    private void createNotification(int nId, int iconRes, String title, String body) {

        Notification noti = new NotificationCompat.Builder(this)
                        .setSmallIcon(iconRes)
                        .setContentTitle(title)
                        .setContentText(body)
                        .build();


        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(nId, noti);
    }

}
