package list.sequencial; 

import inter.List;
import inter.ListEntry;

public class ListaContigua implements List{
	
	private int quant;	
	private ListEntry[] values;
	
	public ListaContigua(int tam){
		this.values = new ListEntry[tam];
		this.quant = 0;
	}

	@Override
	public ListEntry createListEntry(Object entry) {
		return new ListEntry(entry);
	}

	@Override
	public boolean listEmpty() {
		return this.quant == 0;
	}

	@Override
	public boolean listFull() {
		return this.quant == this.values.length;
	}

	@Override
	public void clearList() {
		this.values = new ListEntry[values.length];
		this.quant = 0;
	}

	@Override
	public int listSize() {
		return this.quant;
	}

	@Override
	public void insertList(Object value, int pos) {
		
		ListEntry v1 = this.createListEntry(value);
		
		int temp = -1;
		if ((pos > this.values.length || pos < 0) && this.listEmpty()){
			System.err.println("Indice inválido ou a lista está cheia.");
			return;
		}
		if (this.values[pos] == null){
			this.values[pos] = v1;
			this.quant++;
		}else{
			for (int i = pos+1; i < this.values.length; i++){
				if (this.values[i] == null){
					temp = i;
				}
			}
			
			if (temp > -1){
				for (int i = temp; i > pos; i--){
					this.values[i] = this.values[i-1];
				}
				this.values[pos] = v1;
			}else{
				System.out.println("");
			}
		}
	}

	@Override
	public Object deleteList(int pos) {
		if ((pos > this.values.length || pos < 0)){
			System.err.println("Indice inválido.");
			return null;
		}
		ListEntry temp = this.values[pos];
		for (int i = pos; i < this.values.length-1; i++){
			this.values[i] = this.values[i+1];
		}
		this.values[this.values.length-1] = null;
		this.quant--;
		return temp;
	}

	@Override
	public void replaceList(Object value, int pos) {
		if ((pos > this.values.length || pos < 0)){
			System.err.println("Indice inválido.");
			return;
		}		
		this.values[pos] = (ListEntry) value;
	}
	
	@Override
	public String toString(){
		String temp = "";
		
		for (int i = 0; i < this.values.length; i++){
			if (this.values[i] != null){
				temp += "[" + this.values[i].getValue().toString() + "] ";
			}
		}		
		return temp;
	}
}
