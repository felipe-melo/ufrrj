package exerc.lista3;

public class Principal {
	
	public static void main(String[] args){
		
		/*Cria diversos itens avulsos*/
		Item a1 = new Item("Arroz de 1kg", 1.5f);
		Item f1 = new Item("Feijão de 1kg", 1.5f);
		Item o1 = new Item("Oleo", 1.5f);
		Item acu1 = new Item("Açucar de 1kg", 1.5f);
		Item sal1 = new Item("Sal de 1kg", 1.5f);
		
		/*Criar diversos itens montados*/
		
		Item a5 = new Montado("Arroz de 5kg");
		
		((Montado) a5).inclui(a1);
		((Montado) a5).inclui(a1);
		((Montado) a5).inclui(a1);
		((Montado) a5).inclui(a1);
		((Montado) a5).inclui(a1);
		
		Item acu5 = new Montado("Açucar de 5kg");
		
		((Montado) acu5).inclui(acu1);
		((Montado) acu5).inclui(acu1);
		((Montado) acu5).inclui(acu1);
		((Montado) acu5).inclui(acu1);
		((Montado) acu5).inclui(acu1);
		
		Item cb1 = new Montado("Cesta básica simples");
		
		((Montado) cb1).inclui(a1);
		((Montado) cb1).inclui(f1);
		((Montado) cb1).inclui(o1);
		((Montado) cb1).inclui(acu1);
		((Montado) cb1).inclui(sal1);
		
		Item cb2 = new Montado("Cesta básica complexa");
		
		((Montado)cb2).inclui(a5);
		((Montado)cb2).inclui(f1);
		((Montado)cb2).inclui(o1);
		((Montado)cb2).inclui(acu5);
		((Montado)cb2).inclui(sal1);
		
		System.out.println(cb2);
		
	}

}
