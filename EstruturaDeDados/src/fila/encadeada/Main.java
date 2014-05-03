package fila.encadeada;

import classe.camilla.Camilla;

public class Main {
	
	public static void main(String[] args){
		Fila<Camilla> fila = new Fila<Camilla>();
		
		fila.enqueue(new Camilla("C", 45, "UFadRRJ"));
		fila.enqueue(new Camilla("A", 19, "UFRRJ"));
		fila.enqueue(new Camilla("B", 50, "UJ"));			
		fila.enqueue(new Camilla("D", 50, "UJ"));
		fila.enqueue(new Camilla("C", 45, "UFRRJ"));
		
		System.out.println(fila);
		
		System.out.println(fila.dequeue().toString());
		
		System.out.println(fila);
		
		System.out.println(fila.first().toString());
	}

}
