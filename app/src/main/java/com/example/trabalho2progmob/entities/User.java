package com.example.trabalho2progmob.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private int userID;
    private String nome;
    private String email;
    private String senha;

    public User(){}

    public User(int userID, String nome, String email, String senha){
        this.userID = userID;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public int getUserID() {return userID;}

    public void setUserID(int userID) {this.userID = userID;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getSenha() {return senha;}

    public void setSenha(String senha) {this.senha = senha;}

    @Override
    public String toString() {
        return nome;
    }
}
