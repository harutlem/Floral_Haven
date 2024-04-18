package com.example.floralhaven.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.floralhaven.database.FlowerDatabase;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(tableName = FlowerDatabase.ORDER_HISTORY_TABLE)
public class OrderHistory {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private LocalDateTime date;
    private String flowerName;
    private int flowerQuantity;
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    public int getFlowerQuantity() {
        return flowerQuantity;
    }

    public void setFlowerQuantity(int flowerQuantity) {
        this.flowerQuantity = flowerQuantity;
    }



    public OrderHistory(String flowerName, int flowerQuantity, int userId) {
        this.flowerName = flowerName;
        this.flowerQuantity = flowerQuantity;
        this.userId = userId;
        date =  LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderHistory that = (OrderHistory) o;
        return id == that.id && flowerQuantity == that.flowerQuantity && userId == that.userId && Objects.equals(date, that.date) && Objects.equals(flowerName, that.flowerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, flowerName, flowerQuantity, userId);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
