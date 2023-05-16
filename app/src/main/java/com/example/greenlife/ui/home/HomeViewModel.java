package com.example.greenlife.ui.home;


import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.greenlife.db.App;
import com.example.greenlife.db.AppDatabase;
import com.example.greenlife.db.dao.PlantInfoDao;
import com.example.greenlife.db.entity.PlantInfo;
import com.example.greenlife.model.PlantModel;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HomeViewModel extends AndroidViewModel {

    private PlantModel plant;
    private int minusHappyValue = 1;
    private int plusHappyValueLight = 3;
    private int plusHappyValueClick = 1;

    public HomeViewModel(Application application){
        super(application);
        plant = new PlantModel();

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                plant.minusWetness();
                plant.minusHp();
                plant.addHp();

            }
        }, 0, 1, TimeUnit.SECONDS);

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                plant.minusHappy(minusHappyValue);
            }
        }, 0, 2, TimeUnit.SECONDS);

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                plant.changeIm();
            }
        }, 0, 3, TimeUnit.SECONDS);
/*
        AppDatabase db = App.getInstance().getDatabase();
        PlantInfoDao plantInfoDao = db.plantInfoDao();
        new Thread(new Runnable() {
            @Override
            public void run() {
                plantInfoDao.insert(new PlantInfo("Анемона", "Анемона очень любит солнце", 1, 1, 1, 1));
            }
        }).start();*/

    }


    public LiveData<Integer> getWetnessLiveData(){
        return plant.getWetnessLiveData();
    }

    public LiveData<Integer> getHpLiveData(){
        return plant.getHpLiveData();
    }

    public LiveData<Integer> getHappyLiveData(){
        return plant.getHappyLiveData();
    }

    public LiveData<Integer> getImLiveData(){
        return plant.getImLiveData();
    }

    public void addHappyClick(){
        plant.addHappy(plusHappyValueClick);
    }

    public void addWetness(){
        plant.addWetness();
    }
    public void setLightLevel(float level){
        if(level > 1000) {
            minusHappyValue = 0;
            plant.addHappy(plusHappyValueLight);
        }
        else{
            minusHappyValue = 1;
        }

    }
}
