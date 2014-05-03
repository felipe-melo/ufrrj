package inter;

public class ListEntry {
	
	protected Object value;
	
	public ListEntry(Object obj){
		this.setValue(obj);
	}
	
	public void setValue(Object obj){
		this.value = obj;
	}
	
	public Object getValue(){
		return this.value;
	}

}
