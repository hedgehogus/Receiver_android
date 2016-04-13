package com.example.hedgehog.receiverhome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.ProgressBar;

public class MyReceiver extends BroadcastReceiver {
    static final String key = "my receiver";
    ProgressBar pb;
    Button button;

    public MyReceiver(ProgressBar pb, Button bStart){
        this.pb = pb;
        this.button = bStart;


    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int progress = intent.getIntExtra("progress",0);
        pb.setProgress(progress);
        if (progress==100){
            button.setText(MainActivity.start);
            pb.setProgress(0);
        }

    }
}
