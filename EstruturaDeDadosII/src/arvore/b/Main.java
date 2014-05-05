package arvore.b;


public class Main {
	
	public Integer[] a = new Integer[10];
	public static void main(String[] args){
		
		B_Tree tree = new B_Tree(1);
		
		tree.insert(1, "Teste1");
		tree.insert(2, "Teste2");
		tree.insert(3, "Teste3");
		tree.insert(4, "Teste4");
		tree.insert(5, "Teste5");
		tree.insert(6, "Teste6");
		tree.insert(7, "Teste7");
		
		try {
			System.out.println(tree.searchValue(5).toString());
		} catch (NullPointerException e) {
			System.out.println("Elemento não encontrado");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}