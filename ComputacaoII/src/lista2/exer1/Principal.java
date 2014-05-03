package lista2.exer1;

public class Principal {
	
	public static void main(String[] args){
		Empregado[] e = new Empregado[6];
		
		e[0] = new Empregado("Goku", 5000.00, new Data(1, 9, 2001));
		e[1] = new Empregado("Vegeta", 8000.00, new Data(1, 9, 2001));
		e[2] = new Secretaria("Pikolo", 2000.00, new Data(1, 9, 2001));
		e[3] = new Secretaria("Kuririn", 3500.00, new Data(1, 9, 2001));
		e[4] = new Gerente("Cel", 4000.00, new Data(1, 9, 2001), (Secretaria) e[2], e[1], e[0]);
		e[5] = new Gerente("Majinbu", 6500.00, new Data(1, 9, 2001), (Secretaria) e[3], e[1], e[0]);
		
		((Gerente) e[4]).aumentaSalarioSubordinados(10);
		
		for (int i = 0; i < e.length; i++)
			System.out.println(e[i] + "\n");
		
		((Secretaria) e[3]).anotaRecado("Felipe");
		((Secretaria) e[3]).anotaRecado("Jos�");
		((Secretaria) e[3]).anotaRecado("Pedro");
		((Secretaria) e[3]).anotaRecado("Carlos");
		((Secretaria) e[3]).anotaRecado("Bianca");
		((Secretaria) e[3]).anotaRecado("Gabriel");
		((Secretaria) e[3]).anotaRecado("Marisa");
		((Secretaria) e[3]).anotaRecado("Maria");
		((Secretaria) e[3]).imprimeContato();
		
	}

}