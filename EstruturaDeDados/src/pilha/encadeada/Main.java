package pilha.encadeada;

import classe.camilla.Camilla;

public class Main {
	
	public static void main(String[] args){
		Pilha<Camilla> fila = new Pilha<Camilla>();
		
		fila.push(new Camilla("C", 45, "UFadRRJ"));
		fila.push(new Camilla("A", 19, "UFRRJ"));
		fila.push(new Camilla("B", 50, "UJ"));			
		fila.push(new Camilla("D", 50, "UJ"));
		fila.push(new Camilla("E", 45, "UFadRRJ"));
		
		System.out.println(fila);
		
		System.out.println(fila.pop());
		
		System.out.println(fila);
		
		System.out.println(fila.top());
		
		System.out.println(fila);
	}

}
