package inter;

public class LinkedListEntry extends ListEntry{
	
	private LinkedListEntry next;

	public LinkedListEntry(Object obj) {
		super(obj);
	}

	public LinkedListEntry getNext() {
		return next;
	}

	public void setNext(LinkedListEntry next) {
		this.next = next;
	}
	
	
}
