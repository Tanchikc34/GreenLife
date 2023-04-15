package com.example.greenlife.ui.home;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.greenlife.model.PlantModel;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HomeViewModel extends ViewModel {
    private PlantModel plant;
    public HomeViewModel(){
        plant = new PlantModel();

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                plant.minusClick();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }


    public LiveData<Integer> getWetnessLiveData(){
        return plant.getWetnessLiveData();
    }
    public void addWetness(){
        plant.addWetness();
    }
}
