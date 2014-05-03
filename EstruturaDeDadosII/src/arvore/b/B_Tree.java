package arvore.b;


public class B_Tree<T> {
	
	private Page<T> root;
	private int height;
	private int d;
	
	public B_Tree(int d){
		this.setD(d);
	}
	
	public void insert(Comparable<T> key, T value){
		this.setRoot(this.insert(this.getRoot(), key, value));
	}
	
	private Page<T> insert(Page<T> page, Comparable<T> key, T value){
		if (page == null){
			page = new Page<T>(this.getD());
			Node<T> newOne = new Node<T>(key, value);
			page.getElements().add(newOne);
		}else{
			boolean flag = true; 
			int i = 0;
			for(Node<T> aux : page.getElements()){				
				if (aux.keyGreaterThan(key)){
					if (aux.getPage() != null)
						aux.setPage(insert(aux.getPage(), key, value));						
					else{
						Node<T> newOne = new Node<T>(key, value);
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
					Node<T> newOne = new Node<T>(key, value);
					page.getElements().add(newOne);
					page.checkLenght();
				}
			}
		}
		page.setHeight();
		
		if (page.hasOverFlow()){
			Page<T> left = new Page<T>(this.getD());
			Page<T> right = new Page<T>(this.getD());
			if (page == this.getRoot()){
				for (int i = 0; i < page.getQuant()/2; i++){
					left.getElements().add(page.getElements().get(i));
					right.getElements().add(page.getElements().get(page.getQuant()/2 + 1 + i));
				}
				Node<T> aux = page.getElements().get(page.getQuant()/2);
				aux.setPage(left);
				page.getElements().clear();
				page.getElements().add(aux);
				page.setPage(right);
				right.setFather(page);
				left.setFather(page);
				page.checkLenght();
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
					Node<T> aux = page.getElements().get(page.getQuant()/2);
					aux.setPage(left);
					page.getFather().getElements().add(aux);
					page.getFather().setPage(right);
					right.setFather(page.getFather());
					left.setFather(page.getFather());
					page = right;
					page.checkLenght();
				}else{
					for (int i = 0; i < page.getQuant()/2; i++){
						left.getElements().add(page.getElements().get(i));
						right.getElements().add(page.getElements().get(page.getQuant()/2 + 1 + i));
					}
					Node<T> aux = page.getElements().get(page.getQuant()/2);
					aux.setPage(left);
					page.getFather().getElements().get(j).setPage(right);
					page.getFather().getElements().add(j, aux);
					right.setFather(page.getFather());
					left.setFather(page.getFather());
					page = right;
					page.checkLenght();
				}
			}
		}
		
		return page;
	}
	
	public T searchValue(Comparable<T> key) throws Exception{
		return this.searchValue(key, this.getRoot());
	}
	
	private T searchValue(Comparable<T> key, Page<T> page) throws Exception{
		if (page == null){
			return null;
		}else{
			for (Node<T> no: page.getElements()){
				if (no.keyGreaterThan(key)){
					return searchValue(key, no.getPage());
				}else if (no.keyEqualsThan(key)){
					return no.getValeu();
				}
			}
			return searchValue(key, page.getPage());
		}
	}

	public Page<T> getRoot() {
		return root;
	}

	public void setRoot(Page<T> root) {
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
	
}
