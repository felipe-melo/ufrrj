package list.encadeada;

import inter.LinkedListEntry;
import inter.List;
import inter.ListEntry;

public class LinkedList implements List{
	
	protected int quant;
	protected LinkedListEntry head;
	
	public LinkedList(){
		this.setQuant(0);
	}

	@Override
	public ListEntry createListEntry(Object entr) {
		return new LinkedListEntry(entr);
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
		
		LinkedListEntry v1 = (LinkedListEntry) this.createListEntry(value);
		
		if (pos == 0){
			v1.setNext(this.getHead());
			this.setHead(v1);
			this.addQuant();
			return;
		}
		
		if (pos == this.getQuant()){
			this.SearchValue(pos-1).setNext(v1);
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
		
		if (!this.BorderLineCheck(pos)) return null;
		
		if (pos == 0){
			LinkedListEntry temp = this.getHead();
			this.setHead((this.getHead().getNext()));
			this.subQuant();
			return temp;
		}
		
		LinkedListEntry temp = this.SearchValue(pos-1);
		LinkedListEntry aux = temp.getNext();
		temp.setNext(temp.getNext().getNext());
		this.subQuant();
		return aux;
	}

	@Override
	public void replaceList(Object value, int pos) {
		if (!BorderLineCheck(pos)) return;
		
		this.SearchValue(pos).setValue(value);
	}
	
	protected LinkedListEntry SearchValue(int pos){
		int i = 0;
		for (LinkedListEntry aux = this.getHead(); aux != null; aux = aux.getNext()){
			if (i == pos) return aux;
			i++;
		}
		return null;
	}
	
	protected boolean BorderLineCheck (int pos){
		
		if (pos < 0 || pos > this.getQuant()){
			System.out.println("Indice inválido.");
			return false;
		}
		
		if (this.getHead() == null){
			System.out.println("A lista está vazia.");
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString(){
		String temp = "";
		
		for (LinkedListEntry v = this.getHead(); v != null; v = v.getNext()){
			if (v != null)
				temp += "[" + v.getValue().toString() + "] ";
		}		
		return temp;
	}

	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}
	
	protected void addQuant(){
		this.setQuant(this.getQuant() + 1);
	}
	
	protected void subQuant(){
		this.setQuant(this.getQuant() - 1);
	}

	public LinkedListEntry getHead() {
		return head;
	}

	public void setHead(LinkedListEntry head) {
		this.head = head;
	}

}
