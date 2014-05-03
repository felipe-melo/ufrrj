package aula2.exercicios.parte2;

public class Palindromo {
	
	public static int[] ConversaoAlgarismos(int num, int base){
		
		int aux = 1, aux2;
		
		aux2 = num;
		
		while (aux2/base != 0){
			aux++;
			aux2 = aux2/base;
		}
		
		int[] alg = new int[aux];
		
		for (int i = aux - 1; i >= 0; i--){
			alg[i] = num%base;
			num = num/base;
		}
		
		return alg;
	}
	
	public static boolean VerificaPalindromo(int[] vet){
		
		int tam = vet.length;

		for (int i = 0; i < tam; i++){
			if (vet[i] != vet[tam - i - 1]){
				return false;
			}
		}
		
		return true;
	}
	
	public static void main (String[] args){
		int num = 0, soma = 0;
		
		while (num <= 5){
			if (VerificaPalindromo(ConversaoAlgarismos(num, 10)) && VerificaPalindromo(ConversaoAlgarismos(num, 2))){
				soma += num; 
			}
			num++;
		}
		System.out.println(soma);
	}

}
