package br.uff.tpiii.multiplica.thread;

import java.util.ArrayList;
import java.util.List;

public class Principal {

	public static void main(String[] args) {
		final int LINHA = 3;
		final int COLUNA = 3;
		
		Matriz matriz1 = new Matriz(LINHA, COLUNA);
		Matriz matriz2 = new Matriz(LINHA, COLUNA);
		
		matriz1.populaMatriz();
		matriz2.populaMatriz();
		
		tarefaMultiplica tarefa = new tarefaMultiplica(matriz1, matriz2);
		
		Thread t1 = new Thread(tarefa, "Tmultiplica");

	}

	//Método que efetua a busca no vetor
  	private static void multiplica(int qtdThreads, Matriz matriz1, Matriz matriz2) 
  	{	
  		// Aproximadamente quantos itens do vetor serão vasculhados por thread
  		int itensPorThread = (int) Math
  				.ceil((double) matriz1.colunas / qtdThreads);
  		
  		// Todas as threads pertencerão a esse grupo, isso é para controle
  		ThreadGroup grupo = new ThreadGroup("Grupo de Thread para Busca");
  		
  		int inicio = 0;
  		int fim = itensPorThread;
  		
  		//Crio e instancio uma lista de thread
  		List<Thread> threadList = new ArrayList<Thread>();
  		
  		//Configurando as threads.
  		for (int i = 0; i < qtdThreads; i++) 
  		{
  			if (fim >= vetor.length) 
  			{
  				fim = (vetor.length - 1);
  			}
  			
  			//Instanciando objeto do tipo runnable para as threads
  			Runnable runnable = new tarefaMultiplica(matriz1, matriz2);
  			
  			//Adicionando a thread ao grupo criado anteriormente e dando nomes a elas
  			threadList.add(new Thread(grupo, runnable, "T" + (i+1)));
  			
  			//Incrementando a variável para o inicio da próxima parte da busca à variável inicio
  			inicio = ++fim;
  			
  			//Definindo o final da proxima parte a ser percorrida
  			fim += itensPorThread;
  		}
  		
  		//Criando a variável para contar o tempo decorrido
  		long tempoDecorrido = System.currentTimeMillis();
  		
  		// Chamo o método para "startar" as threads
  		fire(threadList);
  		
  		//A variável recebendo o valor final do tempo decorrido após a busca
  		tempoDecorrido = (System.currentTimeMillis() - tempoDecorrido);
  		
  		System.out.println("\n-----------------------------------------------"
  						+  "\nTempo decorrido na busca com " + qtdThreads + " threads: " + tempoDecorrido + "ms"
  						+  "\n-----------------------------------------------");
  		
  	}
  	
  	//Método que "starta" todas as threads do grupo criado no método anterior
  	private static void fire(List<Thread> threads) 
  	{
  		for (Thread thread : threads) //foreach do java para collections, neste caso, List
  		{
  			thread.start();
  		}
  	}
	
}
