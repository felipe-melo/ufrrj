package arvore.b;

import java.util.ArrayList;


public class BTree<K, V> {
	
	private Page<K, V> root;
	private int height;
	private int d;
	
	private Object[] objVet = new Object[2];
	private Node<K, V> deletedNode;
	
	public BTree(int d){
		this.setD(d);
	}
	
	public void insert(Comparable<K> key, V value){
		this.setRoot(this.insert(this.getRoot(), key, value));
	}
	
	private Page<K, V> insert(Page<K, V> page, Comparable<K> key, V value){
		if (page == null){
			page = new Page<K, V>(this.getD());
			Node<K, V> newOne = new Node<K, V>(key, value);
			page.getElements().add(newOne);
		}else{
			boolean flag = true;
			int i = 0;
			for(Node<K, V> aux : page.getElements()){				
				if (aux.keyGreaterThan(key)){
					if (aux.getPage() != null)
						aux.setPage(insert(aux.getPage(), key, value));						
					else{
						Node<K, V> newOne = new Node<K, 	V>(key, value);
						page.getElements().add(i, newOne);
						page.checkLenght();
					}
					flag = false;
					break;
				}
				i++;
			}
			if (flag){
				if (page.getPage() != null)
					page.setPage(insert(page.getPage(), key, value));
				else{
					Node<K, V> newOne = new Node<K, V>(key, value);
					page.getElements().add(newOne);
					page.checkLenght();
				}
			}
		}
		
		if (page.hasOverFlow()){
			Page<K, V> left = new Page<K, V>(this.getD());
			Page<K, V> right = new Page<K, V>(this.getD());
			if (page == this.getRoot()){
				for (int i = 0; i < page.getQuant()/2; i++){
					left.getElements().add(page.getElements().get(i));
					right.getElements().add(page.getElements().get(page.getQuant()/2 + 1 + i));
				}
				Node<K, V> aux = page.getElements().get(page.getQuant()/2);
				left.setPage(aux.getPage());
				right.setPage(page.getPage());
				aux.setPage(left);
				page.getElements().clear();
				page.getElements().add(aux);
				page.setFather(null);
				page.setPage(right);
				right.setFather(page);
				left.setFather(page);
				page.checkLenght();
				
				if (right.getPage() != null){				
					right.getPage().setFather(right);
					left.getPage().setFather(left);
					
					for (Node<K, V> no: right.getElements()){
						no.getPage().setFather(right);
					}
					
					for (Node<K, V> no: left.getElements()){
						no.getPage().setFather(left);
					}
				}				
			}else{
				int j = -1;
				for (int i = 0; i < page.getFather().getQuant(); i++){
					if (page.getFather().getElements().get(i).getPage() == page){
						j = i;
						break;
					}
				}
				if (j == -1){
					for (int i = 0; i < page.getQuant()/2; i++){
						left.getElements().add(page.getElements().get(i));
						right.getElements().add(page.getElements().get(page.getQuant()/2 + 1 + i));
					}
					Node<K, V> aux = page.getElements().get(page.getQuant()/2);
					right.setPage(aux.getPage());
					left.setPage(page.getPage());
					aux.setPage(left);
					page.getFather().getElements().add(aux);
					page.getFather().checkLenght();
					page.getFather().setPage(right);
					right.setFather(page.getFather());
					left.setFather(page.getFather());
					page = right;
					page.checkLenght();
					
					if (right.getPage() != null){
					
						right.getPage().setFather(right);
						left.getPage().setFather(left);
						
						for (Node<K, V> no: right.getElements()){
							no.getPage().setFather(right);
						}
						
						for (Node<K, V> no: left.getElements()){
							no.getPage().setFather(left);
						}
					}
				}else{
					for (int i = 0; i < page.getQuant()/2; i++){
						left.getElements().add(page.getElements().get(i));
						right.getElements().add(page.getElements().get(page.getQuant()/2 + 1 + i));
					}
					Node<K, V> aux = page.getElements().get(page.getQuant()/2);
					right.setPage(aux.getPage());
					left.setPage(page.getPage());
					aux.setPage(left);
					page.getFather().getElements().get(j).setPage(right);
					page.getFather().getElements().add(j, aux);
					right.setFather(page.getFather());
					left.setFather(page.getFather());
					page = right;
					page.checkLenght();
					
					if (right.getPage() != null){
						right.getPage().setFather(right);
						left.getPage().setFather(left);
						
						for (Node<K, V> no: right.getElements()){
							no.getPage().setFather(right);
						}
						
						for (Node<K, V> no: left.getElements()){
							no.getPage().setFather(left);
						}
					}
				}
			}
			this.setHeightRec(right);
			this.setHeightRec(left);
		}
		setHeightRec(page);
		return page;
	}
	
