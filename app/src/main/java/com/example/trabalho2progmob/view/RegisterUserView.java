package com.example.trabalho2progmob.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalho2progmob.database.LocalDatabase;

import com.example.trabalho2progmob.databinding.ActivityRegisterUserBinding;
import com.example.trabalho2progmob.entities.User;

public class RegisterUserView extends AppCompatActivity {
    private ActivityRegisterUserBinding binding;
    private LocalDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());


        binding.btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(RegisterUserView.this, LoginView.class);
                startActivity(it);
            }
        });


    }

    public void saveUser(View view) {
        String nomeUser = binding.editTextText.getText().toString();
        if (nomeUser.equals("")) {
            Toast.makeText(this, "Adicione um nome.", Toast.LENGTH_SHORT).show();
            return;
        }
        String nomeEmail = binding.editTextTextEmailAddress2.getText().toString();
        if (nomeEmail.equals("")) {
            Toast.makeText(this, "Adicione um email.", Toast.LENGTH_SHORT).show();
            return;
        }
        String nomeSenha = binding.editTextTextPassword2.getText().toString();
        if (nomeSenha.equals("")) {
            Toast.makeText(this, "Adicione uma senha.", Toast.LENGTH_SHORT).show();
            return;
        }

        User thisUser = new User();
        thisUser.setNome(nomeUser);
        thisUser.setEmail(nomeEmail);
        thisUser.setSenha(nomeSenha);

        db.userModel().insertAll(thisUser);
        Toast.makeText(this, "Usuário criado com sucesso.", Toast.LENGTH_SHORT).show();

        Intent it = new Intent(RegisterUserView.this, AddressList.class);
        startActivity(it);
        finish();


    }

}

