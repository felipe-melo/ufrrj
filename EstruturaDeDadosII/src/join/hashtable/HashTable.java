package join.hashtable;

public class HashTable<K, E>{
	
	protected Node<K, E>[] array;
	private int tam;
	
	public HashTable(int tam){
		this.tam = tam;
		this.array = new Node[tam];	
	}

	public void insert(Comparable<K> key, E value ){
		//falta definir o index
		int index = key.hashCode();
		if (index < 0)
			index = -index % tam;
		else
			index = index % tam;
		
		Node<K, E> aux = array[index];
		Node<K, E> newNode = new Node<K, E>();
		newNode.setKey(key);
		newNode.setValue(value);

		if(array[index]==null){
			array[index] = newNode;
		}else{
			while(aux.getProx()!=null){
				aux = aux.getProx();
			}
			aux.setProx(newNode);
		}
	}
	
	public Node<K, E> find(Comparable<E> key){
		int index = key.hashCode();
		if (index < 0)
			index = -index % tam;
		else
			index = index % tam;
		Node<K, E> aux = array[index];
		
		if(array[index] == null){
			return null;
		}
		else if(aux.getKey().equals(key)){
			return aux;
		}else{
			while(aux.getProx() != null){
				
				if(aux.getKey().equals(key)){
					return aux;
				}
				
				aux = aux.getProx();
			}
			return null;
		}
	}
}
