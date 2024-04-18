package com.example.floralhaven.database.typeConverters;

import androidx.room.TypeConverter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class LocalDateTypeConverter {

    @TypeConverter
    public long convertDateToLong(LocalDateTime date){
        ZonedDateTime zdt = ZonedDateTime.of(date, ZoneId.systemDefault());
        return zdt.toInstant().toEpochMilli();
    }

    @TypeConverter
    public LocalDateTime convertLongToDate(Long epochMili)
    {
        Instant instant = Instant.ofEpochMilli(epochMili);
        return LocalDateTime.ofInstant(instant,ZoneId.systemDefault());
    }

}
