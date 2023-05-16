package com.example.greenlife.ui.minigame;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.greenlife.model.FlowerModel;
import com.example.greenlife.model.PlantModel;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FlowerViewModel extends ViewModel {

    private FlowerModel model;
    private int plusPollenValue = 2;

    public FlowerViewModel(){
        model = new FlowerModel();

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                model.cellNum();
            }
        }, 0, 2, TimeUnit.SECONDS);
        /*
        AppDatabase db = App.getInstance().getDatabase();
        OtherInfoDao otherInfoDao = db.otherInfoDao();
        new Thread(new Runnable() {
            @Override
            public void run() {
                otherInfoDao.insert(new OtherInfo(1));
            }
        }).start();*/

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

    public LiveData<Integer> getImageLiveData(){
        return model.getImageLiveData();
    }

    public LiveData<Integer> getLifeLiveData(){
        return model.getLifeLiveData();
    }

    public void addPollenClick(){
        model.addPollen(plusPollenValue);
    }
}
