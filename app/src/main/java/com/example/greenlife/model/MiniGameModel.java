package com.example.greenlife.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MiniGameModel {
    public final static int MAX_VALUE = 105;
    public final static int MIN_VALUE = 0;

    private int cell;
    private int pollenMoney;
    private int life;
    private int image;

    private MutableLiveData<Integer> cellData;
    private MutableLiveData<Integer> pollenData;
    private MutableLiveData<Integer> lifeData;
    private MutableLiveData<Integer> imageData;

    public MiniGameModel(){
        cell = 1;
        pollenMoney = 0;
        life = 3;
        image = 0;
        cellData = new MutableLiveData<>(cell);
        pollenData = new MutableLiveData<>(pollenMoney);
        lifeData = new MutableLiveData<>(life);
        imageData = new MutableLiveData<>(image);
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

    public LiveData<Integer> getImageLiveData() {
        return imageData;
    }

    public void changeImage(){
        if (image < 3){
            image++;
            imageData.postValue(image);
        }
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
        cell = (int) Math.round(Math.random() * 20);
        cellData.postValue(cell);
    }

}
