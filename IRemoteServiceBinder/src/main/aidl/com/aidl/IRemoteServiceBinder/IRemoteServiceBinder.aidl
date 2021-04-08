// IRemoteServiceBinder.aidl
package com.aidl.IRemoteServiceBinder;

// Declare any non-default types here with import statements

import com.aidl.IRemoteServiceBinder.Rect;
interface IRemoteServiceBinder {

    long getCurrentTime();
    Rect getRect();
    List<Rect> getRectList();

}