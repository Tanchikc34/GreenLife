package com.example.greenlife.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MenuInfo {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public int day;

    public MenuInfo(int day) {
        this.day = day;
    }

    public long getId() {
        return id;
    }

    public int getDay() {
        return day;
    }


}

