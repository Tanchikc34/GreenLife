package com.example.greenlife.ui.minigame;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.greenlife.App;
import com.example.greenlife.db.AppDatabase;
import com.example.greenlife.db.dao.OtherInfoDao;
import com.example.greenlife.db.entity.OtherInfo;
import com.example.greenlife.model.MiniGameModel;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MiniGameViewModel extends AndroidViewModel {

    private MiniGameModel model;
    private int plusPollenValue = 2;
    int ROWS = 5;
    int COLUMNS = 4;
    private MutableLiveData<Boolean>[] beesLiveData = new MutableLiveData[ROWS * COLUMNS];


    public MiniGameViewModel(Application application){
        super(application);
        model = new MiniGameModel();

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                model.cellNum();
            }
        }, 0, 2, TimeUnit.SECONDS);

        for(int i = 0; i < beesLiveData.length; i++){
            beesLiveData[i] = new MutableLiveData<>(false);
        }

        AppDatabase db = ((App)application).getDatabase();
        OtherInfoDao otherInfoDao = db.otherInfoDao();
        new Thread(new Runnable() {
            @Override
            public void run() {
                otherInfoDao.insert(new OtherInfo(1));
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

    public LiveData<Integer> getImageLiveData(){
        return model.getImageLiveData();
    }

    public LiveData<Integer> getLifeLiveData(){
        return model.getLifeLiveData();
    }

    public void addPollenClick(){
        model.addPollen(plusPollenValue);
    }
    public LiveData<Boolean> getBeeLiveData(int position){
        return beesLiveData[position];
    }
    public void killBee(int position){
        beesLiveData[position].postValue(Boolean.FALSE);
    }
    public void setBee(int position){
        beesLiveData[position - 1].postValue(Boolean.TRUE);
    }
}
