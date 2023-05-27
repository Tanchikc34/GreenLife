package com.example.greenlife.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.greenlife.db.dao.OtherInfoDao;
import com.example.greenlife.db.dao.PlantInfoDao;
import com.example.greenlife.db.entity.OtherInfo;
import com.example.greenlife.db.entity.PlantInfo;
import com.example.greenlife.db.dao.MenuInfoDao;
import com.example.greenlife.db.entity.MenuInfo;

@Database(entities = {PlantInfo.class, MenuInfo.class, OtherInfo.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlantInfoDao plantInfoDao();
    public abstract MenuInfoDao menuInfoDao();
    public abstract OtherInfoDao otherInfoDao();
}