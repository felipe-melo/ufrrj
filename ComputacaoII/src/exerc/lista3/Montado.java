package exerc.lista3;
import java.util.ArrayList;


public class Montado extends Item{
	
	private ArrayList<Item> itens = new ArrayList<Item>();
	
	public void inclui(Item item){
		itens.add(item);
		
		this.preco += item.preco;
	}
		
	public Montado(String nome){
		super(nome, 0);
		this.id = GeraId.getId();
	}

	@Override
	public String toString() {
		
		String str = "Código: " + this.id + "\n";
		str += "Nome: " + this.nome + ":\n";		
		str += "Preço: " + this.preco + "\n\n";
		
		str += "Elementos: \n\n";
		
		str += "------------------------------\n";		
		for (Item item: itens){
			str += item.toString();
		}
		str += "------------------------------\n";

		return str;
	}
}
