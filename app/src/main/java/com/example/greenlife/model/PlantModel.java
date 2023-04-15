package com.example.greenlife.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class PlantModel {

    private int wetness; //влага

    private MutableLiveData<Integer> wetnessData;

    public PlantModel(){
        wetness = 100;
        wetnessData = new MutableLiveData<>(wetness);
    }

    public LiveData<Integer> getWetnessLiveData() {
        return wetnessData;
    }

    public void minusClick(){
        if (wetness > 0){
            wetness--;
            wetnessData.postValue(wetness);
        }
    }

    public void addWetness(){
        if (wetness < 100){
            wetness++;
            wetnessData.postValue(wetness);
        }
    }
}
