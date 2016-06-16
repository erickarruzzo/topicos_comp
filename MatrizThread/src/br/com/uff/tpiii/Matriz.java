package br.com.uff.tpiii;
import java.util.Random;

public class Matriz {
	
	//Atributos da classe
	private int[][] valores;
	private int linhas, colunas;
	
	//método get para pegar os valores no determinado campo da matriz
	public int[][] getValores() 
	{
		return valores;
	}
	
	//método get para pegar o valor do número de linhas da matriz
	public int getLinhas() 
	{
		return linhas;
	}

	//método get para pegar o valor do número de colunas da matriz
	public int getColunas() 
	{
		return colunas;
	}

	//Construtor
	public Matriz(int lins, int cols)
	{
		this.linhas = lins;
		this.colunas = cols;
		this.valores = new int[lins][cols];
	}
	
	//Método para realizar a multiplicação
	public Matriz multiplicaComThreads(final Matriz outra) 
	{
		//Criando a matriz resultante sendo Constante para a classe tarefaThread poder usar
		final Matriz matrizResultante = new Matriz(this.linhas, outra.colunas);
		
		/* Classe criada para implementar a interface Runnable necessária 
		 * para criação de threads */
		class tarefaThread implements Runnable 
		{
			private int linha;
			
			//Contrutor
			public tarefaThread(int linha) 
			{
				this.linha = linha;
			}
			
			/* Método RUN é o método principal que toda thread criada 
			 * do tipo tarefaThread deverá fazer */
			@Override
			public void run() {
				multiplicaLinha(matrizResultante, outra, linha);
			}
		}
		
		//Criando vetor de thread para agrupá-las
		Thread[] ts = new Thread[matrizResultante.linhas];
		
		//São criadas threads de acordo com o número de linhas da matriz
		for (int i = 0; i < matrizResultante.linhas; i++) 
		{
			/* Instanciando o objeto Runnable do tipo 
			 * tarefaThread necessário para criar a thread */
			Runnable r = new tarefaThread(i);
			
			//Criando e nomeando a thread
			ts[i] = new Thread(r, "T" + i);
			
			//Inicializando a thread
			ts[i].start();
			
			//Tratando possíveis exceções em tempo de execução
			try 
			{
				/* A thread ts[i] tem que ser finalizada 
				 * para então poder iniciar outra thread */
				ts[i].join();
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		//Retornando o valor da Matriz Resultante		
		return matrizResultante;
	}

	//Método privado responsável pela multiplicação de cada linha
	private void multiplicaLinha(Matriz m, Matriz outra, int i)
	{
		long tempoDecorrido = (System.currentTimeMillis());
		String nomeThread = Thread.currentThread().getName();
		int j, k;
		for(j = 0; j < m.colunas; j++) 
		{
			for(k = 0; k < outra.linhas; ++k) 
			{
				m.valores[i][j] += (this.valores[i][k] * outra.valores[k][j]);
			}
		}
		
		tempoDecorrido = ((System.currentTimeMillis()) - tempoDecorrido);
		System.out.println("Linha " + i + " multiplicada pela " + nomeThread + " em " + tempoDecorrido + "ms.");
	}
	
	//Método que preenche de forma randomizada as matrizes
	public Matriz populaMatriz() 
	{
		Random rand = new Random();
		for(int i = 0;i<this.linhas;i++)
		{
            for(int j = 0;j<this.colunas;j++)
            {
            	this.valores[i][j] = rand.nextInt(3) + 1;
            }
		}
		return this;
	}
	
	//Método responsável por imprimir os valores das matrizes no console
	public void imprimeMatriz()
	{
		for(int i=0; i<this.linhas;i++)
		{
			for(int j =0; j<this.colunas;j++)
			{
				System.out.println("[" + i + "][" + j + "] = " + this.valores[i][j]);
			}
		}
	}
}
