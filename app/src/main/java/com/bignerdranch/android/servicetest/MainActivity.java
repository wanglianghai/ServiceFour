package com.bignerdranch.android.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bignerdranch.android.servicetest.service.MyService;

public class MainActivity extends AppCompatActivity {
    private Button mButtonStart;
    private Button mButtonStop;
    private Button mButtonBind;
    private Button mButtonUnbind;
    private MyService.DownloadBinder mBinder;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (MyService.DownloadBinder) service;
            mBinder.startDownload();
            mBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonStart = (Button) findViewById(R.id.start_service);
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startService = new Intent(MainActivity.this, MyService.class);
                startService(startService);
            }
        });

        mButtonStop = (Button) findViewById(R.id.stop_service);
        mButtonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stopService = new Intent(MainActivity.this, MyService.class);
                stopService(stopService);
            }
        });

        mButtonBind = (Button) findViewById(R.id.bind_service);
        mButtonBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bindIntent = new Intent(MainActivity.this, MyService.class);
                bindService(bindIntent, mConnection, BIND_AUTO_CREATE);
            }
        });

        mButtonUnbind = (Button) findViewById(R.id.unbind_service);
        mButtonUnbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(mConnection);
            }
        });
    }
}
