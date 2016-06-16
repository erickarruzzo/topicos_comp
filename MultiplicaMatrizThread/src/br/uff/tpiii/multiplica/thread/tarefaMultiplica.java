package br.uff.tpiii.multiplica.thread;

public class tarefaMultiplica implements Runnable {
	public Matriz matriz1;
	public Matriz matriz2;
	public Matriz matrizResultante;
	
	public tarefaMultiplica(Matriz matriz1, Matriz matriz2){
		this.matriz1 = matriz1;
		this.matriz2 = matriz2;
		this.matrizResultante = new Matriz (matriz1.linhas, matriz2.colunas);
	}
	
	@Override
	public void run() {
		matriz1.multiplicaMatriz(matriz2, matrizResultante);

	}

}
