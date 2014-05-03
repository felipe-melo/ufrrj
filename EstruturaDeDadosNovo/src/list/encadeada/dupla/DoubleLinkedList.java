package list.encadeada.dupla;

import inter.DoubleLinkedListEntry;
import inter.List;
import inter.ListEntry;
import list.encadeada.LinkedList;

public class DoubleLinkedList extends LinkedList implements List{
	
	protected DoubleLinkedListEntry head;
	protected DoubleLinkedListEntry end;

	@Override
	public ListEntry createListEntry(Object entr) {
		return new DoubleLinkedListEntry(entr);
	}

	@Override
	public boolean listEmpty() {
		return this.getQuant() == 0;
	}

	@Override
	public boolean listFull() {
		return false;
	}

	@Override
	public void clearList() {
		this.setHead(null);
		this.setEnd(null);
	}

	@Override
	public int listSize() {
		return this.getQuant();
	}

	@Override
	public void insertList(Object value, int pos) {
		
		if (pos < 0 || pos > this.getQuant()){
			System.out.println("Indice inválido.");
			return;
		}
		
		DoubleLinkedListEntry v1 = (DoubleLinkedListEntry) this.createListEntry(value);
		
		if (pos == 0){
			if (this.getQuant() == 0){
				v1.setNext(this.getHead());
				this.setHead(v1);
				this.setEnd(this.getHead());
				this.addQuant();
				return;
			}else if(this.getQuant() == 1){
				v1.setNext(this.getHead());
				this.setHead(v1);
				this.end.setPrev(this.getHead());
				this.addQuant();
				return;
			}
			
			v1.setNext(this.getHead());
			this.head.setPrev(v1);
			this.setHead(v1);
			this.addQuant();
			return;
		}
		
		if (pos == this.getQuant()){
			this.end.setNext(v1);
			v1.setPrev(this.end);
			this.setEnd(v1);
			this.addQuant();
			return;
		}
		
		DoubleLinkedListEntry temp = (DoubleLinkedListEntry) this.SearchValue(pos-1);
		v1.setNext((temp.getNext()));
		((DoubleLinkedListEntry) temp.getNext()).setPrev(v1);
		temp.setNext(v1);
		v1.setPrev(temp);
		this.addQuant();
	}

	@Override
	public Object deleteList(int pos) {
		
		if (!this.BorderLineCheck(pos)) return null;
				
		if (pos == 0){
			if (this.getQuant() == 0){
				DoubleLinkedListEntry temp = this.getHead();
				this.setHead(this.getHead().getNext());
				this.head.setPrev(null);
				this.subQuant();
				return temp.getValue();
			}
		}
		
		if (pos == this.getQuant() - 1){
			DoubleLinkedListEntry temp = this.getEnd();
			this.setEnd(this.end.getPrev());
			this.end.setNext(null);
			this.subQuant();
			return temp.getValue();
		}
		
		DoubleLinkedListEntry temp = (DoubleLinkedListEntry) this.SearchValue(pos-1);
		DoubleLinkedListEntry temp2 = (DoubleLinkedListEntry) temp.getNext();
		((DoubleLinkedListEntry) temp.getNext().getNext()).setPrev(temp);
		temp.setNext(temp.getNext().getNext());
		this.subQuant();
		return temp2.getValue();
	}

	@Override
	public void replaceList(Object value, int pos) {
		if (!this.BorderLineCheck(pos)) return;
		this.SearchValue(pos).setValue(value);
	}

	@Override
	protected DoubleLinkedListEntry SearchValue(int pos) {
		
		DoubleLinkedListEntry a = this.getHead();
		DoubleLinkedListEntry b = this.getEnd();
		
		for (int i = 0; i < this.getQuant(); i++){
			if (i == pos) return a;			
			if ((this.getQuant() - 1 - i) == pos) return b;
			
			a = (DoubleLinkedListEntry) a.getNext();
			b = (DoubleLinkedListEntry) b.getPrev();
		}
		return null;
	}

	public DoubleLinkedListEntry getEnd() {
		return end;
	}

	public void setEnd(DoubleLinkedListEntry end) {
		this.end = end;
	}
	
	public DoubleLinkedListEntry getHead() {
		return this.head;
	}

	public void setHead(DoubleLinkedListEntry head) {
		this.head = head;
	}
	
}
