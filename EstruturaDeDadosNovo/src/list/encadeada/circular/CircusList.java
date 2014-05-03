package list.encadeada.circular;

import inter.LinkedListEntry;
import inter.ListEntry;
import list.encadeada.LinkedList;

public class CircusList extends LinkedList{
	
	private LinkedListEntry end;
		
	public LinkedListEntry getEnd() {
		return end;
	}

	public void setEnd(LinkedListEntry end) {
		this.end = end;
	}

	@Override
	public ListEntry createListEntry(Object entr) {
		return new LinkedListEntry(entr);
	}

	@Override
	public void insertList(Object value, int pos) {
		
		if (pos < 0 || pos > this.getQuant()){
			System.out.println("Indice inválido.");
			return;
		}
		
		LinkedListEntry v1 = (LinkedListEntry) this.createListEntry(value);
		
		if (pos == 0){
			if (this.getQuant() == 0){
				v1.setNext(this.getHead());
				this.setHead(v1);
				this.head.setNext(this.getHead());
				this.setEnd(this.getHead());
				this.addQuant();
				return;
			}else if(this.getQuant() == 1){
				v1.setNext(this.getHead());
				this.setHead(v1);
				this.end.setNext(this.getHead());
				this.addQuant();
				return;
			}
			
			v1.setNext(this.getHead());
			this.setHead(v1);
			this.end.setNext(this.getHead());
			this.addQuant();
			return;
		}
		
		if (pos == this.getQuant()){
			v1.setNext(this.getHead());
			this.end.setNext(v1);
			this.setEnd(v1);
			this.addQuant();
			return;
		}
		
		LinkedListEntry temp = this.SearchValue(pos-1);
		v1.setNext(temp.getNext());
		temp.setNext(v1);
		this.addQuant();
	}

	@Override
	public Object deleteList(int pos) {
		// TODO Auto-generated method stub
		return super.deleteList(pos);
	}

	@Override
	public void replaceList(Object value, int pos) {
		// TODO Auto-generated method stub
		super.replaceList(value, pos);
	}
}
