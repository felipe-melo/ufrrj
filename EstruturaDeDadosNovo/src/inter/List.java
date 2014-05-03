package inter;

public interface List {
	
	public ListEntry createListEntry(Object entr);
	
	public boolean listEmpty();
	
	public boolean listFull();
	
	public void  clearList();
	
	public int listSize();
	
	public void insertList(Object value, int pos);
	
	public Object deleteList(int pos);
	
	public void replaceList(Object value, int pos);

}
