package aula2.exercicios.parte2;

public class SomaInteiros {
	
	public static int DecomposicaoInteira (int num, int alg, int referencia){
		
		if (alg >= 1){
			int aux = 0;
			for (int i = 0; i < num; i++){
				aux += DecomposicaoInteira (num, alg - 1, i + referencia);
			}
			return 0;
		}else{
			return referencia;
		}
	}
	
	public static void main(String[] args){
		
	}

}
