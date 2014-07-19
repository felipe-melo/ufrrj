package atividade.academica;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import arvore.b.BTree;

import comum.classes.Constants;
import comum.classes.File;
import comum.classes.Pedido;
import comum.classes.Selector;
import comum.classes.Tuple;

public class Consults {
	
	public static ArrayList<Pedido> secondConsult(String fone){
		
		Constants.startFields();
		
		File pedido = new File(Constants.pedidos, "pedido_telefone");
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		
		Selector selector;
		
		selector = new Selector(pedido, false, "pedido_telefone", fone);
		selector.open();
		while(selector.hasNext()){
			Tuple aux = selector.next();
			if(aux.getLine(0, "pedido_telefone").equals(fone)){
				
				pedidos.add(new Pedido(aux.getLine(0, "pedido_data", "pedido_hora", "pedido_pizza_nome", "pedido_quantidade")));
				
				System.out.println(aux.getLine(0, "pedido_data", "pedido_hora", "pedido_pizza_nome"));
			}
			
		}
		Constants.quickSort(pedidos, 0, pedidos.size()-1);
		
		return pedidos;
	}
	
	public static String firstConsult(String fone){
		
		Constants.startFields();
		
		File cliente = new File(Constants.clientes, "cliente_telefone");
		
		Selector selector;
		selector = new Selector(cliente, false, "pedido_telefone", fone);
		selector.open();
			
		/*BTree tree = new BTree(2);
		Constants.loadTree(tree, cliente);*/
		
		if (selector.hasNext()){
			String aux = "";
			try {
				byte array[] = selector.next().getLine(0, "cliente_endereco").getBytes();
				aux = new String(array, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return aux;
		}
		return "";
		
	}
	
	public static ArrayList<String> thirthConsult(String[] array){
		Constants.startFields();
		
		ArrayList<String> clientes = new ArrayList<String>();
		
		File cliente = new File(Constants.clientes, "cliente_telefone");
		File pedido = new File(Constants.pedidos, "pedido_telefone");
		
		BTree tree = new BTree(2);
		Constants.loadTree(tree, cliente);
		
		Selector selector;
		selector = new Selector(pedido, false, array);
		selector.open();
		
		while (selector.hasNext()){
			String tele = selector.next().getLine(0, "pedido_telefone");
			if (tele.equals("")) break;
			int i = tele.hashCode();
			Tuple t = ((Tuple)tree.find(i).getValeu());
			
			clientes.add(t.getLine(0, "cliente_nome"));
		}
		
		return clientes;
	}
	
	public static ArrayList<String> forthConsult(String fone){
		
		Constants.startFields();
		
		ArrayList<String> pizzas = new ArrayList<String>();
		
		File cliente = new File(Constants.clientes, "cliente_telefone");
		File pedido = new File(Constants.pedidos, "pedido_telefone");
		
		BTree tree = new BTree(2);
		Constants.loadTree(tree, cliente);
		
		Selector selector;
		
		
		int quant = 0;
		selector = new Selector(pedido, false, "pedido_telefone", fone);
		selector.open();
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		while(selector.hasNext()){
			String[] aux = selector.next().getLine(0, "pedido_pizza_nome", "pedido_quantidade").split("\t");
			if (aux.length == 2){
				if (map.get(aux[0].hashCode()) != null){
					int ant = map.get(aux[0]);
					int atu = Integer.valueOf(aux[1]);
					map.put(aux[0], Integer.valueOf(atu + ant));
				}else{
					map.put(aux[0], Integer.valueOf(aux[1]));
				}
			}
		}
		
		Set<String> keys = map.keySet();
		
		for (String k: keys){
			int aux = ((Integer)map.get(k)).intValue();
			if (aux > quant)
				quant = aux;
		}
		
		
		for (String k: keys){
			int aux = ((Integer)map.get(k)).intValue();
			if (aux == quant && !(k.equals("")))
				pizzas.add(k);
		}
		return pizzas;
	}
	
	public static ArrayList<String> fifthConsult(String ingrediente){
		
		Constants.startFields();
		
		ArrayList<String> pizzas = new ArrayList<String>();
		
		File cardapio = new File(Constants.cardapio, "cardapio_pizza_nome");
		File cliente = new File(Constants.clientes, "cliente_telefone");

		
		BTree tree = new BTree(2);
		Constants.loadTree(tree, cliente);
		
		Selector selector;
		
		selector = new Selector(cardapio, true, "cardapio_ingredientes", "mussarela");
		selector.open();
		
		while (selector.hasNext()){
			pizzas.add(selector.next().getLine(0, "cardapio_pizza_nome"));
			
		}
		
		return pizzas;
	}

	
	
}