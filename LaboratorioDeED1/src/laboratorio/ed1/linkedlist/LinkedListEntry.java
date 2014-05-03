package laboratorio.ed1.linkedlist;

import laboratorio.ed1.base.ListEntry;

public class LinkedListEntry extends ListEntry{
	
	private LinkedListEntry prev;
	private LinkedListEntry next;
	
	public LinkedListEntry(Object value){
		super(value);
		this.setValue(value);
	}
	
	public LinkedListEntry getPrev() {
		return prev;
	}
	public void setPrev(LinkedListEntry prev) {
		this.prev = prev;
	}
	public LinkedListEntry getNext() {
		return next;
	}
	public void setNext(LinkedListEntry next) {
		this.next = next;
	}

}
