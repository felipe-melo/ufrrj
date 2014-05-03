package prova.primo;

public class Verifica {
	
	public static boolean ehPrimo(long num){
		
		if (num%2 == 0 && num != 2)
			return false;
		
		for (int i = 3; i <= Math.sqrt(num); i++){
			if (num%i == 0)
				return false;
		}
		
		if (num!=1)
			return true;
		else
			return false;
	}
	
	public static boolean MaiorQue8(long num){
		
		int cont = 0;
		long aux = num;
		int soma = 0;
		
		if (ehPrimo(num)){
			while (aux/10 != 0 || aux != 0){
				cont++;
				aux = aux/10;
			}
			
			for(int i = 0; i < cont; i++){
				soma += num%10;
				num = num/10;
			}
			
			if (soma > 8)
				return true;
			else
				return false;
		}
		
		return false;
	}

}
