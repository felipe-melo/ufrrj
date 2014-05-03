package lista.encadeada.circular;

/**
 * Classe para armazenar uma Lista encadeada Circular
 * @author Felipe Melo
 *
 * @param <T> - Tipo do objecto a ser armazenado pela Lista
 */

public class ListaCircular<T> {
	
	private No<T> cabeca;
	private No<T> calda;
	private int tam = 0;
	
	/**
	 * Método que insere um objecto na Lista
	 * @param valor - Objecto a ser inserido na Lista
	 */
	
	public void inserir(T valor){
		
		if (this.cabeca == null){
			this.cabeca = new No<T>(valor, this.cabeca);
			this.calda = this.cabeca;
			this.tam++;
			return;
		}
		
		if (tam == 1){
			this.calda = new No<T>(valor, this.cabeca);
			this.cabeca.setProx(this.calda);
			this.tam++;
			return;
		}
		
		No<T> aux = new No<T>(valor, this.cabeca);
		this.calda.setProx(aux);
		this.calda = aux;
		this.tam++;
		return;
	}
	
	/**
	 * Método que insere um Objecto em uma posição especifica da Lista
	 * @param valor - Objecto a ser inserido na Lista
	 * @param p - posição que o objecto deve ser inserido
	 */
	
	public void inserir(T valor, int p){
		
		if (p > this.tam || p < 0){
			System.err.println("Indice fora do range.");
			return;
		}
		
		if (p == 0){
			if (this.cabeca == null){
				this.cabeca = new No<T>(valor, this.cabeca);
				this.calda = this.cabeca;
				this.tam++;
				return;
			}else if (tam == 1){
				this.calda = new No<T>(this.cabeca.getValor(), this.cabeca);
				this.cabeca.setValor(valor);
				this.cabeca.setProx(calda);
				this.tam++;
				return;
			}else{
				No<T> aux = new No<T>(this.cabeca.getValor(), this.cabeca.getProx());
				this.cabeca.setValor(valor);
				this.cabeca.setProx(aux);
				this.tam++;
				return;
			}
		}
		
		if (p == this.tam){
			if (this.tam == 1){
				this.calda = new No<T>(valor, this.cabeca);
				this.cabeca.setProx(calda);
				this.tam++;
				return;
			}else{
				No<T> aux = new No<T>(valor, this.cabeca);
				this.calda.setProx(aux);
				this.calda = aux;
				this.tam++;
				return;
			}
		}
		
		No<T> obj = this.cabeca;
		int i = 0;
		
		do{
			if (i + 1 == p){
				No<T> aux = new No<T>(valor, obj.getProx());
				obj.setProx(aux);
				this.tam++;
				return;
			}
			
			obj = obj.getProx();
			i++;
		}while (obj.getProx() != this.cabeca);
	}
	
	/**
	 * Método que apaga um objecto da Lista dada uma posição
	 * @param p - posição do objecto a ser apagado
	 */
	
	public void apagar(int p){
		
		if(cabeca == null){
			System.err.println("A lista está vazia.");
			return;
		}else if(p == tam || p < 0){
			System.err.println("indice fora do range.");
			return;
		}
		
		if (p == 0){
			this.cabeca = this.cabeca.getProx();
			this.calda.setProx(this.cabeca);
			this.tam--;
			return;
		}
		
		if (p == this.tam-1){
			this.Buscar(p-1).setProx(this.cabeca);
			this.tam--;
			return;
		}
		
		No<T> obj = this.cabeca;
		int i = 0;
		
		do{
			if(i + 1 == p){
				obj.setProx(obj.getProx().getProx());
				this.tam--;
				return;
			}
			obj = obj.getProx();
			i++;
		}while (obj.getProx() != this.cabeca);
	}
	
	/**
	 * Método que busca um objecto em uma dada posição na Lista
	 * @param p - Posição a ser buscada
	 * @return - objecto buscado
	 */
	
	public No<T> Buscar(int p){
		
		if (this.cabeca == null){
			System.err.println("A lista está vazia");
			return null;
		}
		
		if (this.tam <= p || p < 0){
			System.err.println("posição inválida.");
			return null;
		}
		
		No<T> obj = this.cabeca;
		
		for (int i = 0; i < this.tam; i++){
			if (p == i){
				return obj;
			}
			obj = obj.getProx();
		}
		return null;
	}
	
	/**
	 * Método que atualiza o valor de um No em uma dada posição
	 * @param p - Posição a ser atualizada
	 * @param valor - objecto a ser adicionado 
	 */
	
	public void Atualizar(T valor, int p){
		
		if (this.tam == 0){
			System.err.println("A lista está vazia.");
			return;
		}
		
		if (p >= this.tam || p < 0){
			System.err.println("Posição inválida.");
			return;
		}
		
		No<T> obj = this.Buscar(p);
		obj.setValor(valor);
	}
	
	@Override
	public String toString() {

		String temp = "";
		
		if(cabeca == null) return null;
		
		No<T> p = cabeca;		
		
		do{
			temp = temp + "[" + p.getValor().toString() + "]";
			
			p = p.getProx();
				
		}while(p != this.cabeca);
		
		return temp;
	}
}