	@SuppressWarnings("unchecked")
	public Node<K, V> delete(Comparable<K> key){
		this.delete(this.getRoot(), key);
		this.BalanceTree((Page<K, V>)this.objVet[1]);
		if (this.getDeletedNode() == null)
			this.setDeletedNode(new Node<K, V>(null, null));
		return this.getDeletedNode();
	}
	
	private Page<K, V> delete(Page<K, V> page, Comparable<K> key){
		if (page == null){
			return null;
		}else{
			boolean flag = true; 
			int i = 0;
			for(Node<K, V> aux : page.getElements()){	
				if (aux.keyGreaterThan(key)){
					if (aux.getPage() != null)
						aux.setPage(delete(aux.getPage(), key));
					flag = false;
					break;
				}else if (aux.keyEqualsThan(key)){
					
					if (aux.getPage() == null){						
						this.setDeletedNode(page.getElements().remove(i));
						page.checkLenght();
						objVet[1] = page;
					}else{
						Page<K, V> auxP = aux.getPage(); 
						this.objVet = this.findBiggestOfLowest(aux.getPage());
						((Node<K, V>)objVet[0]).setPage(auxP);
						this.setDeletedNode(page.getElements().remove(i));
						page.getElements().add(i, (Node<K, V>)objVet[0]);
					}
					flag = false;
					break;
				}
				i++;
			}
			if (flag){
				if (page.getPage() != null){
					page.setPage(delete(page.getPage(), key));
					page.checkLenght();
				}
			}
		}
		
		return page;
	}
	
	private Object[] findBiggestOfLowest(Page<K, V> page){
		
		Object[] objVet = new Object[2];
		
		if (page.getPage() == null){
			objVet[0] = page.getElements().remove(page.getElements().size() - 1);
			page.checkLenght();
			objVet[1] = page;
			return objVet;
		}
		
		return findBiggestOfLowest(page.getPage());
	}
	
