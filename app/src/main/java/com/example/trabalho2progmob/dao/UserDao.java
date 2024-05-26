package com.example.trabalho2progmob.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.trabalho2progmob.entities.Address;
import com.example.trabalho2progmob.entities.User;

import java.util.List;

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
    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("SELECT * FROM User WHERE email = :email AND senha = :senha LIMIT 1")
    User getUserByEmailAndPassword(String email, String senha);
}
