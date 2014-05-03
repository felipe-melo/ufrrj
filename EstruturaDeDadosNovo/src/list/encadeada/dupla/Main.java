package list.encadeada.dupla;


public class Main {
	
	public static void main(String[] args){
		DoubleLinkedList lista = new DoubleLinkedList();
		
		lista.insertList("Teste1", 0);
		lista.insertList("Teste2", 0);
		lista.insertList("Teste3", 2);
		lista.insertList("Teste4", 2);
		lista.insertList("Teste5", 3);
		lista.insertList("Teste6", 5);
		
		System.out.println(lista);
		
		System.out.println(lista.deleteList(3));
		
		System.out.println(lista);
		
		lista.replaceList("TesteNovo", 3);
		
		System.out.println(lista);
	}
}
