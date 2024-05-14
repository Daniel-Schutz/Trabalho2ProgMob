package com.example.trabalho2progmob.entities;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = City.class,
        parentColumns = "cityID", childColumns = "cityID",
        onDelete = ForeignKey.CASCADE))
public class Address {

    @PrimaryKey
    private int addressID;
    private String descricao;
    private double latitude;
    private double longitude;
    private int cityID;

    public Address(){}
    public Address(int addressID, String descricao, double latitude, double longitude) {
        this.addressID = addressID;
        this.descricao = descricao;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getAddressID() {return addressID;}

    public void setAddressID(int addressID) {this.addressID = addressID;}

    public String getDescricao() {return descricao;}

    public void setDescricao(String descricao) {this.descricao = descricao;}

    public double getLatitude() {return latitude;}

    public void setLatitude(double latitude) {this.latitude = latitude;}

    public double getLongitude() {return longitude;}

    public void setLongitude(double longitude) {this.longitude = longitude;}

    public int getCityID() {return cityID;}

    public void setCityID(int cityID) {this.cityID = cityID;}
}
