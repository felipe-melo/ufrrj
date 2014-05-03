package pilha.sequencial;

import classe.camilla.Camilla;

public class Main {
	
	public static void main(String[] argv){
		
		Pilha<Camilla> pilha = new Pilha<Camilla>(10);
		
		pilha.push(new Camilla("C", 45, "UFadRRJ"));
		pilha.push(new Camilla("A", 19, "UFRRJ"));
		pilha.push(new Camilla("B", 50, "UJ"));			
		pilha.push(new Camilla("D", 50, "UJ"));
		pilha.push(new Camilla("E", 45, "UFadRRJ"));
		
		System.out.println(pilha);
		
		System.out.println(pilha.pop());
		
		System.out.println(pilha);
		
		System.out.println(pilha.top());
		
		System.out.println(pilha);
	}
}