	private void BalanceTree(Page<K, V> page){
		if (page.hasUnderFlow()){
			boolean brotherHelp = false;
			if (page.getFather() == null){
				if (page.getQuant() == 0){
					this.root.setPage(page.getPage());
					return;
				}
			}
			ArrayList<Node<K, V>> list = page.getFather().getElements();
			Page<K, V> father = page.getFather();
			Node<K, V> auxDad = null;
			Node<K, V> nodeDad = null;
			int index = 0;
			for (int i = 0; i < list.size(); i++){
				if (list.get(i).getPage() == page){
					index = i;
					nodeDad = list.get(i);
					if (i == list.size() - 1 && father.getPage().getQuant() > this.getD()){
						brotherHelp = true;
						Node<K, V> auxBro = father.getPage().getElements().remove(0);
						father.getPage().checkLenght();
						auxDad = father.getElements().remove(father.getQuant() - 1);
						
						auxDad.setPage(page.getPage());
						page.setPage(auxBro.getPage());
						auxBro.setPage(page);
						
						father.getElements().add(auxBro);
						page.getElements().add(auxDad);
						
						father.getPage().checkLenght();
						father.checkLenght();
						page.checkLenght();
							
						break;
					}else if (i+1 <= list.size()-1 && list.get(i + 1).getPage().getElements().size() > this.getD()){
						brotherHelp = true;		
						Node<K, V> auxBro = list.get(i + 1).getPage().getElements().remove(0);
						list.get(i+1).getPage().checkLenght();
						auxDad = list.remove(i + 1);
						
						auxDad.setPage(page.getPage());
						page.setPage(auxBro.getPage());
						auxBro.setPage(page);
						
						list.add(i + 1, auxBro);
						
						page.getElements().add(auxDad);
						
						father.checkLenght();
						page.checkLenght();
						break;
					}
				}else if (page.getFather().getPage() == page && 
						list.get(list.size() - 1).getPage().getQuant() > this.getD()){
					brotherHelp = true;		
					int size = list.get(list.size() - 1).getPage().getQuant() - 1;
					Node<K, V> auxBro = list.get(list.size() - 1).getPage().getElements().remove(size);
					list.get(list.size() - 1).getPage().checkLenght();
					
					auxDad = list.remove(list.size() - 1);
					
					auxDad.setPage(list.get(list.size() - 1).getPage().getPage());
					list.get(list.size() - 1).getPage().setPage(auxBro.getPage());
					auxBro.setPage(list.get(list.size() - 1).getPage());					
					
					list.add(auxBro);
					page.getElements().add(0, auxDad);
					
					father.checkLenght();
					page.checkLenght();
					break;
				}
			}
			if (!brotherHelp){
				if (nodeDad != null && index < father.getQuant() - 1){
					Node<K, V> fa = list.remove(index);
					list.get(index + 1).getPage().getElements().add(0, fa);
					fa.setPage(fa.getPage().getPage());
					for (int i = page.getQuant() -1; i >= 0; i--){
						list.get(index + 1).getPage().getElements().add(0, page.getElements().remove(i));
					}
					father.getPage().checkLenght();
					father.checkLenght();
					this.BalanceTree(father);
				}else if (nodeDad != null && index == father.getQuant() -1){
					Node<K, V> fa = list.remove(index);
					father.getPage().getElements().add(0, fa);					
					for (int i = fa.getPage().getQuant() -1; i >= 0; i--){
						father.getPage().getElements().add(0, fa.getPage().getElements().remove(i));
					}
					fa.setPage(fa.getPage().getPage());
					father.getPage().checkLenght();
					father.checkLenght();
					this.BalanceTree(father);
				}else if (nodeDad == null){
					Node<K, V> fa = list.remove(list.size() - 1);
					father.getPage().getElements().add(0, fa);
					Page<K, V> temp = fa.getPage();
					fa.setPage(fa.getPage().getPage());
					for (int i = page.getQuant() -1; i >= 0; i--){
						father.getPage().getElements().add(0, temp.getElements().remove(i));
					}
					father.getPage().checkLenght();
					father.checkLenght();					
					this.BalanceTree(father);
				}
			}
		}		
	}
	
	private int setHeightRec(Page<K, V> page) {
		if (page == null){
			return 0;
		}else{
			return page.setHeight(this.setHeightRec(page.getPage()) + 1);
		}
	}
	
	public Node<K, V> find(Comparable<K> key){
		Node<K, V> node = find(this.getRoot(), key);
		if (node == null)
			node = new Node<K, V>(null, null);
		return node;
	}
	
	private Node<K, V> find(Page<K, V> page, Comparable<K> key){
		if (page == null){
			return null;
		}else{
			boolean flag = true;			
			for(Node<K, V> aux : page.getElements()){
				if (aux.keyGreaterThan(key)){
					flag = false;
					return find(aux.getPage(), key);
				}else if (aux.keyEqualsThan(key)){
					return aux;
				}
			}
			if (flag){
				return find(page.getPage(), key);
			}
		}
		return null;
	}

	public Page<K, V> getRoot() {
		return root;
	}

	public void setRoot(Page<K, V> root) {
		this.root = root;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public Node<K, V> getDeletedNode() {
		return deletedNode;
	}

	public void setDeletedNode(Node<K, V> deletedNode) {
		this.deletedNode = deletedNode;
	}
}