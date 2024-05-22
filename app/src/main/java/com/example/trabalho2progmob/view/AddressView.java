package com.example.trabalho2progmob.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.trabalho2progmob.R;
import com.example.trabalho2progmob.database.LocalDatabase;
import com.example.trabalho2progmob.databinding.ActivityAddressViewBinding;
import com.example.trabalho2progmob.entities.Address;
import com.example.trabalho2progmob.entities.City;

import java.util.List;
public class AddressView extends AppCompatActivity {

    private ActivityAddressViewBinding binding;
    private LocalDatabase db;
    private int dbAddressID;
    private Address dbAddress;
    private List<City> cities;
    private Spinner spinner;
    private ArrayAdapter<City> citiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddressViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = LocalDatabase.getDatabase(getApplicationContext());

        spinner = binding.spinner;
        dbAddressID = getIntent().getIntExtra(
                "ADDRESS_SELECIONADO_ID", -1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(dbAddressID >= 0){
            fillAddress();
        } else {
            binding.btnExcluirCidade.setVisibility(View.GONE);
        }
        fillCities();
    }

    private void fillAddress() {
        dbAddress = db.addressModel().getAddress(dbAddressID);
        binding.edtDesc.setText(dbAddress.getDescricao());
    }

    private void fillCities() {
        cities = db.cityModel().getAll();
        citiesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, cities);
        spinner.setAdapter(citiesAdapter);
        if(dbAddress != null) {
            spinner.setSelection(dbAddress.getCityID() - 1);
        }
    }

    public void saveAddress(View view) {
        String descricao = binding.edtDesc.getText().toString();
        String newCity = "";

        if(spinner.getSelectedItem() != null){
            newCity = spinner.getSelectedItem().toString();
        }
        if(descricao.equals("")){
            Toast.makeText(this, "A descricao é obrigatória", Toast.LENGTH_SHORT).show();
            return;
        }
        if(newCity.equals("")) {
            Toast.makeText(this, "Entre com uma Cidade.", Toast.LENGTH_SHORT).show();
            return;
        }

        Address newAddress = new Address();
        newAddress.setDescricao(descricao);
        newAddress.setCityID(cities.get(
                spinner.getSelectedItemPosition()).getCityID());
        if(dbAddress != null){
            newAddress.setAddressID(dbAddressID);
            db.addressModel().update(newAddress);
            Toast.makeText(this, "Endereço atualizado com sucesso.",
                    Toast.LENGTH_SHORT).show();
        } else {
            db.addressModel().insertAll(newAddress);
            Toast.makeText(this, "Endereço cadastrado com sucesso.",
                    Toast.LENGTH_SHORT).show();
        }
        finish();
    }
    public void deleteAddress(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Exclusão de Endereço")
                .setMessage("Deseja excluir esse Endereço?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluir();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void excluir() {
        db.addressModel().delete(dbAddress);
        Toast.makeText(this, "Endereço excluído com sucesso.", Toast.LENGTH_SHORT).show();
        finish();
    }
    public void voltar(View view) {
        finish();
    }
}











