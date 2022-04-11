package ro.pub.cs.systems.eim.practicaltest01var06;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.internal.SafeIterableMap;

import java.util.Calendar;
import java.util.Date;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class PracticalTest01Var06Service extends Service {


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Started Service", "onCreate() method was invoked");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Started Service", "onBind() method was invoked");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("Started Service", "onUnbind() method was invoked");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d("Started Service", "onRebind() method was invoked");
    }

    @Override
    public void onDestroy() {
        Log.d("Started Service", "onDestroy() method was invoked");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Thread dedicatedThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.d("Started Service", "Thread.run() was invoked, PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    intent.setAction("Victory");
                    Date currentTime = Calendar.getInstance().getTime();
                    intent.putExtra("Date", currentTime.toString());
                    sendBroadcast(intent);
                }
            });
            dedicatedThread.start();
        }
        return START_REDELIVER_INTENT;
    }
}