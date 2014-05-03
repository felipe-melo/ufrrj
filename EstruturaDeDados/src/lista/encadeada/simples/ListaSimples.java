package lista.encadeada.simples;

import classe.camilla.Comparable;

/**
 * Classe que armazena uma lista encadeada simpples de objectos genéricos
 * @author Felipe Melo
 *
 * @param <T> - Tipo do objecto a ser armazenado
 */

public class ListaSimples<T>{
	
	private No<T> cabeca;
	private int tam;
	
	/**
	 * Método que insere um elemento na lista em uma determinada posição
	 * @param valor - Objecto a ser inserido na lista
	 * @param p - posição em que o objecto será inserido
	 */
	
	public void Inserir(T valor, int p){
		
		if (p < 0 || p > tam){
			System.out.println("Indice fora do range.\n");
			return;
		}
		
		if (cabeca == null){
			cabeca = new No<T>(valor, null);
			this.tam++;
			return;
		}
		
		No<T> aux = cabeca;
		int i = 0;
		
		if (p == 0){
			cabeca = new No<T>(valor, aux);
			this.tam++;
			return;
		}
		
		do{
			i++;
			if (i == p){
				No<T> n = new No<T>(valor, aux.getProx());
				aux.setProx(n);
				this.tam++;
				return;
			}
			aux = aux.getProx();
		}while(aux != null && i < p);
	}
	
	/**
	 * Método que apaga um elemento na lista em uma determinada posição
	 * @param p - posição em que o objecto será apagado
	 */
	
	public void Apagar(int p){
		
		if (cabeca == null){
			System.err.println("A lista está vazia.");
		}
		
		if (p == 0){
			cabeca = cabeca.getProx();
			this.tam--;
			return;
		}
		
		int i = 0;
		for (No<T> el = cabeca; el.getProx() != null; el = el.getProx()){
			if (i == (p - 1)){
				el.setProx(el.getProx().getProx());
				this.tam--;
				return;
			}
			i++;
		}
		System.err.println("Indice inválido.");
	}
	
	/**
	 * Método que busca um elemento na lista em uma determinada posição
	 * @param p - posição em que o objecto será buscado
	 */
	
	public T Buscar(int p){
		
		int i = 0;
		for (No<T> el = cabeca; el.getProx() != null; el = el.getProx()){
			if (i == p){
				return el.getValor();
			}
			i++;
		}
		return null;
	}
	
	/**
	 * Método que insere um elemento na lista respeitando a ordem.
	 * @param valor - Objecto a ser inserido na lista
	 * @param p - possição em que o objecto será inserido
	 */
	
	public void Inserir(Comparable<T> valor){
		
		int result;
		
		if (cabeca == null){
			cabeca = new No<T>((T) valor, null);
			this.tam++;
		}else{
			No<T> p = cabeca;
			No<T> aux;
			
			result = ((Comparable) valor).Comparavel(cabeca.getValor());
			
			if (result >= 0){
				cabeca = new No<T>((T) valor, p);
				return;
			}else{
				aux = new No<T>((T) valor, p.getProx());				
			}
		
			while(p.getProx() != null){
				
				result = ((Comparable) valor).Comparavel(p.getProx().getValor());
				
				if (result >= 0){
					aux = new No<T>((T) valor, p.getProx());
					p.setProx(aux);
					return;
				}
				
				p = p.getProx();
				
			}
		
			p.setProx(new No<T>((T) valor, null));
		}
	}

	@Override
	public String toString() {

		String temp = "";
		
		if(cabeca == null) return null;
		
		No<T> p = cabeca;		
		
		do{
			temp = temp + "[" + p.getValor().toString() + "]";
			
			p = p.getProx();
				
		}while(p != null);
		
		return temp;
	}
}