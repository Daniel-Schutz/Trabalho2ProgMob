package com.example.trabalho2progmob.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.trabalho2progmob.entities.User;
import com.example.trabalho2progmob.database.LocalDatabase;
import com.example.trabalho2progmob.databinding.ActivityLoginBinding;

public class LoginView extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LocalDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = LocalDatabase.getDatabase(getApplicationContext());
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.editTextTextEmailAddress.getText().toString();
                String senha = binding.editTextTextPassword.getText().toString();

                if (verificarLogin(email, senha)) {
                    Intent it = new Intent(LoginView.this, AddressList.class);
                    startActivity(it);
                    finish();
                } else {
                    Toast.makeText(LoginView.this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent it = new Intent(LoginView.this, RegisterUserView.class);
                startActivity(it);
            }
        });

    }
    private boolean verificarLogin(String email, String senha) {
        User user = db.userModel().getUserByEmailAndPassword(email, senha);
        return user != null;
    }

}


