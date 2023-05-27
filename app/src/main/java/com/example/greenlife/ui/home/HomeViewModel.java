package com.example.greenlife.ui.home;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.greenlife.App;
import com.example.greenlife.db.AppDatabase;
import com.example.greenlife.db.dao.PlantInfoDao;
import com.example.greenlife.db.entity.PlantInfo;
import com.example.greenlife.model.MenuModel;
import com.example.greenlife.model.PlantModel;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HomeViewModel extends AndroidViewModel {

    private AppDatabase db;
    private PlantInfoDao plantInfoDao;
    private PlantModel plant;
    private MenuModel time;
    private int plusHappyValue = 1;
    private int minusHappyValue = 1;
    private final static int PLUS_HAPPY_VALUE_LIGHT = 3;
    private final static int PLUS_HAPPY_VALUE_CLICK = 1;
    private PlantInfo plantInfo;
    public final static int MAX_VALUE = 100;
    public final static int MIN_VALUE = 0;

    public HomeViewModel(Application application){
        super(application);
        db = ((App)getApplication()).getDatabase();
        plantInfoDao = db.plantInfoDao();

        plant = new PlantModel(plantInfoDao.getById(1).wetness, plantInfoDao.getById(1).hp, plantInfoDao.getById(1).happy, plantInfoDao.getById(1).im);
        time = new MenuModel();


        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                plant.minusWetness();
                plant.minusHp();
                plant.addHp(plusHappyValue);

            }
        }, 0, 10, TimeUnit.MINUTES);


        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                plant.minusHappy(minusHappyValue);
            }
        }, 0, 5, TimeUnit.MINUTES);


        if (time.getTime() > 0 + plant.getImLiveData().getValue().intValue()){
            plant.changeIm();
        }

        if (time.getTime() >= 2){
            plant.minusAll();
        }



        plantInfo = plantInfoDao.getById(1);

        plantInfo.name = "Анемона";
        plantInfo.text = "Название растения анемона (Anemone), либо ветреница произошло от греческого слова, которое в переводе означает «дочь ветров». Дело в том, что даже от малейшего порыва ветра лепестки такого растения начинают трепетать.";
        plantInfo.wetness = MAX_VALUE;
        plantInfo.hp = MAX_VALUE;
        plantInfo.happy =MAX_VALUE;
        plantInfo.im = MIN_VALUE;

    }

    public void nullBd(){
        plantInfo.wetness = 100;
        plantInfo.hp = 100;
        plantInfo.happy = 100;
        plantInfo.im = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                plantInfoDao.update(plantInfo);
            }
        }).start();
        plant = new PlantModel(plantInfoDao.getById(1).wetness, plantInfoDao.getById(1).hp, plantInfoDao.getById(1).happy, plantInfoDao.getById(1).im);
    }

    public void upBd(){
        plantInfo.wetness = plant.getWetnessLiveData().getValue().intValue();
        plantInfo.hp = plant.getHpLiveData().getValue().intValue();
        plantInfo.happy = plant.getHappyLiveData().getValue().intValue();
        plantInfo.im = plant.getImLiveData().getValue().intValue();
        new Thread(new Runnable() {
            @Override
            public void run() {
                plantInfoDao.update(plantInfo);
            }
        }).start();
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
        plant.addHappy(PLUS_HAPPY_VALUE_CLICK);
    }

    public void addHp(int i){
        plant.addHp(i);
    }

    public void addWetness(){
        plant.addWetness();
    }
    public void setLightLevel(float level){
        if(level > 1000) {
            minusHappyValue = 0;
            plant.addHappy(PLUS_HAPPY_VALUE_LIGHT);
        }
        else{
            minusHappyValue = 1;
        }

    }
}
