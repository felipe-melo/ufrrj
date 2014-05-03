package arvore.b;

public class Node<T> {
	
	private T valeu;
	private Comparable<T> key;
	private Page<T> page;
	private int height;
	
	public Node(Comparable<T> key, T value){
		this.setKey(key);
		this.setValeu(value);
	}
	
	public T getValeu() {
		return valeu;
	}
	public void setValeu(T valeu) {
		this.valeu = valeu;
	}
	public Comparable<T> getKey() {
		return key;
	}
	public void setKey(Comparable<T> key) {
		this.key = key;
	}
	public Page<T> getPage() {
		return page;
	}
	public void setPage(Page<T> page) {
		this.page = page;
	}
	public boolean keyLowerThan(Comparable otherKey){
		return this.getKey().compareTo((T) otherKey) < 0;
	}
	
	public boolean keyEqualsThan(Comparable otherKey){
		return this.getKey().compareTo((T) otherKey) == 0;
	}
	
	public boolean keyGreaterThan(Comparable otherKey){
		return this.getKey().compareTo((T) otherKey) > 0;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
