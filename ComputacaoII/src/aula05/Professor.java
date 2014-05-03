package aula05;

import java.util.Date;


public class Professor implements Pessoa{
	
	String nome;
	Date dataNascimento;

	@Override
	public int getIdade() {
		// TODO Auto-generated method stub
		return (int) (System.currentTimeMillis() - dataNascimento.getTime());
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return this.nome;
	}

}
