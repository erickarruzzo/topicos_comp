package br.com.uff.tciii.threads;

//Runnable é uma interface usada para threads onde existe um método RUN que a caracteriza
class BuscaParalela implements Runnable 
{
	private Object[] vetor;
	private Object alvo;
	private int inicio;
	private int fim;
	
	//Construtor da classe
	//vetor = vetor a ser percorrido, alvo = alvo a ser procurado
	//inicio = posição inicial da busca, fim = posição final da busca
	BuscaParalela(Object[] vetor, Object alvo, int inicio, int fim) 
	{
		this.vetor = vetor;
		this.alvo = alvo;
		this.inicio = inicio;
		this.fim = fim;
	}
	
	//Efetua a busca através do método RUN da interface, que todas as threads farão
	public void run() 
	{
		//Criação da variavel booleana, para uso de flag 
		boolean achou = false;
		
		System.out.println("\nIniciando busca de " + inicio + " - " + fim);
		
		//Busca do item pelo espaço do vetor definido pelas variaveis inicio e fim
		for (int i = inicio; i <= fim; i++)
		{	
			//Se o alvo for igual ao conteúdo de vetor[i], ou seja, se achar o item
			if (alvo.equals(vetor[i]))
			{
				System.out.println("\n-----------------------------------------------\n"
						+ alvo + " achado na posição " + i + " pela thread '"
						+ Thread.currentThread().getName() + "'"
						+ "\n-----------------------------------------------");
				
				achou = true;  //atribuição true à variável achou
			}
		}
		
		//Caso a thread não encontre o item no espaço destinado
		if (!achou)
		{
			System.out.println("\nThread '" + Thread.currentThread().getName()
					+ "' terminou a busca sem sucesso.");
		}
	}
}