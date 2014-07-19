package arvore.b;

public class Node<K, V> {
	
	private V valeu;
	private Comparable<K> key;
	private Page<K, V> page;
	private int height;
	
	public Node(Comparable<K> key, V value){
		this.setKey(key);
		this.setValeu(value);
	}
	
	public V getValeu() {
		return valeu;
	}
	public void setValeu(V valeu) {
		this.valeu = valeu;
	}
	public Comparable<K> getKey() {
		return key;
	}
	public void setKey(Comparable<K> key) {
		this.key = key;
	}
	public Page<K, V> getPage() {
		return page;
	}
	public void setPage(Page<K, V> page) {
		this.page = page;
	}
	public boolean keyLowerThan(Comparable<K> otherKey){
		return this.getKey().compareTo((K) otherKey) < 0;
	}
	
	public boolean keyEqualsThan(Comparable<K> otherKey){
		return this.getKey().compareTo((K) otherKey) == 0;
	}
	
	public boolean keyGreaterThan(Comparable<K> otherKey){
		return this.getKey().compareTo((K) otherKey) > 0;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
