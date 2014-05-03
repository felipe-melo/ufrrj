package metodos.diretos;	

import Matriz.Vetor.Excecoes;

	public class Principal {
	
		public static void main(String[] args){
		
			double[][] A = {{1, 0, 1},
							{1, 1, 0},
							{2, 3, 1}};
		
			
			double[] b = {0, 1, 1};
			
			Retrosubstituicao.TriangularSuperior(A, b);
			
			for (int i = 0; i < A.length; i++){
				for (int j = 0; j < A[0].length; j++){
					System.out.print(A[i][j] + " ");
				}
				System.out.println();
			}
	}

}
