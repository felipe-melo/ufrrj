package laboratoria.ed1.avltree;

public class AVLTree {
	
	private TreeNode root;
	private int quantNode;

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}
	
	public ListEntry createTreeEntry(Object value, Comparable key){
		return new ListEntry(value, key);
	}
	
	public void insertAVLTree(Object value, Comparable key){
		ListEntry entry = new ListEntry(value, key);
		this.setRoot(this.insert(entry, this.getRoot()));
	}
	
	private TreeNode insert(ListEntry entry, TreeNode tree){
		if (tree == null){
			
			tree = new TreeNode();
			
			tree.setValue(entry);
			tree.setLeft(null);
			tree.setRight(null);
			
		}else if (entry.keyLowerThan(tree.getValue().getKey())){
			tree.setLeft(insert(entry, tree.getLeft()));
			if ((height(tree.getLeft()) - height(tree.getRight())) == 2){
				if (entry.keyLowerThan(tree.getLeft().getValue().getKey())){
					tree = this.rightRotation(tree);
				}else{
					tree = this.doubleRightRotation(tree);
				}
			}
		}else{
			tree.setRight(insert(entry, tree.getRight()));
			if ((height(tree.getRight()) - height(tree.getLeft())) == 2){
				if (entry.keyGreaterThan(tree.getRight().getValue().getKey())){
					tree = this.leftRotation(tree);
				}else{
					tree = this.doubleLeftRotation(tree);
				}
			}
		}
		tree.setHeight(Math.max(height(tree.getLeft()), height(tree.getRight())) + 1);
		return tree;
	}	
	
	private TreeNode rightRotation(TreeNode tree){
		TreeNode aux = tree.getLeft();
		tree.setLeft(aux.getRight());
		aux.setRight(tree);
		
		tree.setHeight(Math.max(height(tree.getLeft()), height(tree.getRight())) + 1);
        aux.setHeight(Math.max(height(aux.getRight()), tree.getHeight()) + 1);
		
		return aux;
	}
	
	private TreeNode doubleRightRotation(TreeNode tree){
		tree.setRight(this.leftRotation(tree.getLeft()));
		return this.rightRotation(tree);
	}
	
	private TreeNode leftRotation(TreeNode tree){
		TreeNode aux = tree.getRight();
		tree.setRight(aux.getLeft());
		aux.setLeft(tree);
		
		tree.setHeight(Math.max(height(tree.getLeft()), height(tree.getRight())) + 1);
        aux.setHeight(Math.max(height(aux.getRight()), tree.getHeight()) + 1);
		
		return aux;
	}
	
	public ListEntry searchNode(Comparable key){
		TreeNode aux = search(key, this.getRoot());
		
		if (aux != null)
			return aux.getValue();
		else 
			return null;
	}
	
	private TreeNode search(Comparable key, TreeNode tree){
		
		if (tree == null)
			return null;
		
		if (tree.getValue().keyEqualsThan(key))
			return tree;
		else if (tree.getValue().keyLowerThan(key))
			return search(key, tree.getRight());
		else
			return search(key, tree.getLeft());
	}
	
	public void deleteNode(Comparable key){
		ListEntry entry = new ListEntry(null, key);
		this.setRoot(this.delete(entry, this.getRoot()));
	}
	
	private TreeNode delete(ListEntry entry, TreeNode tree){
		if (tree != null){
			if (entry.keyLowerThan(tree.getValue().getKey())){
				tree.setLeft(delete(entry, tree.getLeft()));
			}else if (entry.keyGreaterThan(tree.getValue().getKey())){
				tree.setRight(delete(entry, tree.getRight()));
			}else if (tree.getLeft() == null){
				return tree.getRight();
			}else if (tree.getRight() == null){
				return tree.getLeft();
			}else{
				tree.setValue(deleteSmaller(tree.getRight()));
				tree.setRight(delete(tree.getValue(), tree.getRight()));
			}
		}
		return balance(tree);
	}
	
	private TreeNode balance(TreeNode tree){
        if( tree == null )
            return tree;
        
        if( height(tree.getLeft()) - height(tree.getRight()) > 1)
            if(height(tree.getLeft().getLeft()) >= height(tree.getLeft().getRight()))
                tree = leftRotation(tree);
            else
                tree = doubleLeftRotation(tree);
        else if(height(tree.getRight()) - height(tree.getLeft()) > 1)
            if(height(tree.getRight().getRight()) >= height(tree.getRight().getLeft()))
                tree = rightRotation(tree);
            else
                tree = doubleRightRotation(tree);

        tree.setHeight(Math.max(height(tree.getLeft()), height(tree.getRight())) + 1);
        return tree;
    }
	
	private ListEntry deleteSmaller (TreeNode tree) {
		if (tree.getLeft() == null){
			return tree.getValue();
		}else{
			return deleteSmaller(tree.getLeft());
		}
	}
	
	private TreeNode doubleLeftRotation(TreeNode tree){
		tree.setLeft(this.rightRotation(tree.getRight()));
		return this.leftRotation(tree);
	}
	
	private void preOrder (TreeNode node){
		if(node == null) return;
		System.out.println("[" + node.getValue().getValue() + ", " + node.getHeight() + "]");
		preOrder(node.getLeft());
		preOrder(node.getRight());	  
	}
	
	private int height(TreeNode tree){
		if (tree == null) return 0;
		return tree.getHeight();
	}

	public int getQuantNode() {
		return quantNode;
	}

	public void setQuantNode(int quantNode) {
		this.quantNode = quantNode;
	}
	
	public void transpose(){
		this.preOrder(this.getRoot());
	}
	
}
