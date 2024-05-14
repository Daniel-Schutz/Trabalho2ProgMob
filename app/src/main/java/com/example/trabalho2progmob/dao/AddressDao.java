package com.example.trabalho2progmob.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.trabalho2progmob.entities.Address;
@Dao
public interface AddressDao {

    @Insert
    void insertAll(Address... address);
    @Query("SELECT * FROM Address WHERE addressID = :id LIMIT 1")
    Address getAddress(int id);
    @Update
    void update(Address address);
    @Delete
    void delete(Address address);
    @Query("SELECT * FROM Address WHERE cityID = :idCity LIMIT 1")
    Address getAddressByCity(int idCity);
}
