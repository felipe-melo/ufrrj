package exercicio.joao;

public class Pessoa {
	
	int cod_p;
	String tipo;
	
	@Override
	public boolean equals(Object obj) {
		Pessoa aux = (Pessoa) obj;
		
		if (this.tipo == aux.tipo)
			return true;
		else
			return false;
	}
	
}
