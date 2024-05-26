package com.example.trabalho2progmob.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.trabalho2progmob.dao.AddressDao;
import com.example.trabalho2progmob.dao.CityDao;
import com.example.trabalho2progmob.dao.UserDao;
import com.example.trabalho2progmob.entities.Address;
import com.example.trabalho2progmob.entities.City;
import com.example.trabalho2progmob.entities.User;

@Database(entities = {City.class, Address.class, User.class}, version = 2)
public abstract class LocalDatabase extends RoomDatabase {
    private static LocalDatabase INSTANCE;
    public static LocalDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), LocalDatabase.class, "ControleEnderecos2daniel.schutz").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
    public abstract UserDao userModel();
    public abstract CityDao cityModel();
    public abstract AddressDao addressModel();
}
