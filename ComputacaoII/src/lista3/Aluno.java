package lista3;

public class Aluno implements Comparavel{
	
	private String nome;
	private float nota;
	
	Aluno(String nome, float nota){
		this.nome = nome;
		this.nota = nota;
	}

	@Override
	public int comparar(Object obj) {
		Aluno aluno = (Aluno) obj;
		
		if (this.nota == aluno.nota){
			return 0;
		}else if (this.nota > aluno.nota){
			return 1;
		}else{
			return -1;
		}
	}

	@Override
	public String toString() {
		String str;
		
		str = "Nome: " + this.nome + "\n";
		str += "Nota: " + this.nota + "\n";
		return str;
	}
	
	
}
