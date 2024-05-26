package com.example.trabalho2progmob.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.trabalho2progmob.entities.User;
import com.example.trabalho2progmob.R;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {

    private Context context;
    private List<User> people;

    public UserAdapter(@NonNull Context context, @NonNull List<User> people) {
        super(context, 0, people);
        this.context = context;
        this.people = people;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        }

        User currentUser = people.get(position);

        TextView txtNome = listItem.findViewById(R.id.txtDesc);
        TextView txtEmail = listItem.findViewById(R.id.txtCidade);

        txtNome.setText(currentUser.getNome());
        txtEmail.setText(currentUser.getEmail());

        return listItem;
    }
}
