package com.example.hedgehog.receiverhome;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ProgressBar pb;
    Button bStart;
    static final String start = "Start";
    static final String stop = "Stop";
    MyReceiver receiver;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb = (ProgressBar) findViewById(R.id.pb);
        pb.setProgress(0);
        bStart = (Button) findViewById(R.id.bStart);
        bStart.setOnClickListener(this);
        receiver = new MyReceiver(pb,bStart);
        intentFilter = new IntentFilter(MyReceiver.key);
        registerReceiver(receiver, intentFilter);



    }

    @Override
    public void onClick(View v) {

        Intent i = new Intent(this, MyService.class);
        i.putExtra("progress", pb.getProgress());

        switch(bStart.getText().toString()){
            case start:
                bStart.setText(stop);
                startService(i);




                break;
            case stop:
                bStart.setText(start);
                stopService(i);

                break;

        }

    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}
