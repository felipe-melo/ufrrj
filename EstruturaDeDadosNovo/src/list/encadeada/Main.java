package list.encadeada;

public class Main {
	
	public static void main(String[] args){
		LinkedList lista = new LinkedList();
		
		lista.insertList("Teste1", 0);
		lista.insertList("Teste2", 0);
		lista.insertList("Teste3", 2);
		lista.insertList("Teste4", 2);
		lista.insertList("Teste5", 3);
		lista.insertList("Teste6", 5);
		
		System.out.println(lista);
		
		lista.deleteList(4);
		
		System.out.println(lista);
		
		lista.replaceList("Teste0", 0);
		
		System.out.println(lista);
		
		System.out.println(lista.SearchValue(2).getValue());
	}

}
