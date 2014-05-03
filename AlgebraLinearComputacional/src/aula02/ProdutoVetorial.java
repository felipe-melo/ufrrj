package aula02;

public class ProdutoVetorial {
	
	public static void main (String[] args){
		int[][] A = {{1, 0}, {2, 3}};
		int[] x = {1, 2}, y = {0, 1};
		int n = 2;
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				A[i][j] += x[i] * y[j];
				System.out.print (A[i][j] + " ");
			}
			System.out.println();
		}
	}

}
