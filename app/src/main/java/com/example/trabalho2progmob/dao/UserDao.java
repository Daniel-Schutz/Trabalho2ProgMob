package com.example.trabalho2progmob.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.trabalho2progmob.entities.User;
@Dao
public interface UserDao {

    @Insert
    void insertAll(User... user);
    @Query("SELECT * FROM User WHERE userID = :id LIMIT 1")
    User getUser(int id);

    @Update
    void update(User user);
    @Delete
    void delete(User user);

}
