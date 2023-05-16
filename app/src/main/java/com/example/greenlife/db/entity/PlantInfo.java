package com.example.greenlife.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PlantInfo {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String text;
    public int wetness;
    public int hp;
    public int happy;
    public int im;

    public PlantInfo(String name, String text, int wetness, int hp, int happy, int im) {
        this.name = name;
        this.text = text;
        this.wetness = wetness;
        this.hp = hp;
        this.happy = happy;
        this.im = im;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public int getWetness() {
        return wetness;
    }

    public int getHp() {
        return hp;
    }

    public int getHappy() {
        return happy;
    }

    public int getIm() {
        return im;
    }

    @Override
    public String toString() {
        return "PlantInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

