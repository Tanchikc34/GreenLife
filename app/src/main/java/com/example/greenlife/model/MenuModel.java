package com.example.greenlife.model;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MenuModel {

    private String day;
    private int time;

    private MutableLiveData<String> dayData;

    public MenuModel(){
        day = "0";
        dayData = new MutableLiveData<>(day);
    }

    public LiveData<String> getDayLiveData() {
        return dayData;
    }

    public int getTime(){
        return time;
    }

    public void updateTime(long time){
        // Текущее время
        Date currentDate = new Date();
        long millis = currentDate.getTime();

        Log.d("MenuModel", time + " " + millis);

        long currentMillis = millis - time;

        long day = Math.round(currentMillis / 86400000);

        long hours = Math.round(currentMillis / 3600000);
        String hour = String.valueOf(hours);

        long mins = Math.round((currentMillis / 60000) % 60);
        String min = String.valueOf(mins);

        long sc = Math.round((currentMillis / 1000) % 60);
        String sec = String.valueOf(sc);

        time = day;

        dayData.postValue(hour + " : " + min + " : " + sec);
    }

}
