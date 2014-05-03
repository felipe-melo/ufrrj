package list.encadeada.circular;

public class Main {
	
	public static void main(String[] args){
		
		CircusList lista = new CircusList();
		
		lista.insertList("Teste1", 0);
		lista.insertList("Teste0", 0);
		lista.insertList("Teste2", 2);
		lista.insertList("Teste4", 4);
		lista.insertList("Teste3", 3);
		lista.insertList("Teste6", 6);
		lista.insertList("Teste5", 5);
		
		System.out.println(lista);
	}

}
