package fila.sequencial;

import classe.camilla.Camilla;

public class Main {
	
	public static void main(String[] argv){
		
		Fila<Camilla> pilha = new Fila<Camilla>(10);
		
		pilha.enqueue(new Camilla("C", 45, "UFadRRJ"));
		pilha.enqueue(new Camilla("A", 19, "UFRRJ"));
		pilha.enqueue(new Camilla("B", 50, "UJ"));			
		pilha.enqueue(new Camilla("D", 50, "UJ"));
		pilha.enqueue(new Camilla("C", 45, "UFadRRJ"));
		
		System.out.println(pilha);
		
		System.out.println(pilha.dequeue().toString());
		
		System.out.println(pilha);
		
		System.out.println(pilha.top().toString());
	}
}
