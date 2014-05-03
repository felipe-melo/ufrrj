package biblioteca.ufrrj;

import java.util.ArrayList;
import java.util.HashSet;

public class Biblioteca {
	
	public ArrayList<Usuario> users = new ArrayList<Usuario>();
	public ArrayList<Exemplar> exemplares = new ArrayList<Exemplar>();
	public ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
	
	public boolean EmprestaExemplar(Exemplar exemplar, Usuario user){
		
		if (!users.contains(user) || !exemplares.contains(exemplar)){
			System.out.println("Usuário ou exemplar não cadastrado.");
			return false;
		}
		
		if (!exemplar.disponivel){
			System.out.println("O Exemplar está indisponível.");
			return false;
		}else{
			if (user.cheio){		
				System.out.println("Limite atingido");
				return false;
			
			}else{
				user.exemplar.add(exemplar);
				Emprestimo aux = new Emprestimo();
				aux.codExemplar = exemplar.id;
				aux.codUsu = user.id;
				emprestimos.add(aux);
				exemplar.disponivel = false;
			
				if (user.exemplar.size() == 3)
					user.cheio = true;

				System.out.println("Emprestimo feito com sucesso.");
				return true;
			}
		}
	}
	
	public void DevolveExemplar(Exemplar exemplar, Usuario user){
		
		boolean existe = false;
		Emprestimo aux = new Emprestimo();
		int index1 = -1;
		int index2;
		
		aux.codUsu = user.id;
		aux.codExemplar = exemplar.id;
		
		for (Emprestimo e: emprestimos){
			if (e.codExemplar == aux.codExemplar && e.codUsu == aux.codUsu){
				existe = true;
				index1 = emprestimos.indexOf(e);
			}
		}
		
		if (existe){
			
			emprestimos.remove(index1);
			
			index2 = user.exemplar.indexOf(exemplar);
			user.exemplar.remove(index2);
			
			exemplar.disponivel = true;
			user.cheio = false;
		}else{
			System.out.println("Emprestimo inválido");
		}
	}

}