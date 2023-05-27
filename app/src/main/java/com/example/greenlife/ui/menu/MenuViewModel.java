package com.example.greenlife.ui.menu;

import android.app.Application;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.greenlife.App;
import com.example.greenlife.db.AppDatabase;
import com.example.greenlife.db.entity.MenuInfo;
import com.example.greenlife.model.MenuModel;

import java.util.Date;
import java.util.Locale;


public class MenuViewModel extends AndroidViewModel{

    private MenuModel model;
    private AppDatabase db;

    public MenuViewModel(Application application){
        super(application);
        model = new MenuModel();
        db = ((App) application).getDatabase();
    }

    public LiveData<String> getDayLiveData(){
        return model.getDayLiveData();
    }

    public void updateTime(){
        new Thread(() ->
                model.updateTime(db.menuInfoDao().getById(1).time)
        ).start();
    }
}
