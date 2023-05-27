package com.example.greenlife.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MenuInfo {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public long time;
    public MenuInfo(Long time) {
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public long getDay() {
        return time;
    }


}

