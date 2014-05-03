package laboratoria.ed1.avltree;

public class ListEntry {
	
	private Object value;
	private Comparable key;
	
	public ListEntry(Object value, Comparable key){
		this.setValue(value);
		this.setKey(key);
	}
	
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public Comparable getKey() {
		return key;
	}
	public void setKey(Comparable key) {
		this.key = key;
	}
	
	public boolean keyLowerThan(Comparable otherKey){
		return this.getKey().compareTo(otherKey) < 0;
	}
	
	public boolean keyEqualsThan(Comparable otherKey){
		return this.getKey().compareTo(otherKey) == 0;
	}
	
	public boolean keyGreaterThan(Comparable otherKey){
		return this.getKey().compareTo(otherKey) > 0;
	}

}
