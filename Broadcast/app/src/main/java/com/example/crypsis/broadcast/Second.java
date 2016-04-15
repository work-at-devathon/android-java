package com.example.crypsis.broadcast;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;


public class Second extends Service {
    final static String MY_ACTION1 = "MY_ACTION";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"second service",Toast.LENGTH_SHORT).show();
Intent i=new Intent();
        String s1=intent.getStringExtra("value1");
        String s2=intent.getStringExtra("value2");
        int a=Integer.parseInt(s1);
        int b=Integer.parseInt(s2);
        int sum=a+b;
        i.putExtra("res",sum);
        i.setAction(MY_ACTION1);
        sendBroadcast(i);
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service2 Destroyed", Toast.LENGTH_SHORT).show();

    }
}
