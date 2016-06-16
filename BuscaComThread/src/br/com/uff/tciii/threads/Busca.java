package br.com.uff.tciii.threads;

import java.util.*;

public class Busca 
{	
	public static void main(String[] args) 
	{	
		final int QUANTIDADE_ITENS = 1000; //Ou seja, tamanho do vetor
		
		final int QTD_PROCESSADORES = 8; //Simula a qtd de processadores
		
		final int VALOR_PROCURADO = 10; //Valor a ser procurado no vetor
		
		//Criação e atribuição da variável para contar o tempo decorrido na criação do vetor
		long tempoDecorrido = System.currentTimeMillis();
		
		//Chamada do método para a criação do vetor de inteiros
		Object[] vetor = criaVetor(QUANTIDADE_ITENS);
		
		//A variável recebendo o valor final do tempo decorrido após a criação do vetor
		tempoDecorrido = (System.currentTimeMillis() - tempoDecorrido);
		
		System.out.println("-----------------------------------------------");
		System.out.println("Tempo para criação do vetor: " + tempoDecorrido + " ms");
		System.out.println("-----------------------------------------------");
		
		//Cria a quantidade de threads de acordo com o numero de processadores
		int qtdThreads = QTD_PROCESSADORES;
		
		// Chamada do método de busca
		buscar(qtdThreads, vetor, VALOR_PROCURADO);
		
	}
	
	//Método para criar o vetor de objetos de números inteiros
	private static Object[] criaVetor(int totalElementos) 
	{	
		//Cria a lista de inteiros e a instancia
		List<Integer> lista = new ArrayList<Integer>();
		
		//Randomiza os inteiros e guarda na variavel rand
		Random rand = new Random();
		
		//Enquanto o tamanho da lista for menor que o número de elementos
		//os numeros vão sendo adicionados de forma randomica à lista
		while (lista.size() < totalElementos) 
		{	
			//num recebe o valor rand a cada ciclo
			Integer num = rand.nextInt(totalElementos); 
			
			if(!lista.contains(num)) //verificação da existência do num na lista
			{
				lista.add(num); //Só serão adicionados numeros sem repetição
			}
		}
		
		//Retorna o vetor(lista) inteiros
		return lista.toArray();
	}
	
	//Método que efetua a busca no vetor
	private static void buscar(int qtdThreads, Object[] vetor, Object alvo) 
	{	
		// Aproximadamente quantos itens do vetor serão vasculhados por thread
		int itensPorThread = (int) Math
				.ceil((double) vetor.length / qtdThreads);
		
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
			BuscaParalela runnable = new BuscaParalela(vetor, alvo, inicio, fim);
			
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
