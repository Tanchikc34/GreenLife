package com.example.greenlife.db.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.greenlife.db.entity.MenuInfo;

import java.util.List;

@Dao
public interface MenuInfoDao {
    @Query("SELECT * FROM menuInfo")
    List<MenuInfo> getAll();
    @Query("SELECT * FROM menuInfo WHERE id = :id")
    MenuInfo getById(long id);
    @Insert
    void insert(MenuInfo employee1);
    @Update
    void update(MenuInfo employee1);
    @Delete
    void delete(MenuInfo employee1);
    @Query("DELETE FROM menuInfo")
    void deleteAll();
}
