package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Congrats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);

        View view = findViewById(R.id.fundo);
        Integer cor = Integer.parseInt(getIntent().getStringExtra("CorFundo"));
        ((TextView)findViewById(R.id.tempo)).setText("Tempo para conclusão: " + getIntent().getStringExtra("Tempo"));
        ((TextView)findViewById(R.id.erros)).setText("Erros até conclusão: " + getIntent().getStringExtra("Erros"));
        view.setBackgroundColor(cor);
    }

    public void reiniciar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        if (intent != null)
            startActivity(intent);
    }

    public void salvarPlacar(View view){
        Intent intent = new Intent(this, CadastroPontuacao.class);
        if (intent != null) {
            intent.putExtra("Tempo", getIntent().getStringExtra("Tempo"));
            intent.putExtra("Erros", getIntent().getStringExtra("Erros"));
            startActivity(intent);
        }
    }

    public void visualizaHistorico(View view){
        Intent intent = new Intent(this, Historico.class);
        if (intent != null) {
            startActivity(intent);
        }
    }
}