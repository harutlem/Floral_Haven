package com.example.floralhaven.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.floralhaven.database.FlowerDatabase;


import java.time.LocalDateTime;
import java.util.Objects;

@Entity(tableName = FlowerDatabase.FLOWER_TABLE)
    public class Flower {
        @PrimaryKey(autoGenerate = true)
        private int id;

        private int roseQuantity;
        private int tulipQuantity;
        private int lilyQuantity;
        private int carnationQuantity;
        private LocalDateTime date;



    public Flower(int roseQuantity, int tulipQuantity, int lilyQuantity, int carnationQuantity) {
        this.roseQuantity = roseQuantity;
        this.tulipQuantity = tulipQuantity;
        this.lilyQuantity = lilyQuantity;
        this.carnationQuantity = carnationQuantity;
        date =  LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Flower{" +
                "roseQuantity=" + roseQuantity +
                ", tulipQuantity=" + tulipQuantity +
                ", lilyQuantity=" + lilyQuantity +
                ", carnationQuantity=" + carnationQuantity +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flower flower = (Flower) o;
        return id == flower.id && roseQuantity == flower.roseQuantity && tulipQuantity == flower.tulipQuantity && lilyQuantity == flower.lilyQuantity && carnationQuantity == flower.carnationQuantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roseQuantity, tulipQuantity, lilyQuantity, carnationQuantity);
    }

    public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getRoseQuantity() {
            return roseQuantity;
        }

        public void setRoseQuantity(int roseQuantity) {
            this.roseQuantity = roseQuantity;
        }

        public int getTulipQuantity() {
            return tulipQuantity;
        }

        public void setTulipQuantity(int tulipQuantity) {
            this.tulipQuantity = tulipQuantity;
        }

        public int getLilyQuantity() {
            return lilyQuantity;
        }

        public void setLilyQuantity(int lilyQuantity) {
            this.lilyQuantity = lilyQuantity;
        }

        public int getCarnationQuantity() {
            return carnationQuantity;
        }

        public void setCarnationQuantity(int carnationQuantity) {
            this.carnationQuantity = carnationQuantity;
        }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

