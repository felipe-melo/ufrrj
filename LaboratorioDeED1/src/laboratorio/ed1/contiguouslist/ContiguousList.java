package laboratorio.ed1.contiguouslist;

import laboratorio.ed1.base.ListEntry;
import laboratorio.ed1.base.ListInter;

public class ContiguousList implements ListInter{
	
	private ListEntry[] listEntryVet;
	private int quantElements;
	private int max;
	
	public ContiguousList(int tam){
		this.setListEntryVet(new ListEntry[tam]);
		this.setQuantElements(0);
	}

	@Override
	public ListEntry createListEntry(Object value) {
		return new ListEntry(value);
	}

	@Override
	public boolean listEmpty() {
		return this.getQuantElements() == 0;
	}

	@Override
	public boolean listFull() {
		return this.getQuantElements() == this.getListEntryVet().length;
	}

	@Override
	public void clearList() {
		this.setListEntryVet(new ListEntry[this.getListEntryVet().length]);
	}

	@Override
	public int listSize() {
		return this.getQuantElements();
	}

	@Override
	public void insertList(Object entry, int position) {
		
		if (position < 0 || position >= this.getListEntryVet().length){
			System.err.println("Posição inválida.");
			return;
		}
		
		ListEntry element = this.createListEntry(entry);
		
		if (!this.listFull()){
			if (position > this.getMax()) this.setMax(position);
			if (this.getListEntryVet()[position] == null){
				this.getListEntryVet()[position] = element;
				this.addQuantElements();
				return;
			}else{
				int i = position;
				
				while (true){
					i++;
					if (this.getListEntryVet()[i] == null){
						this.rotateList(position, i);
						this.getListEntryVet()[position] = element;
						this.addQuantElements();
						return;
					}else if (i == this.getListEntryVet().length - 1){
						i = -1;
					}
				}
			}
		}else{
			System.out.println("A lista está cheia.");
		}
	}
	
	private void addQuantElements(){
		this.setQuantElements(this.getQuantElements() + 1);
	}
	
	private void subQuantElements(){
		this.setQuantElements(this.getQuantElements() - 1);
	}
	
	private void rotateList(int pos1, int pos2){
		if (pos2 > pos1){
			for (int i = pos2; i > pos1; i--){
				this.getListEntryVet()[i] = this.getListEntryVet()[i-1];
			}
		}else{
			while (pos1 != pos2){
				if (pos2 == 0){
					this.getListEntryVet()[pos2] = this.getListEntryVet()[this.getListEntryVet().length-1];
					pos2 = this.getListEntryVet().length-1;
				}else{
					this.getListEntryVet()[pos2] = this.getListEntryVet()[pos2-1];
					pos2--;
				}
			}
		}
	}

	@Override
	public Object deleteList(int position) {
		
		if (this.listEmpty()){
			System.out.println("A lista está vazia.");
			return null;
		}else{
			this.subQuantElements();
			ListEntry aux = this.getListEntryVet()[position];
			this.getListEntryVet()[position] = null;
			return aux;
		}
	}

	@Override
	public void replaceList(Object entry, int position) {
		ListEntry element = this.createListEntry(entry);
		this.getListEntryVet()[position] = element;
	}

	public ListEntry[] getListEntryVet() {
		return listEntryVet;
	}

	public void setListEntryVet(ListEntry[] listEntryVet) {
		this.listEntryVet = listEntryVet;
	}

	public int getQuantElements() {
		return quantElements;
	}

	public void setQuantElements(int quantElements) {
		this.quantElements = quantElements;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	@Override
	public String toString() {
		
		String out = "[";
		
		for (int i = 0; i < this.getListEntryVet().length; i++){
			if (this.getListEntryVet()[i] != null){
				if (i != this.getMax())
					out += this.getListEntryVet()[i].getValue() + ", ";
				else
					out += this.getListEntryVet()[i].getValue();
			}
		}
		
		out += "]";
		return out;
	}
}
