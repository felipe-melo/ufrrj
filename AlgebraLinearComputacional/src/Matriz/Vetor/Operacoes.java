package Matriz.Vetor;

public class Operacoes {
	
	//Soma Vetores
	public static double[] SomaVetorial(double[] A, double[] B) throws Excecoes{
		if (A.length != B.length) throw new Excecoes();
		
		double[] C = new double[A.length];
		
		for (int i = 0; i < A.length; i++){
			C[i] = A[i] + B[i];
		}
		return C;
	}
	
	//Multiplica Matrizes
	public static double[][] Multiplicacao(double[][] mat1, double[][] mat2) throws Excecoes{
		double[][] result = new double[mat1.length][mat2[0].length];
		
		if (mat1[0].length != mat2.length){
			throw new Excecoes();
		}else{
			for (int i = 0; i < mat1.length; i++){
				for (int j = 0; j < mat2[0].length; j++){
					for (int k = 0; k < mat1[0].length; k++){
						result[i][j] += mat1[i][k] * mat2[k][j];
					}
				}
			}
			return result;
		}
	}
	
	//Transpoem Matriz
	public static double[][] TransposicaoMat(double[][] mat){
		double[][] temp = new double[mat[0].length][mat.length];
		for (int i = 0; i < mat.length; i++){
			for (int j = 0; j < mat[0].length; j++){
				temp[j][i] = mat[i][j];
			}
		}
		return temp;
	}
	
	//Zera Matriz
	public static void ZeraMat(double[][] mat){
		for (int i = 0; i < mat.length; i++){
			for (int j = 0; j < mat[0].length; j++){
				mat[j][i] = 0;
			}
		}
	}
	
	//Zera Vetor
	public static void ZeraVet(double[] vet){
		for (int i = 0; i < vet.length; i++){
			vet[i] = 0;
		}
	}
	
	//Calcula a Norma Vetorial no L2
	public static double CalculaNorma2Vet(double[] Vet){		
		double norma = 0;
		
		for (int i = 0; i < Vet.length; i++){
			norma += Math.pow(Vet[i], 2);
		}
		
		norma = Math.sqrt(norma);
		
		return norma;
	}
	
	//Calcula a Norma Vetorial no infinito
	public static double CalculaNormaInfVet(double[] Vet){		
		double norma = Math.pow(Vet[0], 2);
		norma = Math.sqrt(norma);
		double temp;
		
		for (int i = 0; i < Vet.length; i++){
			temp = Math.pow(Vet[0], 2);
			temp = Math.sqrt(temp);
			if (Vet[i] > temp){
				norma = temp;
			}
		}
		
		return norma;
	}
	
	//Cacula a Norma Matrizial no L2
	public static double CalculaNorma2Mat(double[][] mat){
		double traco = 0;
		double[][] temp = new double[mat[0].length][mat.length];
		double[][] multi = new double[mat.length][mat.length];
		temp = TransposicaoMat(mat);
		try {
			multi = Multiplicacao(temp, mat);
		} catch (Excecoes e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < multi.length; i++){
			traco += Math.sqrt(Math.pow(multi[i][i], 2));
		}
		
		return Math.sqrt(traco);
		
	}
	
	//Cacula a Norma Matrizial no Infinito
	public static double CalculaNormaInfMat(double[][] mat){
		
		double aux = 0;
		double max = 0;
		for (int i = 0; i < mat.length; i++){
			for (int j = 0; j < mat[0].length; j++){
				aux += Math.sqrt(Math.pow(mat[i][j], 2));
				if (aux > max)
					max = aux;
			}
			aux = 0;
		}
		return max;
	}
	
	//Calcula a dist�ncia entre vetores
	public static double DistanciaVet(double[] A, double[] B) throws Excecoes{
		if (A.length != B.length){
			throw new Excecoes();
		}
		
		double[] C = new double[A.length];
		double norma;
			
		for (int i = 0; i < A.length; i++){
			C[i] = A[i] - B[i];
		}
			
		norma = CalculaNorma2Vet(C);
			
		return norma;
	}
	
	//Calcula dist�ncia entre matrizes
	public static double[][] DistanciaMat(double[][] A, double[][] B) throws Excecoes{
		if (A.length != B.length || A[0].length != B[0].length){
			throw new Excecoes();
		}else{
			double[][] C = new double[A.length][A[0].length];
			
			for (int i = 0; i < C.length; i++){
				for (int j = 0; j < C[0].length; j++){
					C[i][j] = A[i][j] - B[i][j];
				}
			}
			return C;
		}
	}
	
	//Realiza produto entre uma matriz e um vetor
	public static double[] ProdutoMatVet(double[][] A, double[] x) throws Excecoes{
		if (A[0].length != x.length) throw new Excecoes();
		
		double[] vet = new double[A.length];
		
		for (int i = 0; i < A.length; i++){
			for (int k = 0; k < x.length; k++){
				vet[i] += A[i][k] * x[k];
			}
		}
		
		return vet;
	}
	
	public static void PermutaLinhaMat(double[][] mat, int l1, int l2){
		double aux;
		
		for (int j = 0; j < mat[0].length; j++){
			aux = mat[l1][j];
			mat[l1][j] = mat[l2][j];
			mat[l2][j] = aux;
		}
	}
	
	public static void PermutaElemVet(double[] vet, int i1, int i2){
		double aux;
		
		aux = vet[i1];
		vet[i1] = vet[i2];
		vet[i2] = aux;
	}

}