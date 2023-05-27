package com.example.greenlife.model;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.greenlife.App;
import com.example.greenlife.db.AppDatabase;
import com.example.greenlife.db.dao.PlantInfoDao;

public class PlantModel {
    public final static int MAX_VALUE = 100;
    public final static int MIN_VALUE = 0;

    private int wetness; //влага
    private int hp;
    private int happy;
    private int im;

    private MutableLiveData<Integer> wetnessData;
    private MutableLiveData<Integer> hpData;
    private MutableLiveData<Integer> happyData;
    private MutableLiveData<Integer> imData;

    public PlantModel(int wetness, int hp, int happy, int im){
        this.wetness = wetness;
        this.hp = hp;
        this.happy = happy;
        this.im = im;
        wetnessData = new MutableLiveData<>(this.wetness);
        hpData = new MutableLiveData<>(this.hp);
        happyData = new MutableLiveData<>(this.happy);
        imData = new MutableLiveData<>(this.im);
    }

    public LiveData<Integer> getWetnessLiveData() {
        return wetnessData;
    }

    public LiveData<Integer> getHpLiveData() {
        return hpData;
    }

    public LiveData<Integer> getHappyLiveData() {
        return happyData;
    }

    public LiveData<Integer> getImLiveData() {
        return imData;
    }

    public void changeIm(){
        if (im < 13){
            im++;
            imData.postValue(im);
        }
    }

    public void minusHp(){
        if (wetness < MAX_VALUE / 2 || happy < MAX_VALUE / 1.66){
            if (hp > MIN_VALUE){
                hp--;
                hpData.postValue(hp);
            }
        }
    }

    public void minusAll(){
        hp = 0;
        hpData.postValue(hp);
        happy = 0;
        happyData.postValue(happy);
        wetness = 0;
        wetnessData.postValue(wetness);
    }


    public void addHp(int hp){
        if (wetness > MAX_VALUE / 2 || happy > MAX_VALUE / 1.66){
            if (this.hp < MAX_VALUE){
                this.hp+= hp;
                hpData.postValue(this.hp);
            }
        }
    }

    public void minusHappy(int happy){
        if (this.happy > MIN_VALUE){
            this.happy-= happy;
            happyData.postValue(this.happy);
        }
    }

    public void addHappy(int happy){
        if (this.happy >= MAX_VALUE) {
            return;
        }

        this.happy += happy;

        if(this.happy > MAX_VALUE)
            this.happy = MAX_VALUE;

        happyData.postValue(this.happy);
    }

    public void minusWetness(){
        if (wetness > MIN_VALUE){
            wetness--;
            wetnessData.postValue(wetness);
        }
    }

    public void addWetness(){
        if (wetness < MAX_VALUE){
            wetness++;
            wetnessData.postValue(wetness);
        }
    }
}
