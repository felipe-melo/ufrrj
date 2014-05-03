package pilha.sequencial;


public class Pilha<T> {
	
	private T[] last;
	private int tam;
	
	public Pilha(int N){
		this.last = (T[]) new Object[N];
		this.tam = 0;
	}
	
	public boolean isEmpty(){
		return this.tam == 0;
	}
	
	public void push(T valor){
		
		if (this.tam == this.last.length){
			System.err.println("A Pilha está lotada.");
			return;
		}else{
			last[tam++] = valor;
		}
	}
	
	public T pop(){
		if (this.isEmpty()){
			System.err.println("A Pilha está vazia.");
			return null;
		}else{
			return this.last[--tam];
		}
	}
	
	public T top(){
		if (this.isEmpty()){
			System.err.println("A Pilha está vazia.");
			return null;
		}else{
			return this.last[tam-1];
		}
	}
	
	@Override
	public String toString() {

		String temp = "";
		
		if(this.last == null) return null;
		
		for (int i = this.tam-1; i >= 0; i--){
			temp = temp + "[" + this.last[i].toString() + "]";
		}
		return temp;
	}

}
