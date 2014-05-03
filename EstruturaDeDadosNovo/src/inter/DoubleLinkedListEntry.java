package inter;

public class DoubleLinkedListEntry extends LinkedListEntry{
	
	protected DoubleLinkedListEntry prev;

	public DoubleLinkedListEntry(Object obj) {
		super(obj);
		// TODO Auto-generated constructor stub
	}

	public DoubleLinkedListEntry getPrev() {
		return prev;
	}

	public void setPrev(DoubleLinkedListEntry prev) {
		this.prev = prev;
	}

}
