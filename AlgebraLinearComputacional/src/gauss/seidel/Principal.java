package gauss.seidel;	

import Matriz.Vetor.Excecoes;

	public class Principal {
	
		public static void main(String[] args){
		
			double[][] A = {{10, -1, 2, 0},
							{-1, 11, -1, 3},
							{2, -1, 10, -1},
							{0, 3, -1, 8}};
		
			double[] x0 = {0, 0, 0, 0};
			double[] b = {6, 25, -11, 15};
			double[] x = new double[x0.length];
			double tol = Math.pow(10, -3);
		
			try {
				GaussSeidel.Calcula_Sistema(A, x0, b, tol);
			} catch (Excecoes e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
