package biblioteca.ufrrj;

public class Principal {
	
	public static void main(String[] args){
		
		//Cria 3 alunos
		Aluno aluno1 = new Aluno(1, "aluno", "felipe", 2012785091, "ccomp");		
		Aluno aluno2 = new Aluno(2, "aluno", "bianca", 40040001, "vet");		
		Aluno aluno3 = new Aluno(3, "aluno", "valente", 27304022, "adm");
		
		//Cria 3 funcionarios
		Funcionario func1 = new Funcionario(4, "func", "rodrigo", 4027, "dtl", "tecadm");		
		Funcionario func2 = new Funcionario(5, "func", "goku", 2541, "lab", "tecadm");		
		Funcionario func3 = new Funcionario(6, "func", "vegeta", 5240, "reitoria", "tecadm");
		
		//Cria 3 professores
		Professor prof1 = new Professor(7, "prof", "carlos", 2350, "dtl", "ccomp");				
		Professor prof2 = new Professor(8, "prof", "marisa", 8876, "dtl", "letras");		
		Professor prof3 = new Professor(9, "prof", "fernando", 7842, "hisgeo", "historia");
		
		//Cria 3 livros
		Livro liv1 = new Livro(1, "livro", "desastre");
		Livro liv2 = new Livro(2, "livro", "java android");
		Livro liv3 = new Livro(3, "livro", "cálculo I");
		
		//Cria 3 revistas
		Revista revis1 = new Revista(4, "revista", "veja");
		Revista revis2 = new Revista(5, "revista", "contigo");
		Revista revis3 = new Revista(6, "revista", "rolling stone");
		
		//Cria 3 filmes
		Filme filme1 = new Filme(7, "filme", "os miseraveis");
		Filme filme2 = new Filme(8, "filme", "harry potter");
		Filme filme3 = new Filme(9, "filme", "madagascar");
		
		//Cria um objeto Biblioteca
		Biblioteca bibli = new Biblioteca();
		
		//Adiciona os usuários na biblioteca
		bibli.users.add(aluno1);
		//bibli.users.add(aluno2);
		bibli.users.add(aluno3);
		bibli.users.add(func1);
		bibli.users.add(func2);
		bibli.users.add(func3);
		bibli.users.add(prof1);
		bibli.users.add(prof2);
		bibli.users.add(prof3);
		
		//Adiciona os exemplares na biblioteca
		bibli.exemplares.add(liv1);
		bibli.exemplares.add(liv2);
		bibli.exemplares.add(liv3);
		bibli.exemplares.add(revis1);
		bibli.exemplares.add(revis2);
		bibli.exemplares.add(revis3);
		bibli.exemplares.add(filme1);
		bibli.exemplares.add(filme2);
		bibli.exemplares.add(filme3);
		
		//Realiza emprestimos
		bibli.EmprestaExemplar(filme1, aluno2);
		bibli.EmprestaExemplar(filme2, aluno2);
		bibli.EmprestaExemplar(filme3, aluno2);
		
		//Efetua devolução
		bibli.DevolveExemplar(filme2, aluno2);
		
		//Realiza emprestimo
		bibli.EmprestaExemplar(revis1, aluno2);
		
		for (Emprestimo em: bibli.emprestimos){
			System.out.println(em.codExemplar + " - " + em.codUsu);
		}
		
		
	}

}
