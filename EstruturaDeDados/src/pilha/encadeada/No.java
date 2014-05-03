package pilha.encadeada;

/**
 * 
 * @author Felipe Melo
 *
 * @param <T> Classe do objeto a ser armaznado na pilha
 */
public class No<T> {
	
	private T valor;
	private No<T> prox;
	
	/**
	 * Método construtor do NOH da pilha
	 * @param v - objeto a ser armazenado 
	 * @param p - referencia para o anterior da pilha
	 */
	
	public No(T v, No<T> p) {
		valor = v;
		prox = p;
	}
	
	/**
	 * Método que retorna o objeto armazenado 
	 * @return - Objecto do No 
	 */
	
	public T getValor() {
		return valor;
	}
	
	/**
	 * Método que seta o objeto no No 
	 * @param valor - Objecto a ser setado no No 
	 */
	public void setValor(T valor) {
		this.valor = valor;
	}
	
	/**
	 * Método que retorna o próximo No 
	 * @return - Próximo No
	 */
	public No<T> getProx() {
		return prox;
	}
	
	/**
	 * Método que seta o próximo No 
	 * @param prox - Próximo No da pilha
	 */
	public void setProx(No<T> prox) {
		this.prox = prox;
	}
	
}
