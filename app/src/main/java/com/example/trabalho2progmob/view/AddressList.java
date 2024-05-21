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
import com.example.trabalho2progmob.databinding.ActivityAddressesListBinding;
import com.example.trabalho2progmob.entities.Address;


import java.util.List;

public class AddressList extends AppCompatActivity {
    private @NonNull ActivityAddressesListBinding binding;
    private LocalDatabase db;
    private List<Address> addresses;
    private ListView listViewAddresses;
    private Intent edtIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddressesListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        listViewAddresses = binding.listAddresses;

        binding.btnHomePeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressList.this, AddressView.class));
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        edtIntent = new Intent(this, AddressView.class);
        preencheEnderecos();
    }
    private void preencheEnderecos() {
        addresses = db.addressModel().getAll();
        ArrayAdapter<Address> addressesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, addresses);
        listViewAddresses.setAdapter(addressesAdapter);

        listViewAddresses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Address addresselected = addresses.get(position);
                edtIntent.putExtra("ADDRESS_SELECIONADA_ID",
                        addresselected.getAddressID());
                startActivity(edtIntent);
            }
        });
    }
}