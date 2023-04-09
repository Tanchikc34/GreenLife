package com.example.greenlife;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class PlantImage{

    public PlantImage(String name) {
        this.name = name;
    }

    public String name;

    private int live;

    private MutableLiveData<Integer> liveLiveData;

    private static volatile PlantImage instance;

    private PlantImage(){
        live = 100;
        liveLiveData = new MutableLiveData<>(live);
    }

    public static PlantImage getInstance() {
        if(instance == null){
            synchronized (PlantImage.class){
                if(instance == null)
                    instance = new PlantImage();
            }
        }
        return instance;
    }

    public LiveData<Integer> getliveLiveData() {
        return liveLiveData;
    }

    public void mClick(){
        live--;
        liveLiveData.postValue(live);
    }

    public void addClick(){
        live++;
        liveLiveData.postValue(live);
    }
}
