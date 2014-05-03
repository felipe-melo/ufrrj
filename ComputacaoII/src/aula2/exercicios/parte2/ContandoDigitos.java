package aula2.exercicios.parte2;

public class ContandoDigitos {
	
	public static void ContandoDig(int num){
		
		int cont = 0;
		
		for (int i = 1; i < num; i++){
			for (int j = 1; j < num; j++){
				if (i + j == num){
					cont++;
					System.out.println(i + " + " + j + " = " + num);
				}
			}
		}
		System.out.println("Podemos escrever de " + cont + " maneiras.");
	}
	
	public static void main(String[] args){
		ContandoDig(100);
	}

}
