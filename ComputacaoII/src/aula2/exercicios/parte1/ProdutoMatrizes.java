package aula2.exercicios.parte1;

public class ProdutoMatrizes {
	
	public static int[][] Gerador(int n, int m){
		int[][] matriz = new int[n][m];		
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j < m; j++){
				matriz[i][j] = (int) (Math.random() * 100);
				System.out.print ("'" + matriz[i][j] + "' ");
			}
			System.out.println();
		}
		System.out.println();
		return matriz;
	}
	
	public static void ProdutoMatrix(int[][] matrizA, int[][] matrizB){
		int[][] matriz = new int[matrizA.length][matrizB[0].length];		
		
		for (int i = 0; i < matrizA.length; i++){
			for (int j = 0; j < matrizB[0].length; j++){
				for (int k = 0; k < matrizA[0].length; k++){
					matriz[i][j] += matrizA[i][k] * matrizB[k][j];
				}
				System.out.print("'" + matriz[i][j] + "' ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args){
		ProdutoMatrix(Gerador(3, 2), Gerador(2, 5));
	}

}
