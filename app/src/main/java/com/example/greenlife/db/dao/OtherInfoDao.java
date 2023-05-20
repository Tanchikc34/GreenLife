package com.example.greenlife.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.greenlife.db.entity.OtherInfo;

import java.util.List;

@Dao
public interface OtherInfoDao {
    @Query("SELECT * FROM otherInfo")
    List<OtherInfo> getAll();
    @Query("SELECT * FROM otherInfo WHERE id = :id")
    OtherInfo getById(long id);
    @Insert
    void insert(OtherInfo employee2);
    @Update
    void update(OtherInfo employee2);
    @Delete
    void delete(OtherInfo employee2);
    @Query("DELETE FROM menuInfo")
    void deleteAll();
}
