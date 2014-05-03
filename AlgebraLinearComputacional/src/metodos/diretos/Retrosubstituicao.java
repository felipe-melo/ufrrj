package metodos.diretos;

import Matriz.Vetor.*;

public class Retrosubstituicao {
	
	public static void TriangularSuperior(double[][] A, double[] b){
		
		double ap = 0, m;
		
		for (int i = 1; i < A.length; i++){
			for (int j = 0; j < A[0].length; j++){
				if (i-1 == j){
					ap = A[i-1][i-1];
					for (int k = i; k < A.length; k++){
						if (A[k][j] > A[i-1][j] && A[k][j] != 0){
							//Operacoes.PermutaLinhaMat(A, i-1, k);
							//Operacoes.PermutaElemVet(b, i-1, k);							
						}
					}
				}
				m = A[i-1][j]/ap;
				A[i][j] = A[i][j] - m * A[i-1][j];
				b[i] = b[i] - (b[i]/ap);
			}
		}
		
	}

}
