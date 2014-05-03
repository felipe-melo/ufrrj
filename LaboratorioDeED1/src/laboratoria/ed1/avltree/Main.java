package laboratoria.ed1.avltree;

public class Main {
	
	public static void main(String[] args){
		
		AVLTree avlTree = new AVLTree();
		
		avlTree.insertAVLTree("Teste 1", "A");
		avlTree.insertAVLTree("Teste 2", "B");
		avlTree.insertAVLTree("Teste 3", "E");
		avlTree.insertAVLTree("Teste 4", "D");
		avlTree.insertAVLTree("Teste 5", "G");
		avlTree.insertAVLTree("Teste 6", "R");
		
		avlTree.transpose();
		
		if (avlTree.searchNode("R") != null)
			System.out.println(avlTree.searchNode("R").getValue());
		else
			System.out.println("Elemento não encontrado.");
		
		avlTree.deleteNode("B");
		
		avlTree.transpose();
	}

}