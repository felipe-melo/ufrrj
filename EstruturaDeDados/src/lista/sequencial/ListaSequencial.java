package lista.sequencial;

public class ListaSequencial<T> {
	
	private T[] valores;
	private int quant;
	
	public ListaSequencial(int max){
		this.valores = (T[]) new Object[max];
		this.quant = 0;
	}

	public int getQuant() {
		return this.quant;
	}
	
	public T getValor(int p){
		if (p >= 0 && p <= this.getQuant())
			return this.valores[p];
		System.err.println("Indice de busca inválido.");
		return null;
	}	
	
	public void inserir(T valor, int p){
		
		if (p < 0 || p > this.getQuant()){
			System.err.println("Posição inválida.");
			return;
		}
		if (this.getQuant() > valores.length-1){
			System.err.println("A lista está cheia.");
			return;
		}
		for (int i = this.quant; i > p; i--) {
			this.valores[i] = this.valores[i-1];
		}
		this.quant++;
		this.valores[p] = valor;
	}
	
	public void apagar(int p){
		
		if (p < 0 && p >= this.quant){
			System.err.println("Posição inválida.");
			return;
		}
		
		for (int i = p; i < this.quant-1; i++) {
			this.valores[i] = this.valores[i+1];
		}
		
		this.quant--;
	}
	
	@Override
	public String toString() {

		String temp = "";
		
		if(this.valores == null) return null;
		
		for (int i = 0; i < this.quant; i++){
			temp = temp + "[" + this.valores[i].toString() + "]";
		}
		return temp;
	}
	
}
