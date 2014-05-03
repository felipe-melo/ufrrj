package lista.encadeada.dupla;

public class ListaDupla<T> {
	
	private No<T> cabeca;
	private No<T> calda;
	private int tam = 0;
	
	public void inserir(T valor, int p){
		
		if (p > this.tam || p < 0){
			System.err.println("Indice fora do range.");
			return;
		}
		
		if (p == 0){
			if (this.cabeca == null){
				this.cabeca = new No<T>(valor, null, null);
				this.calda = this.cabeca;
				this.tam++;
				return;
			}else if (tam == 1){
				this.calda = new No<T>(this.cabeca.getValor(), null, this.cabeca);
				this.cabeca.setValor(valor);
				this.cabeca.setProx(calda);
				this.tam++;
				return;
			}else{
				No<T> aux = new No<T>(valor, this.cabeca, null);
				this.cabeca.setPrev(aux);
				this.cabeca = aux;
				this.tam++;
			}
		}
		
		if (p == this.tam){
			if (this.tam == 1){
				this.calda = new No<T>(valor, null, this.cabeca);
				this.cabeca.setProx(calda);
				this.tam++;
				return;
			}else{
				No<T> aux = new No<T>(valor, null, this.calda);
				this.calda.setProx(aux);
				this.calda = aux;
				this.tam++;
				return;
			}
		}
		
		No<T> obj1 = cabeca.getProx();
		No<T> obj2 = calda;
		
		for (int i = 1; i < this.tam; i++){
			if (i == p){
				No<T> aux = new No<T>(valor, obj1, obj1.getPrev());
				obj1.getPrev().setProx(aux);
				obj1.setPrev(aux);
				this.tam++;
				return;
			}
			
			if (this.tam - i == p){
				No<T> aux = new No<T>(valor, obj2, obj2.getPrev());
				obj2.getPrev().setProx(aux);
				obj2.setPrev(aux);
				this.tam++;
				return;
			}
			
			obj1 = obj1.getProx();
			obj2 = obj2.getPrev();
		}
	}
	
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
			this.cabeca.setPrev(null);
			this.tam--;
			return;
		}
		
		if (p == this.tam-1){
			this.calda = this.calda.getPrev();
			this.calda.setProx(null);
			this.tam--;
			return;
		}
		
		No<T> obj1 = cabeca.getProx();
		No<T> obj2 = calda.getPrev();
			
		for (int i = 1; i < tam-2; i++){
			if(i == p){
				obj1.getPrev().setProx(obj1.getProx());
				obj1.getProx().setPrev(obj1.getPrev());
				this.tam--;
				return;
			}
			
			if(this.tam - 1 - i == p){
				obj2.getPrev().setProx(obj2.getProx());
				obj2.getProx().setPrev(obj2.getPrev());
				this.tam--;
				return;
			}
			obj1 = obj1.getProx();
			obj2 = obj2.getPrev();
		}	
	}
	
	public No<T> Buscar(int p){
		
		if (this.cabeca == null){
			System.err.println("A lista está vazia.");
			return null;
		}
		
		if (this.tam <= p || p < 0){
			System.err.println("\nposição inválida.");
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
	
	public void Atualizar(T valor, int p){
		
		if (this.tam == 0){
			System.err.println("A lista está vazia.");
			return;
		}
		
		if (p >= this.tam || p < 0){
			System.err.println("Posição inválida.");
			return;
		}
		
		No<T> obj;
		try {
			obj = this.Buscar(p);
			obj.setValor(valor);
		} catch (Exception e) {
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
