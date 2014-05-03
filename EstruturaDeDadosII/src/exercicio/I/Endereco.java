package exercicio.I;

public class Endereco {
	
	private String codigo;
	private String logradouro;
	private String numero;
	private String cep;
	private String bairro;
	private String cidade;
	private String estado;
	
	
	
	public Endereco(String codigo, String logradouro, String numero,
			String cep, String bairro, String cidade, String estado) {
		
		this.setCodigo(codigo);
		this.setLogradouro(logradouro);
		this.setNumero(numero);
		this.setCep(cep);
		this.setBairro(bairro);
		this.setCidade(cidade);
		this.setEstado(estado);
	}

	public void imprimeEndereco(){
		System.out.println("Logradouro: " + this.getLogradouro() + 
				" " + this.getNumero() + "º, " + this.getBairro() + ", " + this.getCidade() + 
				", " + this.getEstado() + ", Cep: " + this.getCep());
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
