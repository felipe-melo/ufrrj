package gauss.seidel;

import Matriz.Vetor.*;
import metodo.de.jacobi.*;

public class GaussSeidel {
	
	public static void Calcula_Sistema(double[][] A, double[] x0, double[] b, double tol) throws Excecoes{
		
		if (x0.length != A[0].length || A[0].length != b.length){
			throw new Excecoes();
		}
		
		double[][] T = new double[A.length][A[0].length];		
		double[] xkp1 = new double[x0.length];		
		double[] c = new double[b.length];		
		double distancia = 1;
		int cont = 0;
		
		//Gera matriz T
		for (int i = 0; i < T.length; i++){
			for (int j = 0; j < T[0].length; j++){
				if (i == j)
					T[i][j] = 0;
				else
					T[i][j] = -(A[i][j]/A[i][i]);
			}
		}
		
		//Gera o vetor C
		for (int i = 0; i < b.length; i++){
			c[i] = b[i]/A[i][i];
		}
		
		while (distancia > tol){
			
			Operacoes.ZeraVet(xkp1);
			
			System.out.println("Itera��o " + cont + ":");			
			System.out.print("{");
			for (int i = 0; i < x0.length; i++){
				System.out.print(x0[i]);
				if (i < x0.length - 1)
					System.out.print(", ");
			}
			System.out.println("}");
			System.out.println();
			
			cont++;
			
			//Calcula a pr�xima solu��o
			for (int i = 0; i < T.length; i++){
				for (int k = 0; k < i; k++){
					xkp1[i] += (T[i][k] * xkp1[k]);
				}
				
				for (int k = i; k < x0.length; k++){
					xkp1[i] += (T[i][k] * x0[k]);
				}
				
				xkp1[i] += c[i];
			}
			
			//Calcula a dist�ncia entre as solu��es
			try {
				distancia = Operacoes.DistanciaVet(xkp1, x0);
			} catch (Excecoes e) {
				e.printStackTrace();
			}
			//atualiza a �ltima solu��o encontrada para calcular a pr�xima
			Jacobi.transfereValor(xkp1, x0);
			
		}
		
		//Imprime a solu��o encontrada
		System.out.println("A solu��o aproximada do sistema foi encontrada na Itera��o " + cont + ":");		
				
		System.out.print("{");
		for (int i = 0; i < xkp1.length; i++){
			System.out.print(xkp1[i]);
			if (i < xkp1.length - 1)
				System.out.print(", ");
		}
		System.out.print("}");
	}

}
