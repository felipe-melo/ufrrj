package pilha.encadeada;

public class Pilha<T> {
	
	private No<T> topo;
	
	public void push(T valor){
		
		if (this.isEmpty()){
			topo = new No<T>(valor, null);
			return;
		}
		
		No<T> aux = new No<T>(valor, topo);
		this.topo = aux;
	}
	
	public boolean isEmpty(){
		return this.topo == null;
	}
	
	public T pop(){
		if (!this.isEmpty()){
			No<T> temp = topo;
			topo = topo.getProx();
			return temp.getValor();
		}else{
			System.out.println("A Pilha está vazia.");
			return null;
		}
	}
	
	public T top(){
		return topo.getValor();
	}
	
	@Override
	public String toString() {

		String temp = "";
		
		if(this.isEmpty()) return null;
		
		No<T> f = this.topo;
		
		do{
			temp = temp + "[" + f.getValor().toString() + "]";
			
			f = f.getProx();
				
		}while(f != null);
		
		return temp;
	}

}
