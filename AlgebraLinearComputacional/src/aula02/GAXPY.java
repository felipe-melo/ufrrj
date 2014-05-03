package aula02;

public class GAXPY {
	
	public static void main (String[] args){
		int[] x = {1, 2}, y = {0, 1};
		int[][] A ={{1, 0}, {2, 3}};
		int n = 2;
		
		/*for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				y[i] += A[i][j] * x[j];
			}
			System.out.print(y[i] + " ");
		}
		
		System.out.println();
		
		for (int j = 0; j < n; j++){
			for (int i = 0; i < n; i++){
				y[i] += A[i][j] * x[j];
			}
			System.out.print(y[i] + " ");
		}*/
		
		for (int i = 0; i < n; i++){
			y = ProdutoLinhas(A[i], x, n);
			
			System.out.print(y[i] + " ");
		}		
	}
	
	public static int[] ProdutoLinhas(int[] v1, int[] v2, int tam){
		
		int[] vet = new int[tam];
		
		for (int i = 0; i < tam; i++){
			vet[i] += v1[i] * v2[i];
		}
		
		return vet;
	}

}
