package prova.agenda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Agenda {
	
	ArrayList<Contato> contatos = new ArrayList<Contato>();
	
	public Contato busca(Contato c){
		
		for (Contato aux: contatos){
			if (c.nome == aux.nome && c.email == aux.email && c.sobre == aux.sobre){
				System.out.println("Acho veio");
				return aux;			
			}
		}
			
		System.out.println("Contato não encontrado");
		return c;
	}
	
	public void ordena(){
		
		Contato aux = new Contato();
		
		for (Contato i: contatos){
			for (Contato j: contatos){
				
			}
		}
	}

}
