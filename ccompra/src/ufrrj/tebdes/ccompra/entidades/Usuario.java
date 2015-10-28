package ufrrj.tebdes.ccompra.entidades;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ufrrj.tebdes.ccompra.embutidos.Endereco;

@Entity
@Table(name="usuario")
public class Usuario {
	
	public Usuario(){
		this.setEndereco(new Endereco());
		this.setLogin(new Login());
	}
	
	@Id @GeneratedValue
	@Column(name="usu_id")
	private Long id;
	@Column(name="usu_nome", nullable=false, length=50)
	private String nome;
	@Column(name="usu_rg", nullable=false, length=12, unique=true)
	private String rg;
	@Column(name="usu_cpf", nullable=false, length=15, unique=true)
	private String cpf;
	
	@Embedded
	private Endereco endereco;
	
	@Column(name="usu_nivel", nullable=false)
	private Integer nivel;
	
	@Column(name="usu_telefone", nullable=false, length=15)
	private String telefone;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="usu_data_nascimento", nullable=true, length=10)
	private Date dataNascimento;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Login login;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Integer getNivel() {
		return nivel;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
}
