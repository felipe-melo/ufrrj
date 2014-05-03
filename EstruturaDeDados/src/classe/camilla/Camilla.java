package classe.camilla;

public class Camilla implements Comparable{
	
	private String nome;
	private int idade;
	private String Universidade;
	
	
	public Camilla(String nome, int idade, String universidade) {
		super();
		this.nome = nome;
		this.idade = idade;
		Universidade = universidade;
	}
	
	@Override
	public String toString() {
		return "{" + this.getNome() + ", " + this.getIdade() + ", " + this.getUniversidade() + "}"; 
	}

	@Override
	public boolean equals(Object obj) {
		return (((Camilla) obj).getNome().equals(this.getNome()));
	}
	/*
	public int compara(Object obj){
		if(obj.getNome)
	}
	*/
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getUniversidade() {
		return Universidade;
	}
	public void setUniversidade(String universidade) {
		Universidade = universidade;
	}

	@Override
	public int Comparavel(Object obj) {
		
		String nome = ((Camilla) obj).getNome();
		
		int result = nome.compareTo(this.getNome());
		
		return result;
	}

}
