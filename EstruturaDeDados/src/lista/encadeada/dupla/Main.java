package lista.encadeada.dupla;

import classe.camilla.Camilla;

public class Main {

	public static void main(String[] args) {
			ListaDupla<Camilla> lista = new ListaDupla<Camilla>();
			
			Camilla c = new Camilla("Z", 45, "Estácio");
			
			lista.inserir(new Camilla("C", 45, "Estácio"), 0);
			lista.inserir(new Camilla("A", 19, "UFRRJ"), 0);
			
			lista.inserir(new Camilla("B", 50, "UFRJ"), 0);			
			lista.inserir(new Camilla("D", 50, "UNIRIO"), 3);
			lista.inserir(new Camilla("E", 45, "UERJ"), 4);
			
			lista.inserir(new Camilla("F", 45, "UERJ"), 3);
			lista.inserir(new Camilla("G", 45, "UERJ"), 4);
			lista.inserir(new Camilla("H", 45, "UERJ"), 5);
			
			System.out.println(lista);
			
			lista.apagar(3);
			System.out.println(lista);
			lista.apagar(0);
			System.out.println(lista);
			lista.apagar(5);
			System.out.println(lista);
			
			try {
				System.out.println(lista.Buscar(0).getValor().toString());
			} catch (Exception e) {
			}
			
			lista.Atualizar(c, 2);
			System.out.println(lista);
	}

}
