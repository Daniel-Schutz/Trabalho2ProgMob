package com.example.trabalho2progmob.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.trabalho2progmob.entities.City;
import com.example.trabalho2progmob.R;

import java.util.List;

public class CityAdapter extends ArrayAdapter<City> {

    private Context context;
    private List<City> cities;

    public CityAdapter(@NonNull Context context, @NonNull List<City> cities) {
        super(context, 0, cities);
        this.context = context;
        this.cities = cities;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.item_city, parent, false);
        }

        City currentCity = cities.get(position);

        TextView txtCidade = listItem.findViewById(R.id.txtDesc);
        TextView txtEstado = listItem.findViewById(R.id.txtCidade);

        txtCidade.setText(currentCity.getCidade());
        txtEstado.setText(currentCity.getEstado());

        return listItem;
    }
}
