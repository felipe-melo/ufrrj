package Matriz.Vetor;

public class Teste {
	
	public static void main(String[] args){
		
		double aux;
		
		double[][] L = {{2.236, 0, 0},
						{0, 0.5, 0},
						{1.118, 0, 0.935}};
		
		double[][] U = {{2.236, 0, 1.118},
						{0, 0.5, 0},
						{0, 0, 0.935}};
		
		double[][] C = new double[3][3];
		
		try {
			C = Operacoes.Multiplicacao(L, U);
		} catch (Excecoes e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < C.length; i++){
			for (int j = 0; j < C[0].length; j++){
				System.out.print((int)C[i][j] + " ");
			}
			System.out.println();
		}
	}

}
