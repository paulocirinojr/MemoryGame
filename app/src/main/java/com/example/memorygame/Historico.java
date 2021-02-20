package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Historico extends AppCompatActivity {
    private Database db;
    int tipoHistorico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        ListView listView = (ListView) findViewById(R.id.lista);

        db = new Database(this);
        String [] itens = new String[db.all().size()];
        int i = 0;
        for (Player p : db.all())
            itens[i++] = String.format("Nome: %s | Tempo: %s",p.getNome(),p.getTempo());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,itens);

        listView.setAdapter(adapter);

        tipoHistorico = 1; /// Tempo
    }

    public void reiniciarJogo(View view){
        Intent intent = new Intent(this, MainActivity.class);
        if (intent != null)
            startActivity(intent);
    }

    public void alteraHistorico(View view){
        ListView listView = (ListView) findViewById(R.id.lista);
        String [] itens = new String[db.all().size()];
        Button botaoHistorico = (Button) findViewById(R.id.botaoHistorico);
        int i = 0;
        for (Player p : db.all()) {
            if (tipoHistorico == 1)
                itens[i++] = String.format("Nome: %s | Tempo: %s",p.getNome(),p.getTempo());
            else
                itens[i++] = String.format("Nome: %s | Erros: %d", p.getNome(), p.getErros());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,itens);
        listView.setAdapter(adapter);

        if (tipoHistorico == 1) {
            tipoHistorico = 2;
            botaoHistorico.setText("Por Erros");
        }
        else {
            tipoHistorico = 1;
            botaoHistorico.setText("Por Tempo");
        }
    }

    public void limparHistorico(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Confirmação");
        builder.setMessage("Deseja limpar o histórico?");
        builder.setPositiveButton("Limpar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.clear();
                        ListView listView = (ListView) findViewById(R.id.lista);
                        listView.setAdapter(null);
                    }
                });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}