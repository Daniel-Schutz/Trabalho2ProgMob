package com.example.trabalho2progmob.adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.trabalho2progmob.entities.Address;
import com.example.trabalho2progmob.entities.City;
import com.example.trabalho2progmob.R;
import com.example.trabalho2progmob.database.LocalDatabase;

import java.util.List;

public class AddressAdapter extends ArrayAdapter<Address> {

    private Context context;
    private List<Address> addresses;
    private LocalDatabase db;

    public AddressAdapter(@NonNull Context context, @NonNull List<Address> addresses) {
        super(context, 0, addresses);
        this.context = context;
        this.addresses = addresses;
        this.db = LocalDatabase.getDatabase(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.item_address, parent, false);
        }

        Address currentAddress = addresses.get(position);

        TextView txtDesc = listItem.findViewById(R.id.txtDesc);
        final TextView txtCidade = listItem.findViewById(R.id.txtCidade);

        txtDesc.setText(currentAddress.getDescricao());

        // AsyncTask para buscar o nome da cidade
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // Busca o nome da cidade com base no ID da cidade
                City city = db.cityModel().getCity(currentAddress.getCityID());
                // Atualiza a TextView do nome da cidade no thread principal
                if (city != null) {
                    txtCidade.post(new Runnable() {
                        @Override
                        public void run() {
                            txtCidade.setText(city.getCidade());
                        }
                    });
                }
            }
        });

        return listItem;
    }
}
