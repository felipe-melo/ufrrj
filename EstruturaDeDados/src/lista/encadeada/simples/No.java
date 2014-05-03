package lista.encadeada.simples;

/**
 * 
 * @author Felipe Melo
 *
 * @param <T> Classe do objeto a ser armaznado na lista
 */
public class No<T> {
	
	private T valor;
	private No<T> prox;
	
	/**
	 * Método construtor do NOH da lista
	 * @param v - objeto a ser armazenado 
	 * @param p - referencia para o próximo da lista
	 */
	
	public No(T v, No<T> p) {
		valor = v;
		prox = p;
	}
	
	/**
	 * metodo que retorna o objeto armazenado 
	 * @return
	 */
	
	public T getValor() {
		return valor;
	}
	
	/**
	 * Método que seta o valor em um No
	 * @param valor -  Valor a ser setado no No
	 */
	public void setValor(T valor) {
		this.valor = valor;
	}
	
	/**
	 * Método que retorna o próximo No
	 * @return - O objecto seguinto da lista
	 */
	public No<T> getProx() {
		return prox;
	}
	
	/**
	 * Método que seta o próximo o próximo No da lista
	 * @param prox - Próximo No da ser setado
	 */
	public void setProx(No<T> prox) {
		this.prox = prox;
	}
	
}
