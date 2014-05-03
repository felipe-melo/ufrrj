package list.sequencial;

import inter.List;

public class Main {
	
	public static void main(String[] args){
		
		List lista = new ListaContigua(10);
				
		lista.insertList("Teste1", 0);
		lista.insertList("Teste2", 0);
		lista.insertList("Teste3", 2);
		lista.insertList("Teste4", 3);
		lista.insertList("Teste5", 4);
		
		System.out.println(lista);
		
		lista.deleteList(2);
		
		System.out.println(lista); 
	}

}
