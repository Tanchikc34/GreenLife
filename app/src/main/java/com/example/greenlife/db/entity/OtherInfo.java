package com.example.greenlife.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class OtherInfo {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public int money;

    public OtherInfo(int money) {
        this.money = money;
    }

    public long getId() {
        return id;
    }

    public int getMoney() {
        return money;
    }
}
