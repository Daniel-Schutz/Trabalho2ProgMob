package com.example.trabalho2progmob.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.trabalho2progmob.entities.City;
@Dao
public interface CityDao {

    @Insert
    void insertAll(City... city);
    @Query("SELECT * FROM City WHERE cityID = :id LIMIT 1")
    City getCity(int id);
    @Update
    void update(City city);
    @Delete
    void delete(City city);
}
