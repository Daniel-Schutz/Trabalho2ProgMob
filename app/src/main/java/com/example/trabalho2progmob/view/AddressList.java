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
import com.example.trabalho2progmob.adapters.AddressAdapter;
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


                Intent it = new Intent(AddressList.this, MainActivity.class);
                startActivity(it);
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
        AddressAdapter addressesAdapter = new AddressAdapter(this, addresses);
        listViewAddresses.setAdapter(addressesAdapter);
        listViewAddresses.setAdapter(addressesAdapter);

        listViewAddresses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Address addressSelected = addresses.get(position);
                edtIntent.putExtra("ADDRESS_SELECIONADO_ID", addressSelected.getAddressID());
                startActivity(edtIntent);
            }
        });
    }

}