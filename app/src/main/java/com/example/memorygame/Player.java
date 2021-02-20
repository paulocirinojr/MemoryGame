package com.example.memorygame;

public class Player {
    private String nome, tempo;
    private int erros;

    public Player(){
        nome = tempo = "";
        erros = 0;
    }

    public int getErros() {
        return erros;
    }

    public String getNome() {
        return nome;
    }

    public String getTempo() {
        return tempo;
    }

    public void setErros(int erros) {
        this.erros = erros;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }
}
