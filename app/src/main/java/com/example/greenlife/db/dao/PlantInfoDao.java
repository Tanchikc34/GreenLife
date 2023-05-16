package com.example.greenlife.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.greenlife.db.entity.PlantInfo;

import java.util.List;

@Dao
public interface PlantInfoDao {
    @Query("SELECT * FROM plantinfo")
    List<PlantInfo> getAll();
    @Query("SELECT * FROM plantinfo WHERE id = :id")
    PlantInfo getById(long id);
    @Insert
    void insert(PlantInfo employee);
    @Update
    void update(PlantInfo employee);
    @Delete
    void delete(PlantInfo employee);
    @Query("DELETE FROM plantinfo")
    void deleteAll();
}
