package laboratorio.ed1.contiguouslist;

public class Main {
	
	public static void main(String[] args){
		
		ContiguousList listaContigua = new ContiguousList(8);
		
		listaContigua.insertList("Teste 0", 0);
		listaContigua.insertList("Teste 1", 1);
		listaContigua.insertList("Teste 2", 2);
		listaContigua.insertList("Teste 3", 4);
		listaContigua.insertList("Teste 5", 5);
		listaContigua.insertList("Teste 6", 6);
		listaContigua.insertList("Teste 7", 7);
		
		System.out.println(listaContigua);
		
		listaContigua.insertList("Teste 4", 4);
		
		System.out.println(listaContigua);
		
		listaContigua.replaceList("Teste Novo", 5);
		
		System.out.println(listaContigua);
		
		listaContigua.deleteList(5);
		
		System.out.println(listaContigua);
	}

}
