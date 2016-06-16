package br.uff.tpiii.multiplica.thread;

import java.util.ArrayList;
import java.util.List;

import br.com.uff.tciii.threads.BuscaParalela;

public class Matriz{
    public int colunas;
    public int linhas;   
    public int valoresMatriz [][];
    
    //Construtor
    public Matriz(int colunas,int linhas){
    	this.linhas = linhas;
        this.colunas = colunas;
    }
    
    public void populaMatriz(){
        for(int i = 0;i<this.linhas;i++)
            for(int j = 0;j<this.colunas;j++)
            	this.valoresMatriz[i][j] = this.valorAleatorio();
    }
    
    private int valorAleatorio(){
        return new java.util.Random().nextInt(100) + 1;
    }
    
    public void multiplicaMatriz (Matriz matriz,Matriz resultado){
    	for (int l=0;l<this.linhas;l++){
    		resultado.valoresMatriz[l][n]+=(this.valoresMatriz[l][n])*(matriz.valoresMatriz[n][c]);
        }
    }
    
  
}