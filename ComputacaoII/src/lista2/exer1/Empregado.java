package lista2.exer1;

public class Empregado {
	
	private String nome;
	private double salario;
	private Data data;
	
	
	
	public Empregado(String nome, double salario, Data data) {
		super();
		this.nome = nome;
		this.salario = salario;
		this.data = data;
	}
	
	public String getNome() {
		return nome;
	}	
	public double getSalario() {
		return salario;
	}
	public String getData() {
		return data.toString();
	}
	
	protected void aumentaSalario(double percent){
		double aumento = this.salario * (percent/100);
		this.salario += aumento;
	}

	@Override
	public String toString() {
		String str = "Nome: " + this.nome + "\n";
		str += "Sal�rio: " + this.salario + "\n";
		str += "Data admiss�o: " + this.getData();
		return str;
	}

}
