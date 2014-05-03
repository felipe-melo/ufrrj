package exerc.lista3;

public class Item {
	
	protected int id;
	protected String nome;
	protected float preco;
	
	public Item(String nome, float preco){
		this.nome = nome;
		this.preco = preco;
		this.id = GeraId.getId();
	}

	@Override
	public String toString() {
		String str;
		
		str = "Código: " + id + "\n";
		str += "Nome: " + this.nome + "\n";
		str += "Preço: " + this.preco + "\n\n";		
		
		return str;
	}
}
