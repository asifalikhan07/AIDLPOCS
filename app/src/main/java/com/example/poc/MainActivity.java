package com.example.poc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aidl.IRemoteServiceBinder.IRemoteServiceBinder;
import com.aidl.IRemoteServiceBinder.Rect;
import com.aidl.services.RectService;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private IRemoteServiceBinder service;
    private RemoteServiceConnection serviceConnection;
    private boolean flag;
    private Button fetchData;
    private Button checkConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectService();
        checkConnection = (Button) findViewById(R.id.checkConnection);
        checkConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    Toast.makeText(MainActivity.this, "Service is connected", Toast.LENGTH_LONG)
                            .show();
                } else {
                    Toast.makeText(MainActivity.this, "Service is not connected", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        fetchData = (Button) findViewById(R.id.fetchdata);
        fetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData();
            }
        });


    }

    private void connectService() {
        serviceConnection = new RemoteServiceConnection();
        Intent i = new Intent(this, RectService.class);
        i.setPackage("com.aidl.services");
        boolean ret = getApplicationContext().bindService(i, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    class RemoteServiceConnection implements ServiceConnection {

        public void onServiceConnected(ComponentName name, IBinder boundService) {
            service = IRemoteServiceBinder.Stub.asInterface((IBinder) boundService);
            flag = true;

        }

        public void onServiceDisconnected(ComponentName name) {
            service = null;
            flag = false;
        }
    }

    private void showData() {

        if (service != null) {
            try {
                long time = service.getCurrentTime();
                Rect rect = service.getRect();
                List<Rect> rectList = service.getRectList();
                Toast.makeText(MainActivity.this, "    Rect =   " + rect +
                        "        List of Rect = " + rectList, Toast.LENGTH_LONG)
                        .show();

            } catch (Exception e) {
                Log.e(TAG, "e = " + e);

            }
        }
    }

}