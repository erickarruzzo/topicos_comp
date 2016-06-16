package br.com.uff.tpiii;

public class Principal {

	public static void main(String[] args) {
		final int LINHA = 3;
		final int COLUNA = 3;
		
		System.out.println("Multiplicação de Matrizes de forma paralela");
		
		Matriz m1 = new Matriz(LINHA, COLUNA);
		Matriz m2 = new Matriz(LINHA, COLUNA);
		Matriz mR = new Matriz(m1.getLinhas(), m2.getColunas());
		
		m1.populaMatriz();
		m2.populaMatriz();
		
		System.out.println("\nMatriz 1:\n");
		m1.imprimeMatriz();
		System.out.println("\nMatriz 2:\n");
		m2.imprimeMatriz();
		
		System.out.println();
		
		if (m1.getLinhas() == m2.getColunas())
		{
			mR = m1.multiplicaComThreads(m2);
			System.out.println("\nMatriz Resultante:\n");
			mR.imprimeMatriz();
		}
		else
		{
			System.out.println("Não é possível realizar a multiplicação entre as matrizes");
		}
	}
}
