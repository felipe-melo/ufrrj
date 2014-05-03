package lista.encadeada.circular;

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
	
	public No(T v, No<T> pro) {
		valor = v;
		prox = pro;
	}
	
	/**
	 * metodo que retorna o objeto armazenado 
	 * @return - Valor contido no No
	 */
	
	public T getValor() {
		return valor;
	}
	
	/**
	 * Método que seta um valor no No
	 * @param valor - objecto a ser setado para o No
	 */
	public void setValor(T valor) {
		this.valor = valor;
	}
	
	/**
	 * Método que retorna um ponteiro para o próximo No
	 * @return - ponteiro do próximo No
	 */
	public No<T> getProx() {
		return prox;
	}
	
	/**
	 * Método que seta um ponteiro para o próximo No
	 * @param prox - No a ser setado como próximo
	 */
	public void setProx(No<T> prox) {
		this.prox = prox;
	}	
}
