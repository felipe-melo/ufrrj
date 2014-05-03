package prova.agenda;

public class Principal {
	
	public static void main(String[] args){
		
		//Cria contato1
		Contato cont1 = new Contato();		
		cont1.nome = "wilson";
		cont1.email = "wilson@";
		cont1.id = 1;
		cont1.tel = 12345678;
		cont1.sobre = "silva";
		
		//Cria contato2
		Contato cont2 = new Contato();		
		cont2.id = 2;
		cont2.nome = "pedro";
		cont2.email = "pedro@";
		cont2.tel = 87654321;
		cont2.sobre = "diniz";
		
		//Cria agenda
		Agenda agenda = new Agenda();
		
		//Adiciona contatos na agenda
		agenda.contatos.add(cont1);
		agenda.contatos.add(cont2);
		
		//Contato a ser buscado
		Contato teste = new Contato();
		//Preenche contato a ser buscado
		teste.nome = "wilson";
		teste.email = "wilson@";
		teste.sobre = "silva";
		
		agenda.busca(teste);
		
	}

}
