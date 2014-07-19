package comum.classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import join.hashtable.HashTable;
import arvore.b.BTree;

public class Constants {
	
	public final static String pessoas = "arquivos/pessoas.txt";
	public final static String enderecos = "arquivos/enderecos.txt";
	public final static String clientes = "arquivos/clientes.txt";
	public final static String cardapio = "arquivos/cardapio.txt";
	public final static String pedidos = "arquivos/pedidos.txt";
	
	public static HashMap<String, Integer> fields = new HashMap<String, Integer>();
	
	public static void startFields(){
		fields.put("cliente_telefone", 0);
		fields.put("cliente_nome", 1);
		fields.put("cliente_endereco", 2);
		fields.put("cardapio_pizza_nome", 0);
		fields.put("cardapio_ingredientes", 1);
		fields.put("cardapio_preco", 2);
		fields.put("pedido_telefone", 0);
		fields.put("pedido_data", 1);
		fields.put("pedido_hora", 2);
		fields.put("pedido_pizza_nome", 3);
		fields.put("pedido_quantidade", 4);
	}
	
	public static BufferedReader setFileToRead(String path) throws FileNotFoundException{
		FileReader fr = new FileReader(path);
		return new BufferedReader(fr);
	}
	
	public static void closeFile(BufferedReader file) throws IOException{
		file.close();
	}
	
	public static void loadTree (BTree tree, File file){
		file.open();
		while(file.hasNext()){
			Tuple tuple = file.next();
			int index = tuple.getKeyField().hashCode();
			//if (index < 0) System.out.println(tuple.getKeyField());
			tree.insert(index, tuple);
		}
	}
	
	public static void loadHash (HashTable table, File file){
		file.open();
		while(file.hasNext()){
			Tuple tuple = file.next();
			table.insert(tuple.getKeyField().hashCode(), tuple);
		}
	}
	
	public static void quickSort(ArrayList<Pedido> pedidos, int ini, int fim) {
		int meio;
		if (ini < fim) {
			meio = partition(pedidos, ini, fim);
			quickSort(pedidos, ini, meio);
			quickSort(pedidos, meio + 1, fim);
		}
	}
		 
	public static int partition(ArrayList<Pedido> pedidos, int ini, int fim) {
		int topo, i;
		Pedido pivo;
		pivo = pedidos.get(ini);
		topo = ini;
	 
		for (i = ini + 1; i <= fim; i++) {
			if (pedidos.get(i).date.after(pivo.date)) {
				pedidos.set(topo, pedidos.get(i));
				pedidos.set(i, pedidos.get(topo + 1));
				topo++;
			}
		}
		pedidos.set(topo, pivo);
		return topo;
	}
}
