package prova.primo;

import java.util.Scanner;

public class Principal {
	
	public static void main(String[] args){
		
		Scanner ler = new Scanner(System.in);
		
		int teste = ler.nextInt();
		
		for (long i = 0; i <= teste; i++){
			if (Verifica.MaiorQue8(i)){
				System.out.println(i);
			}
		}
		
		String nome1 = "Felipe";
		String nome2 = "Felipa";
			
		
	}

}
