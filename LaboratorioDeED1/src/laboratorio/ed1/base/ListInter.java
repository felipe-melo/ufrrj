package laboratorio.ed1.base;

public interface ListInter {
	
	public ListEntry createListEntry(Object value);
	public boolean listEmpty();
	public boolean listFull();
	public void clearList();
	public int listSize();
	public void insertList(Object entry, int position);
	public Object deleteList(int position);
	public void replaceList(Object entry, int position);

}
