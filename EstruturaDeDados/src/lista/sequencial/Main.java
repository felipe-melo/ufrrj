package lista.sequencial;

import classe.camilla.Camilla;

public class Main {
	
	public static void main(String[] args){
		ListaSequencial<Camilla> lista = new ListaSequencial<Camilla>(10);
		
		lista.inserir(new Camilla("C", 45, "UFadRRJ"), 0);
		lista.inserir(new Camilla("A", 19, "UFRRJ"), 0);
		lista.inserir(new Camilla("B", 50, "UJ"), 2);
		lista.inserir(new Camilla("D", 50, "UJ"), 2);
		lista.inserir(new Camilla("C", 45, "UFadRRJ"), 3);
		
		System.out.println(lista);
		
		lista.apagar(2);
		
		System.out.println(lista);
		
		System.out.println(lista.getValor(3));
	}

}
