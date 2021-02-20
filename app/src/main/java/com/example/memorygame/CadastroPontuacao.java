package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CadastroPontuacao extends AppCompatActivity {
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pontuacao);

        db = new Database(this);
    }

    /// Recupera os dados e envia pro BD.
    public void salvarPontuacao(View view){
        String tempo = getIntent().getStringExtra("Tempo");
        int erros = Integer.parseInt(getIntent().getStringExtra("Erros"));
        TextInputEditText nome = (TextInputEditText)findViewById(R.id.nome);
        Player player = new Player();

        player.setTempo(tempo);
        player.setErros(erros);
        player.setNome(nome.getText().toString());

        db.insert(player);
        db.close();

        Intent intent = new Intent(this, Historico.class);
        if (intent != null)
            startActivity(intent);
    }
}