package metodo.de.jacobi;

import Matriz.Vetor.*;

public class Jacobi {
	
	static void Calcula_Solucao(double[][] A, double[] x0, double[] b, double tol){
		
		double[][] T = new double[A.length][A[0].length];		
		double[] xkp1 = new double[x0.length];		
		double[] c = new double[b.length];		
		double distancia = 1;
		int cont = 0;
		
		//Gera a matriz T
		for (int i = 0; i < T.length; i++){
			for (int j = 0; j < T[0].length; j++){
				if (i == j){
					T[i][j] = 0;
					continue;
				}
				
				T[i][j] = - (A[i][j]/A[i][i]);
			}
		}
		
		//Gera o vetor C
		for (int i = 0; i < b.length; i++){
			c[i] = b[i]/A[i][i];
		}
		
		while(distancia > tol){
			
			//Zera o vetor da próxima solução
			Operacoes.ZeraVet(xkp1);
			
			System.out.println("Iteração " + cont + ":");			
			System.out.print("{");
			for (int i = 0; i < x0.length; i++){
				System.out.print(x0[i]);
				if (i < x0.length - 1)
					System.out.print(", ");
			}
			System.out.println("}");
			System.out.println();
			
			cont++;
			
			//Cálcula o xk+1 (próxima solução)
			try {
				xkp1 = Operacoes.ProdutoMatVet(T, x0);
			} catch (Excecoes e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				xkp1 = Operacoes.SomaVetorial(xkp1, c);
			} catch (Excecoes e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//Calcula a distância entre as soluções
			try {
				distancia = Operacoes.DistanciaVet(xkp1, x0);
			} catch (Excecoes e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//atualiza a última solução encontrada para calcular a próxima
			try {
				transfereValor(xkp1, x0);
			} catch (Excecoes e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		//Imprime a solução encontrada
		System.out.println("A solução aproximada do sistema foi encontrada na Iteração " + cont + ":");		
		
		System.out.print("{");
		for (int i = 0; i < xkp1.length; i++){
			System.out.print(xkp1[i]);
			if (i< xkp1.length - 1)
				System.out.print(", ");
		}
		System.out.print("}");
	}
	
	//Transfere arquivos de um vetor para outro
	public static void transfereValor(double[] x0, double[] x1) throws Excecoes{
		if (x0.length != x1.length)
			throw new Excecoes();
		else{
			for (int i = 0; i < x1.length; i++){
				x1[i] = x0[i];
			}
		}
	}

}
