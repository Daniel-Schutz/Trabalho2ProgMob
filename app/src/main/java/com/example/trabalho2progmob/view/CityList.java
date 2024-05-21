package com.example.trabalho2progmob.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.trabalho2progmob.databinding.ActivityCitiesListBinding;
public class CityList extends AppCompatActivity {
    private ActivityCitiesListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCitiesListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
