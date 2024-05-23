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
import com.example.trabalho2progmob.database.LocalDatabase;
import com.example.trabalho2progmob.databinding.ActivityPeopleListBinding;
import com.example.trabalho2progmob.entities.User;


import java.util.List;

public class PeopleList extends AppCompatActivity {
    private @NonNull ActivityPeopleListBinding binding;
    private LocalDatabase db;
    private List<User> people;
    private ListView listViewPeople;
    private Intent edtIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPeopleListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        listViewPeople = binding.listPeople;

        binding.btnHomePeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PeopleList.this, PeopleView.class));
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        edtIntent = new Intent(this, PeopleView.class);
        preencherUsuarios();
    }
    private void preencherUsuarios() {
        people = db.userModel().getAll();
        ArrayAdapter<User> peopleAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, people);
        listViewPeople.setAdapter(peopleAdapter);

        listViewPeople.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                User personselected = people.get(position);
                edtIntent.putExtra("USER_SELECIONADO_ID", personselected.getUserID());
                startActivity(edtIntent);
            }
        });
    }
}