package laboratorio.ed1.linkedlist;

import laboratorio.ed1.base.ListEntry;
import laboratorio.ed1.base.ListInter;

public class LinkedList implements ListInter{
	
	private int size;
	private LinkedListEntry head;
	
	public LinkedList(){
		this.setSize(0);
		this.setHead(new LinkedListEntry(null));
	}

	@Override
	public ListEntry createListEntry(Object value) {
		return new LinkedListEntry(value);
	}

	@Override
	public boolean listEmpty() {
		return this.getSize() == 0;
	}

	@Override
	public boolean listFull() {
		return false;
	}

	@Override
	public void clearList() {
		this.setHead(new LinkedListEntry(null));
		this.setSize(0);
	}

	@Override
	public int listSize() {
		return this.getSize();
	}

	@Override
	public void insertList(Object entry, int position) {
		
		if (position < 0 || position > this.getSize()){
			System.err.println("Posição inválida.");
			return;
		}
		
		LinkedListEntry element = (LinkedListEntry) this.createListEntry(entry);
		
		if (position == 0){
			if (this.getSize() == 0){
				this.setHead(element);
				this.addSize();
				return;
			}else{
				element.setNext(this.getHead());
				this.setHead(element);
				this.addSize();
				return;
			}

		}
		
		LinkedListEntry aux = getPosition(position - 1);
		element.setPrev(aux);
		element.setNext(aux.getNext());
		
		if (position < this.getSize())
			aux.getNext().setPrev(element);
		
		aux.setNext(element);
		this.addSize();
		return;
	}
	
	private void addSize(){
		this.setSize(this.getSize() + 1);
	}
	
	private void subSize(){
		this.setSize(this.getSize() - 1);
	}

	@Override
	public Object deleteList(int position) {
		
		if (position < 0 || position >= this.getSize()){
			System.err.println("Posição inválida.");
			return null;
		}
		
		if (position == 0){
			if (this.listEmpty()){
				System.out.println("A lista está vazia.");
				return null;
			}else{
				this.subSize();
				LinkedListEntry aux = this.getHead();
				this.setHead(this.getHead().getNext());
				this.getHead().setPrev(null);
				return aux.getValue();
			}

		}
		
		LinkedListEntry aux = getPosition(position - 1);
		LinkedListEntry aux2 = aux.getNext();
		
		if (position < this.getSize() - 1){
			aux.setNext(aux.getNext().getNext());
			aux.getNext().getNext().setPrev(aux);
		}else if (position < this.getSize()){
			aux.setNext(aux.getNext().getNext());
		}	
		this.subSize();
		
		return aux2.getValue();
	}

	@Override
	public void replaceList(Object entry, int position) {
		LinkedListEntry aux = this.getPosition(position); 
		if (aux != null){
			aux.setValue(entry);
			return;
		}
		System.err.println("Posisão inválida.");
	}
	
	public LinkedListEntry getPosition(int position){
		
		if (position < 0 || position > this.getSize()){
			return null;
		}
		
		int i = 0;
		
		for (LinkedListEntry aux = this.getHead(); aux != null; aux = aux.getNext()){
			if (position == i){
				return aux;
			}
			i++;
		}
		
		return null;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public LinkedListEntry getHead() {
		return head;
	}

	public void setHead(LinkedListEntry head) {
		this.head = head;
	}

	@Override
	public String toString() {
		
		String out = "[";
		
		for (LinkedListEntry e = this.getHead(); e != null; e = e.getNext()){
			if (e.getNext() == null)
				out += e.getValue();
			else
				out += e.getValue() + ", ";
		}
		
		out += "]";
		return out;
	}

}
