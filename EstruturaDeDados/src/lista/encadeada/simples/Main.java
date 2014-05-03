package lista.encadeada.simples;

import classe.camilla.Camilla;

public class Main {

	public static void main(String[] args) {
			ListaSimples<Camilla> lista = new ListaSimples<Camilla>();
						
			lista.Inserir(new Camilla("E", 45, "Estácio"), 0);
			lista.Inserir(new Camilla("A", 19, "UFRRJ"), 0);
			lista.Inserir(new Camilla("B", 50, "UFRJ"), 0);			
			lista.Inserir(new Camilla("D", 50, "UNIRIO"), 0);
			lista.Inserir(new Camilla("C", 45, "UERJ"), 3);
			lista.Inserir(new Camilla("B", 45, "UERJ"));
			
			System.out.println(lista);
			
			lista.Apagar(3);
			
			System.out.println(lista);
			
			System.out.println(lista.Buscar(3));
	}

}
