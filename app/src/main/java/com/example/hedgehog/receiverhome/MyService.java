package com.example.hedgehog.receiverhome;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyService extends Service {

    int progress;
    MyThread thread;
    public static boolean isServiceOn;



    @Override
    public void onCreate() {
        super.onCreate();
        isServiceOn = true;
        thread = new MyThread();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        progress = intent.getIntExtra("progress", 0);
        if (!thread.isRunningNow) {
            thread.start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        thread.stopMe();
        isServiceOn = false;
    }



    class MyThread extends Thread
    {
        volatile boolean finished = false;
        public boolean isRunningNow;

        public void stopMe()
        {
            finished = true;
        }



        public void run()
        {
            isRunningNow = true;

            while (!finished) {
                if (progress < 100){
                   // Log.d("dfdf1", Integer.toString(progress));


                    try {
                        sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progress +=1;
                    Intent i = new Intent(MyReceiver.key);
                    i.putExtra("progress", progress);
                    i.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                    sendBroadcast(i);
                }else {
                    finished = true;
                    break;
                }
            }
            isRunningNow = false;
            stopSelf();
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
