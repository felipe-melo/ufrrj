package fila.sequencial;

/**
 * Classe que armezena uma Fila vetorial
 * @author Felipe Melo
 *
 * @param <T> - Tipo do objecto que a classe armazenará
 */

public class Fila<T> {
	
	private T[] fila;
	private int tam;
	
	/**
	 * Método contrutor da Fila
	 * @param N - Número máximo de objectos que a Fila terá
	 */
	
	public Fila(int N){
		this.fila = (T[]) new Object[N];
		this.tam = 0;
	}
	
	/**
	 * Método que verifica se a Fila está vazia
	 * @return - true se a Fila estiver vazia e false caso contrário
	 */
	
	public boolean isEmpty(){
		return this.tam == 0;
	}
	
	/**
	 * Método que insere um elemento na Fila
	 * @param valor - Valor a ser inserido na Fila
	 */
	
	public void enqueue(T valor){
		
		if (this.tam == this.fila.length){
			System.err.println("A lista está lotada.");
			return;
		}else{
			if (!this.isEmpty()){
				for (int i = tam; i > 0; i--){
					this.fila[i] = this.fila[i-1];
				}
				this.fila[0] = valor;
				this.tam++;
			}else{
				this.fila[0] = valor;
				this.tam++;
			}
		}
	}
	
	/**
	 * Método que remove um primeiro elemento da Fila
	 * @return - O objecto que está sendo removido
	 */
	
	public T dequeue(){
		if (this.isEmpty()){
			System.err.println("A lista está vazia.");
			return null;
		}else{
			return this.fila[--tam];
		}
	}
	
	/**
	 * Método que retorna o primeiro elemento da Fila
	 * @return - Primeiro elemento da Fila
	 */
	
	public T top(){
		if (this.isEmpty()){
			System.err.println("A lista está vazia.");
			return null;
		}else{
			return this.fila[tam-1];
		}
	}
	
	@Override
	public String toString() {

		String temp = "";
		
		if(this.fila == null) return null;
		
		for (int i = 0; i < this.tam; i++){
			temp = temp + "[" + this.fila[i].toString() + "]";
		}
		return temp;
	}

}
