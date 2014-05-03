package fila.encadeada;

/**
 * 
 * @author Felipe Melo
 *
 * @param <T>
 */

public class Fila<T> {
	
	private No<T> first;
	private No<T> last;
	private int tam;
	
	/**
	 * Método construtor da Fila
	 */
	
	public Fila(){
		this.tam = 0;
	}
	
	/**
	 * Método de insersão de objecto na Fila
	 * @param valor - Valor a ser inserido na Fila
	 */
	
	public void enqueue(T valor){
		
		if (this.isEmpty()){
			No<T> aux = new No<T>(valor, null);
			first = new No<T>(null, aux);
			last = first;
			this.tam++;
			return;
		}
		
		if (this.tam == 1){
			No<T> aux = new No<T>(valor, null);
			this.last = new No<T> (null, aux);
			first.getProx().setProx(last.getProx());
			this.tam++;
			return;
		}
		
		No<T> aux = new No<T>(valor, null);
		this.last.getProx().setProx(aux);
		this.last.setProx(aux);
		this.tam++;
	}
	
	/**
	 * Método que remove o primeiro elemento da Fila
	 * @return - Retorna o elemento que está sendo removido
	 */
	
	public T dequeue(){
		if (!this.isEmpty()){
			No<T> aux = this.first.getProx();
			this.first.setProx(this.first.getProx().getProx());
			this.tam--;
			return aux.getValor();
		}else{
			System.out.println("A fila está vazia.");
			return null;
		}
	}
	
	/**
	 * Método que retorna o primeiro elemento da Fila
	 * @return - Primeiro elemento da Fila
	 */
	
	public T first(){
		if (!this.isEmpty()){
			return this.first.getProx().getValor();
		}else{
			System.out.println("A fila está vazia.");
			return null;
		}
	}
	
	/**
	 * Método para verificar se a Fila está vazia
	 * @return - true se a Fila estiver vazia e false caso contrário
	 */
	
	public boolean isEmpty(){
		return this.first == null;
	}
	
	@Override
	public String toString() {

		String temp = "";
		
		if(this.isEmpty()) return null;
		
		No<T> f = first.getProx();
		
		do{
			temp = temp + "[" + f.getValor().toString() + "]";
			
			f = f.getProx();
				
		}while(f != null);
		
		return temp;
	}

}
