package com.example.floralhaven.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.floralhaven.database.entities.OrderHistory;

import java.util.List;

@Dao
public interface FlowerDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(OrderHistory record);

    @Query("Select * from " + FlowerDatabase.ORDER_HISTORY_TABLE)
    List<OrderHistory> getAllRecords();


}
