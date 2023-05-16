package com.example.greenlife.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.greenlife.db.entity.MenuInfo;

import java.util.List;

@Dao
public interface OtherInfoDao {
    @Query("SELECT * FROM menuInfo")
    List<MenuInfo> getAll();
    @Query("SELECT * FROM menuInfo WHERE id = :id")
    MenuInfo getById(long id);
    @Insert
    void insert(MenuInfo employee2);
    @Update
    void update(MenuInfo employee2);
    @Delete
    void delete(MenuInfo employee2);
    @Query("DELETE FROM menuInfo")
    void deleteAll();
}
