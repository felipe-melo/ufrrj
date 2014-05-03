package aula2.exercicios.parte1;

public class Crivo {
	
	public static boolean VerificaPrimo(int num){
				
		for (int i = 2; i <= Math.sqrt(num); i++){
			if (num%i == 0 && num != 2){
				return false;
			}
		}
		
		return true;
	}
	
	public static void main (String[] args){
		int cont = 0, n = 2;
		
		while (cont < 1000){
			if (VerificaPrimo(n)){
				System.out.println(n);
				cont++;
			}
			n++;
		}
	}

}
