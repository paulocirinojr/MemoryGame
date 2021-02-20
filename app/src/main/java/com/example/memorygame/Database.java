package com.example.memorygame;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String DATABASE_NAME = "bd1";
    private static final int DATABASE_ACCESS = 0;

    private static final String SQL_STRUCT = "CREATE TABLE IF NOT EXISTS player (id_ INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL, tempo TEXT, erros INTEGER);";
    private static final String SQL_INSERT = "INSERT INTO player(nome,tempo,erros) VALUES ('%s', '%s', '%d');";
    private static final String SQL_SELECT_ALL = "SELECT * FROM player ORDER BY nome;";
    private static final String SQL_CLEAR = "DROP TABLE IF EXISTS player;";

    private SQLiteDatabase database;
    private Cursor cursor;
    private int indexID, indexNome, indexTempo, indexErros;

    public Database(Context context) {
        database = context.openOrCreateDatabase(DATABASE_NAME,DATABASE_ACCESS,null);
        database.execSQL(SQL_STRUCT);
    }

    public void clear(){
        database.execSQL(SQL_CLEAR);
    }

    public void close(){
        database.close();
    }

    public void insert(Player player){
        String query = String.format(SQL_INSERT,player.getNome(),player.getTempo(),player.getErros());
        database.execSQL(query);
    }

    public List<Player> all(){
        List<Player> players = new ArrayList<>();
        Player player;

        cursor = database.rawQuery(SQL_SELECT_ALL,null);

        if(cursor.moveToFirst()){
            indexID = cursor.getColumnIndex("id_");
            indexNome = cursor.getColumnIndex("nome");
            indexErros = cursor.getColumnIndex("erros");
            indexTempo = cursor.getColumnIndex("tempo");

            do{
                player = new Player();
                player.setNome(cursor.getString(indexNome));
                player.setErros(cursor.getInt(indexErros));
                player.setTempo(cursor.getString(indexTempo));

                players.add(player);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return players;
    }

}
