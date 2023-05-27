package com.example.greenlife.ui.minigame;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.greenlife.App;
import com.example.greenlife.db.AppDatabase;
import com.example.greenlife.db.dao.OtherInfoDao;
import com.example.greenlife.db.dao.PlantInfoDao;
import com.example.greenlife.db.entity.OtherInfo;
import com.example.greenlife.db.entity.PlantInfo;
import com.example.greenlife.model.MiniGameModel;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MiniGameViewModel extends AndroidViewModel {

    private MiniGameModel model;
    private int plusPollenValue = 2;
    int ROWS = 5;
    int COLUMNS = 4;
    private MutableLiveData<Boolean>[] beesLiveData = new MutableLiveData[ROWS * COLUMNS];
    private OtherInfo otherInfo;
    private OtherInfoDao otherInfoDao;
    private AppDatabase db;


    public MiniGameViewModel(Application application){
        super(application);
        db = ((App)application).getDatabase();
        otherInfoDao = db.otherInfoDao();
        model = new MiniGameModel(otherInfoDao.getById(1).money);

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                model.cellNum();
            }
        }, 0, 1, TimeUnit.SECONDS);

        for(int i = 0; i < beesLiveData.length; i++){
            beesLiveData[i] = new MutableLiveData<>(false);
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                otherInfoDao.insert(new OtherInfo(0));
            }
        }).start();

        otherInfo = otherInfoDao.getById(1);
        otherInfo.money = 0;
    }

    public void upBd(){
        otherInfo.money = model.getPollenLiveData().getValue().intValue();
        new Thread(new Runnable() {
            @Override
            public void run() {
                otherInfoDao.update(otherInfo);
            }
        }).start();
    }

    public void minusLife(){
        model.minusLife();
    }

    public LiveData<Integer> getCellLiveData(){
        return model.getCellLiveData();
    }

    public LiveData<Integer> getPollenLiveData(){
        return model.getPollenLiveData();
    }

    public LiveData<Integer> getLifeLiveData(){
        return model.getLifeLiveData();
    }

    public void addPollenClick(){
        model.addPollen(plusPollenValue);
    }

    public void minusPollen(){
        model.minusPollen();
        if (model.getPollenLiveData().getValue().intValue() > 0){
        otherInfo.money = model.getPollenLiveData().getValue().intValue() - 5;
        new Thread(new Runnable() {
            @Override
            public void run() {
                otherInfoDao.update(otherInfo);
            }
        }).start();}
    }

    public LiveData<Boolean> getBeeLiveData(int position){
        return beesLiveData[position];
    }
    public void killBee(int position){
        beesLiveData[position].postValue(Boolean.FALSE);
    }
    public void setBee(int position){
        beesLiveData[position].postValue(Boolean.TRUE);
    }
}
