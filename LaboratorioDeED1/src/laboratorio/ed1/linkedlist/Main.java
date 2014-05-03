package laboratorio.ed1.linkedlist;

public class Main {
	
	public static void main(String[] args){
		LinkedList linkedList = new LinkedList();
		
		linkedList.insertList("Teste 1", 0);
		linkedList.insertList("Teste 2", 0);
		linkedList.insertList("Teste 3", 1);
		linkedList.insertList("Teste 4", 3);
		linkedList.insertList("Teste 5", 3);
		linkedList.insertList("Teste 6", 0);
		linkedList.insertList("Teste 7", 0);
		
		System.out.println(linkedList);
		
		System.out.println("[" + linkedList.deleteList(1) + "]");
		
		System.out.println(linkedList);
		
		linkedList.replaceList("Teste Novo", 4);
		
		System.out.println(linkedList);
		
		LinkedListEntry aux = linkedList.getPosition(10); 
		
		if (aux != null)
			System.out.println("[" + aux.getValue() + "]");
		else
			System.out.println("Elemento não encontrado.");
	}

}
