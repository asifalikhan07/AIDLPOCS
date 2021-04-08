package com.aidl.IRemoteServiceBinder;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

public class Rect implements Parcelable {
    private long currentTime;


    private int length;
    private int width;
    private List<Rect> listOfRect = new ArrayList<Rect>();

    public void setData(List<Rect> listOfRect) {
        this.listOfRect = listOfRect;

    }

    public Rect(int length, int witdh) {
        this.length = length;
        this.width = witdh;
    }

    private Rect(Parcel in) {

        currentTime = in.readLong();
        length = in.readInt();
        width = in.readInt();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(currentTime);
        out.writeInt(length);
        out.writeInt(width);
        out.writeArray(new List[]{listOfRect});

    }

    public static final Creator<Rect> CREATOR
            = new Creator<Rect>() {
        public Rect createFromParcel(Parcel in) {
            return new Rect(in);
        }

        public Rect[] newArray(int size) {
            return new Rect[size];
        }
    };

    public long getCurrentTime() {
        return System.currentTimeMillis();
    }


    public List<Rect> geRectList() {
        return this.listOfRect;
    }

    public Rect getRect() {
        if (listOfRect != null && listOfRect.size() > 0) {
            return listOfRect.get(listOfRect.size() - 1);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Rect{" +

                "Length = " + length +
                "width = " + width +
                " }";
    }


}
