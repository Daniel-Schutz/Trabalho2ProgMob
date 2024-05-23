package com.example.trabalho2progmob.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.trabalho2progmob.R;
import com.example.trabalho2progmob.database.LocalDatabase;
import com.example.trabalho2progmob.databinding.ActivityCityViewBinding;
import com.example.trabalho2progmob.entities.City;

public class CityView extends AppCompatActivity {
    private LocalDatabase db;
    private ActivityCityViewBinding binding;
    private int dbCityID;
    private City dbCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCityViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        dbCityID = getIntent().getIntExtra(
                "CITY_SELECIONADA_ID", -1);
    }
    protected void onResume() {
        super.onResume();
        if(dbCityID >= 0) {
            getDBCity();
        } else {
            binding.btnExcluirCidade.setVisibility(View.GONE);
        }
    }
    private void getDBCity() {
        dbCity = db.cityModel().getCity(dbCityID);
        binding.edtCidade.setText(dbCity.getCidade());
        binding.edtEstado.setText(dbCity.getEstado());
    }

    public void saveCity(View view) {
        String nomeCity = binding.edtCidade.getText().toString();
        if (nomeCity.equals("")) {
            Toast.makeText(this, "Adicione uma cidade.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        String nomeState = binding.edtEstado.getText().toString();
        if (nomeState.equals("")) {
            Toast.makeText(this, "Adicione um estado.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        City thisCity = new City();
        thisCity.setCidade(nomeCity);
        thisCity.setEstado(nomeState);

        if (dbCity != null) {
            thisCity.setCityID(dbCityID);
            db.cityModel().update(thisCity);
            Toast.makeText(this, "Cidade atualizada com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            db.cityModel().insertAll(thisCity);
            Toast.makeText(this, "Cidade criada com sucesso.", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void deleteCity(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Exclusão de Cidade")
                .setMessage("Deseja excluir essa cidade?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluir();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    private void excluir() {
        if(db.addressModel().getAddressByCity(dbCityID)!=null){
            Toast.makeText(this, "Impossível excluir Cidade com endereços cadastrados", Toast.LENGTH_SHORT).show();
        }else {
            db.cityModel().delete(dbCity);
            Toast.makeText(this, "Cidade excluída com sucesso", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
    public void voltar(View view) {
        finish();
    }
}