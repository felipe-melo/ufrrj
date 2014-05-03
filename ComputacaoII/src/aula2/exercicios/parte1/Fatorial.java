package aula2.exercicios.parte1;

public class Fatorial {
	
	public static void fatorial(int num){
		long fat = 1;
		
		for (int i = 1; i <= num; i++){
			fat *= i;
		}
		
		System.out.println ("O fatorial de " + num + " eh " + fat);
	}
	
	public static void main(String[] args){
		fatorial(10);
		fatorial(15);
		fatorial(20);
	}

}
