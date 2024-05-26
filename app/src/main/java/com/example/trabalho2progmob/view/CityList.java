package com.example.trabalho2progmob.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.trabalho2progmob.R;
import com.example.trabalho2progmob.adapters.CityAdapter;
import com.example.trabalho2progmob.database.LocalDatabase;
import com.example.trabalho2progmob.databinding.ActivityCitiesListBinding;
import com.example.trabalho2progmob.entities.City;


import java.util.List;

public class CityList extends AppCompatActivity {
    private @NonNull ActivityCitiesListBinding binding;
    private LocalDatabase db;
    private List<City> cities;
    private ListView listViewCities;
    private Intent edtIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCitiesListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        listViewCities = binding.listCities;

        binding.btnHomeCities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnAddCities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CityList.this, CityView.class));
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        edtIntent = new Intent(this, CityView.class);
        fillCities();
    }
    private void fillCities() {
        cities = db.cityModel().getAll();
        CityAdapter citiesAdapter = new CityAdapter(this, cities);
        listViewCities.setAdapter(citiesAdapter);

        listViewCities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                City cityselected = cities.get(position);
                edtIntent.putExtra("CITY_SELECIONADA_ID",
                        cityselected.getCityID());
                startActivity(edtIntent);
            }
        });
    }
}