package lista2.exer1;

public class Data {
	
	private int dia;
	private int mes;
	private int ano;
	
	public Data(int dia, int mes, int ano) {
		super();
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}

	@Override
	public String toString() {
	String str = this.dia + "/" + this.mes + "/" + this.ano;
		return str;
	}
	
}
