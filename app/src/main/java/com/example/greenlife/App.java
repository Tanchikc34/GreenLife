package com.example.greenlife;

import android.app.Application;

import androidx.room.Room;

import com.example.greenlife.db.AppDatabase;
import com.example.greenlife.db.entity.MenuInfo;
import com.example.greenlife.db.entity.OtherInfo;
import com.example.greenlife.db.entity.PlantInfo;

import java.util.Date;

public class App extends Application {

    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();

        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();

        if (database.menuInfoDao().getById(1) == null){
            Date currentDate = new Date();
            long millis = currentDate.getTime();
            database.menuInfoDao().insert(new MenuInfo(millis));
        }

        if (database.otherInfoDao().getById(1) == null && database.plantInfoDao().getById(1) == null){
            database.otherInfoDao().insert(new OtherInfo(0));
            database.plantInfoDao().insert(new PlantInfo("Анемона", "Название растения анемона (Anemone), либо ветреница произошло " +
                    "от греческого слова, которое в переводе означает «дочь ветров». Дело в том, что даже от малейшего порыва ветра лепестки такого растения начинают трепетать.", 100, 100, 100, 0));
        }
    }

    public AppDatabase getDatabase() {
        return database;
    }

}