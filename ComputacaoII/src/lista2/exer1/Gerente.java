package lista2.exer1;

public class Gerente extends Empregado{
	
	Empregado[]  empregados = new Empregado[3];
	
	public Gerente(String nome, double salario, Data data, Empregado empregado1, Empregado empregado2, Empregado empregado3) {
		super(nome, salario, data);
		this.empregados[0] = empregado1;
		this.empregados[1] = empregado2;
		this.empregados[2] = empregado3;
	}
	
	public void aumentaSalarioSubordinados(float percent){
		for (int i = 0; i < empregados.length; i++){
			empregados[i].aumentaSalario(percent);
		}
	}
	
}
