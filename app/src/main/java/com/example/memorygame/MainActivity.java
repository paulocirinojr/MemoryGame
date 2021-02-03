package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    List<View> botoes;
    List<Integer> sequencia, seqAux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botoes = new ArrayList<>();
        sequencia = new ArrayList<>();

        botoes.add((Button) findViewById(R.id.button1));
        botoes.add((Button) findViewById(R.id.button2));
        botoes.add((Button) findViewById(R.id.button3));
        botoes.add((Button) findViewById(R.id.button4));
        botoes.add((Button) findViewById(R.id.button5));
        botoes.add((Button) findViewById(R.id.button6));

        criaSeq();

    }

    private void criaSeq(){
        for(int i = 0; i < 6; i++)
            sequencia.add(i+1);

        Collections.shuffle(sequencia);
        seqAux = new ArrayList<>();

        seqAux.addAll(sequencia);
    } /// criaSeq()

    public void verificaBotao(View view){
        Button btn = (Button) view;
        double progress, tam;

        /// Verifica se o botão clicado é o primeiro da sequencia.
        if (btn.getText().equals(sequencia.get(0).toString())) {
            View fundo = findViewById(R.id.fundo),
                    barraProgresso = findViewById(R.id.progressBar);

            /// Seta a cor do fundo
            fundo.setBackgroundColor((botoes.get(sequencia.get(0)-1).getBackgroundTintList().getDefaultColor()));

            /// Oculta o botão
            view.setVisibility(View.INVISIBLE);

            /// Remove o número correto da sequência.
            sequencia.remove(0);

            /// Altera a barra de progresso.
            tam = (double)sequencia.size();
            ProgressBar progressBar = (ProgressBar) barraProgresso;
            progress = new Double (tam / 6);
            progress *= 100;
            progressBar.setProgress(100 - (int) progress);
        }
        else{
            reiniciar(view);
        }



    } // verificaBotao()

    public void reiniciar(View view){
        sequencia.clear();
        sequencia.addAll(seqAux);
        View fundo = findViewById(R.id.fundo);
        fundo.setBackgroundColor(Color.WHITE);

        for (int i = 0 ; i < 6 ; i++)
            botoes.get(i).setVisibility(View.VISIBLE);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(0);
    }
}