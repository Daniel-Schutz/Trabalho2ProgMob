package com.example.trabalho2progmob.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.trabalho2progmob.R;
import com.example.trabalho2progmob.view.AddressList;
import com.example.trabalho2progmob.view.CityList;
import com.example.trabalho2progmob.view.PeopleList;

import com.example.trabalho2progmob.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("teste");
                Intent it=new Intent(MainActivity.this,CityList.class);
                startActivity(it);
            }
        });
        binding.btnPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MainActivity.this,PeopleList.class);
                startActivity(it);
            }
        });
        binding.btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MainActivity.this,AddressList.class);
                startActivity(it);
            }
        });

    }
}