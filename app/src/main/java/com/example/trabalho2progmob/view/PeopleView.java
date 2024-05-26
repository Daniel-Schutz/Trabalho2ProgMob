package com.example.trabalho2progmob.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.util.Patterns;
import com.example.trabalho2progmob.R;
import com.example.trabalho2progmob.database.LocalDatabase;
import com.example.trabalho2progmob.databinding.ActivityPeopleViewBinding;
import com.example.trabalho2progmob.entities.User;

public class PeopleView extends AppCompatActivity {
    private LocalDatabase db;
    private ActivityPeopleViewBinding binding;
    private int dbPeopleID;
    private User dbPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPeopleViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        dbPeopleID = getIntent().getIntExtra(
                "USER_SELECIONADO_ID", -1);
    }
    protected void onResume() {
        super.onResume();
        if(dbPeopleID >= 0) {
            getDBPeople();
        } else {
            binding.btnExcluirUser.setVisibility(View.GONE);
        }
    }
    private void getDBPeople() {
        dbPeople = db.userModel().getUser(dbPeopleID);
        binding.edtEmail.setText(dbPeople.getEmail());
        binding.edtNome.setText(dbPeople.getNome());
        binding.edtSenha.setText(dbPeople.getSenha());
    }

    public void saveUser(View view) {
        String nomeUser = binding.edtNome.getText().toString();
        if (nomeUser.equals("")) {
            Toast.makeText(this, "Adicione um nome.", Toast.LENGTH_SHORT).show();
            return;
        }
        String nomeEmail = binding.edtEmail.getText().toString();
        if (nomeEmail.equals("")) {
            Toast.makeText(this, "Adicione um email.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(nomeEmail).matches()) {
            Toast.makeText(this, "Formato de email inválido.", Toast.LENGTH_SHORT).show();
            return;
        }
        String nomeSenha = binding.edtSenha.getText().toString();
        if (nomeSenha.equals("")) {
            Toast.makeText(this, "Adicione uma senha.", Toast.LENGTH_SHORT).show();
            return;
        }

        User thisUser = new User();
        thisUser.setNome(nomeUser);
        thisUser.setEmail(nomeEmail);
        thisUser.setSenha(nomeSenha);
        if (dbPeople != null) {
            thisUser.setUserID(dbPeopleID);
            db.userModel().update(thisUser);
            Toast.makeText(this, "Usuário atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            db.userModel().insertAll(thisUser);
            Toast.makeText(this, "Usuário criado com sucesso.", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void deleteUser(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Exclusão de Usuário")
                .setMessage("Deseja excluir essaUsuário?")
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
        db.userModel().delete(dbPeople);
        Toast.makeText(this, "Usuário excluído com sucesso", Toast.LENGTH_SHORT).show();
        finish();
    }
    public void voltar(View view) {
        finish();
    }
}