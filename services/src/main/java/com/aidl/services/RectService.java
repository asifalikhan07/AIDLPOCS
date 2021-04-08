package com.aidl.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.aidl.IRemoteServiceBinder.IRemoteServiceBinder;
import com.aidl.IRemoteServiceBinder.Rect;

import java.util.ArrayList;
import java.util.List;

public class RectService extends Service {
    private Rect rect1;
    private List<Rect> rectList;

    @Override
    public void onCreate() {
        super.onCreate();
        //adding dummy data
        rect1 = new Rect(10, 30);
        Rect rect2 = new Rect(10, 30);
        Rect rect3 = new Rect(10, 30);
        List<Rect> arrayList = new ArrayList();
        arrayList.add(rect1);
        arrayList.add(rect2);
        arrayList.add(rect3);
        rect1.setData(arrayList);
        rectList = rect1.geRectList();


    }


    private final IRemoteServiceBinder.Stub mBinder = new IRemoteServiceBinder.Stub() {


        @Override
        public long getCurrentTime() throws RemoteException {
            if (rectList != null && rectList.size() > 0)
                return rectList.get(rectList.size() - 1).getCurrentTime();
            else
                return 0;
        }

        @Override
        public Rect getRect() throws RemoteException {
            return rectList.get(rectList.size() - 1);
        }

        @Override
        public List<Rect> getRectList() throws RemoteException {
            return rectList;
        }


    };

    @Override
    public IBinder onBind(Intent intent) {
        // Return the interface
        return mBinder;
    }

}