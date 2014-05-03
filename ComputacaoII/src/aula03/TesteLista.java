package aula03;

public class TesteLista {
	
	public static void main (String[] args){
		Lista lista1 = new Lista(10);
		
		lista1.adicionarValor(56);
		lista1.adicionarValor(84);
		lista1.adicionarValor(2);
		lista1.adicionarValor(27);
		
		lista1.ordenarLista();
		
		lista1.imprimirLista();
	}

}
