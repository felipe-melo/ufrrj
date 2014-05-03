package aula2.exercicios.parte1;

public class MatrizTrans {	
	
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
	
	public static void Transpostador(int[][] matriz, int n, int m){
		int[][] matrizTrans = new int[n][m];
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j < m; j++){
				matrizTrans[i][j] = matriz[j][i];
				System.out.print ("'" + matrizTrans[i][j] + "' ");
			}
			System.out.println();
		}
		
		
	}
	
	public static void main(String[] args){
		Transpostador(Gerador(2, 2), 2, 2);
	}
}