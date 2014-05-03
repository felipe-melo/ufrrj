package aula02;

public class ProdutoMatricial {
	
	public static void main(String[] args){
		int[][] A = {{1, 2}, {3, 4}}, B = {{5, 6},{7, 8}};
		int[][] C = {{0, 0}, {0, 0}};
		int n = 2;
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				for (int k = 0; k < n; k++){
					C[i][j] += A[i][k] * B[k][j];
				}
				System.out.print(C[i][j] + " ");
			}
			System.out.println();
		}
	}

}