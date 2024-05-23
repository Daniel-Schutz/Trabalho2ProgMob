package com.example.trabalho2progmob.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class City {
    @PrimaryKey
    private int cityID;
    private String cidade;
    private String estado;

    public City(){}
    public City(int cityID, String cidade, String estado) {
        this.cityID = cityID;
        this.cidade = cidade;
        this.estado = estado;
    }

    public int getCityID() {return cityID;}

    public void setCityID(int cityID) {this.cityID = cityID;}

    public String getCidade() {return cidade;}

    public void setCidade(String cidade) {this.cidade = cidade;}

    public String getEstado() {return estado;}

    public void setEstado(String estado) {this.estado = estado;}

    @Override
    public String toString() {
        return cidade;
    }
}
