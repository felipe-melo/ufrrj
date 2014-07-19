package join.hashtable;

public class Node<K, V> {
	
	private Node<K, V> prox = null;
	private Comparable<K> key = null;
	private V value = null;
	
	public Comparable<K> getKey() {
		return key;
	}
	public void setKey(Comparable<K> key) {
		this.key = key;
	}
	public Node<K, V> getProx() {
		return prox;
	}
	public void setProx(Node<K, V> prox) {
		this.prox = prox;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	
	public boolean keyEqualsThan(Comparable<K> otherKey){
		return this.getKey().compareTo((K) otherKey) == 0;
	}
	
}
