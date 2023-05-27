package com.example.greenlife.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MiniGameModel {

    private final static int MAX_VALUE = 105;
    private final static int MIN_VALUE = 0;

    private int cell;
    private int pollenMoney;
    private int life;

    private MutableLiveData<Integer> cellData;
    private MutableLiveData<Integer> pollenData;
    private MutableLiveData<Integer> lifeData;

    public MiniGameModel(int pollenMoney){
        this.pollenMoney = pollenMoney;
        life = 3;
        cellData = new MutableLiveData<>(cell);
        pollenData = new MutableLiveData<>(this.pollenMoney);
        lifeData = new MutableLiveData<>(life);
    }

    public LiveData<Integer> getCellLiveData() {
        return cellData;
    }

    public LiveData<Integer> getPollenLiveData() {
        return pollenData;
    }

    public LiveData<Integer> getLifeLiveData() {
        return lifeData;
    }

    public void addPollen(int pollen){
        if (this.pollenMoney >= MAX_VALUE) {
            return;
        }

        this.pollenMoney += pollen;

        if(this.pollenMoney > MAX_VALUE)
            this.pollenMoney = MAX_VALUE;

        pollenData.postValue(this.pollenMoney);
    }

    public void minusPollen(){
        if (pollenMoney > MIN_VALUE){
            pollenMoney -= 5;
            pollenData.postValue(pollenMoney);
        }
    }

    public void minusLife(){
        if (life > MIN_VALUE){
            life --;
            lifeData.postValue(life);
        }
    }

    public void cellNum(){
        cell = (int) Math.round(Math.random() * 19);
        cellData.postValue(cell);
    }

}
