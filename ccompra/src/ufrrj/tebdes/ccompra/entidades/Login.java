package ufrrj.tebdes.ccompra.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Login {
	
	@Id @GeneratedValue
	@Column(name="log_id")
	private Long id;
	@Column(name="log_email", nullable=false, length=100)
	private String email;
	@Column(name="log_senha", nullable=false, length=100)
	private String senha;
	@Column(name="log_conf_senha", nullable=false, length=100)
	private String confSenha;
	
	@OneToOne(mappedBy="login", cascade=CascadeType.ALL)
	private Usuario usuario;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Usuario getUsuario(){
		return this.usuario;
	}
	public void setUsuario(Usuario usuario){
		this.usuario = usuario;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getConfSenha() {
		return confSenha;
	}
	public void setConfSenha(String confSenha) {
		this.confSenha = confSenha;
	}
}
