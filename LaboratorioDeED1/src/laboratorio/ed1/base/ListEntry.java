package laboratorio.ed1.base;

public class ListEntry implements Comparable{
	
	private Object value;
	private Object key;
	
	public ListEntry(Object value){
		this.setValue(value);
	}
	
	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Object getKey() {
		return key;
	}

	public void setKey(Object key) {
		this.key = key;
	}

}
