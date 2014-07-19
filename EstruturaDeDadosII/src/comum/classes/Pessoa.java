package comum.classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Pessoa {
	
	private static BufferedReader br;
	private String codigo;
	private String nome;
	private String sobreNome;
	private String codEnd;
	
	public Pessoa(String codigo, String nome, String sobreNome, String codEnd){
		this.setCodigo(codigo);
		this.setNome(nome);
		this.setSobreNome(sobreNome);
		this.setCodEnd(codEnd);
	}
	
	public Endereco getEndereco() throws IOException{
		br = Constants.setFileToRead(Constants.enderecos);
		String linha[];
		while(br.ready()){
			linha = br.readLine().split("\t");
			
			if (linha[0].equals(this.getCodEnd())){
				return new Endereco(linha[0], linha[1], linha[2], linha[3], linha[4], linha[5], linha[6]);
			}
		}
		return null;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getSobreNome() {
		return sobreNome;
	}
	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}
	public String getCodEnd() {
		return codEnd;
	}
	public void setCodEnd(String codEnd) {
		this.codEnd = codEnd;
	}

}
