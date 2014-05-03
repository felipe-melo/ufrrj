package fila.encadeada;

/**
 * 
 * @author Felipe Melo
 *
 * @param <T> Classe do objeto a ser armaznado na fila
 */
public class No<T> {
	
	private T valor;
	private No<T> prox;
	
	/**
	 * Método construtor do NO da fila
	 * @param v - objeto a ser armazenado 
	 * @param p - referencia para o próximo da fila
	 */
	
	public No(T valor, No<T> prox) {
		this.valor = valor;
		this.prox = prox;
	}
	
	/**
	 * metodo que retorna o objeto armazenado 
	 * @return
	 */
	
	public T getValor() {
		return valor;
	}
	
	/**
	 * metodo que seta o valor do No
	 * @param valor - valor a ser setado
	 */
	public void setValor(T valor) {
		this.valor = valor;
	}
	
	/**
	 * Metodo que retorna o próximo No da Fila
	 * @return - No seguinte
	 */
	public No<T> getProx() {
		return this.prox;
	}
	
	/**
	 * metodo que seta o próximo do No
	 * @param valor - No a ser setado
	 */
	public void setProx(No<T> prox) {
		this.prox= prox;
	}
	
}
