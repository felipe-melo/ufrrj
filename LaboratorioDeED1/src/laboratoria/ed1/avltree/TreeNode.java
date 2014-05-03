package laboratoria.ed1.avltree;

public class TreeNode {
	
	private ListEntry value;
	private TreeNode left;
	private TreeNode right;
	private int height;
	
	public ListEntry getValue() {
		return value;
	}
	public void setValue(ListEntry value) {
		this.value = value;
	}
	public TreeNode getLeft() {
		return left;
	}
	public void setLeft(TreeNode left) {
		this.left = left;
	}
	public TreeNode getRight() {
		return right;
	}
	public void setRight(TreeNode right) {
		this.right = right;
	}
	public void setHeight(int h){
		this.height = h;
	}
	public int getHeight(){
		return height;
	}

}
