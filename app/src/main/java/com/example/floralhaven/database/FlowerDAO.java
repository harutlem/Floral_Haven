package com.example.floralhaven.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.floralhaven.database.entities.Flower;
import com.example.floralhaven.database.entities.OrderHistory;

import java.util.List;

@Dao
public interface FlowerDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Flower record);

    @Query("Select * from " + FlowerDatabase.FLOWER_TABLE)
    List<Flower> getAllRecords();


   /// @Query("Select * from " + FlowerDatabase.FLOWER_TABLE + " where userId = :loggedInUserId  order by date desc")
   // List<Flower> getAllLogsByUserId(int userId);
}
